package ds2020.dtos.validators;

import ds2020.dtos.UserDTO.UserDTO;

public class UserValidator {

    public static boolean isValid(UserDTO user) {
        if (user != null) {
            if (!user.getUsername().equals("")) {
                if (!user.getPassword().equals("")) {
                    if (!user.getRole().equals("")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
