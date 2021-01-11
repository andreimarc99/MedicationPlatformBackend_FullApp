package ds2020.dtos.DoctorDTO;

import ds2020.dtos.UserDTO.UserDTO;
import ds2020.entities.User;
import ds2020.entities.UserRole;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

public class InsertedDoctorDTO extends RepresentationModel<InsertedDoctorDTO> {

    private Long doctorId;
    private String name;
    private String address;
    private Date birthDate;
    private String username;
    private String password;
    private UserRole role;

    public InsertedDoctorDTO() {
    }

    public InsertedDoctorDTO(Long doctorId, String name, String address, Date birthDate, String username, String password, UserRole role) {
        this.doctorId = doctorId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.role = role;

    }

    public String getName() {
        return name;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
