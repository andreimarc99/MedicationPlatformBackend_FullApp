package ds2020.dtos.UserDTO;

import ds2020.entities.UserRole;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

public class UserDetailsDTO extends RepresentationModel<UserDetailsDTO> {

    private String username;
    private String password;
    private String name;
    private String address;
    private Date birthDate;
    private UserRole role;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO (String username, String password, String name, String address, Date birthDate, UserRole role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
