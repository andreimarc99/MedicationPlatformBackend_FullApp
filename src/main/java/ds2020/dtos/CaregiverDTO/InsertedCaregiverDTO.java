package ds2020.dtos.CaregiverDTO;

import ds2020.entities.UserRole;

import java.sql.Date;

public class InsertedCaregiverDTO {

    private Long caregiverId;
    private String name;
    private String address;
    private Date birthDate;
    private String username;
    private String password;
    private UserRole role;

    public InsertedCaregiverDTO() {
    }

    public InsertedCaregiverDTO(Long caregiverId, String name, String address, Date birthDate) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
    }

    public InsertedCaregiverDTO(Long caregiverId, String name, String address, Date birthDate, String username, String password, UserRole userRole) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.role = userRole;
    }

    public Long getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(Long caregiverId) {
        this.caregiverId = caregiverId;
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

    public UserRole getUserRole() {
        return role;
    }

    public void setUserRole(UserRole userRole) {
        this.role = userRole;
    }
}
