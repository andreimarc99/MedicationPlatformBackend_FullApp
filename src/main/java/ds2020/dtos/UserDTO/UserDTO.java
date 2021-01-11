package ds2020.dtos.UserDTO;

import ds2020.entities.UserRole;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

public class UserDTO extends RepresentationModel<UserDTO> {

    private String username;
    private String password;
    protected UserRole role;

    public UserDTO() {
    }

    public UserDTO (String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }
}
