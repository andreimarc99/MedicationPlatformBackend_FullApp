package ds2020.dtos.builders;

import ds2020.dtos.UserDTO.UserDTO;
import ds2020.dtos.UserDTO.UserDetailsDTO;
import ds2020.entities.User;

public class UserBuilder {

    private UserBuilder() {
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(),
                user.getPassword(),
                user.getRole());
    }

    public static User toUser(UserDTO user) {
        return new User(user.getUsername(),
                user.getPassword(),
                user.getRole());
    }
}
