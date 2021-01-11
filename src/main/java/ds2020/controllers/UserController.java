package ds2020.controllers;

import ds2020.dtos.UserDTO.UserDTO;
import ds2020.dtos.UserDTO.UserDetailsDTO;
import ds2020.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user_t")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.findUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> insertUser(@RequestBody UserDTO userDTO) {
        String username = userService.insertUser(userDTO);
        return new ResponseEntity<>(username, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<String>(username, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUserDTO = userService.updateUser(userDTO);
            return new ResponseEntity<>(userDTO.getUsername(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
