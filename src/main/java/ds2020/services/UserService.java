package ds2020.services;

import ds2020.dtos.UserDTO.UserDTO;
import ds2020.dtos.UserDTO.UserDetailsDTO;
import ds2020.dtos.builders.UserBuilder;
import ds2020.dtos.validators.UserValidator;
import ds2020.entities.User;
import ds2020.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    public String insertUser(UserDTO userDTO) {
        User user = UserBuilder.toUser(userDTO);
        user = userRepository.save(user);
        LOGGER.debug("User with username {} was inserted in db", user.getUsername());
        return user.getUsername();
    }

    public void deleteUser(String username) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User " + username + " not found.");
        }
        userRepository.deleteById(username);
    }

    public UserDTO updateUser(UserDTO userDTO) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(userDTO.getUsername());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User " + userDTO.getUsername() + " not found.");
        }
        User updatedUser = null;
        if (UserValidator.isValid(userDTO)) {
            updatedUser = UserBuilder.toUser(userDTO);
            updatedUser = userRepository.save(updatedUser);
        } 
        return UserBuilder.toUserDTO(updatedUser);
    }
}
