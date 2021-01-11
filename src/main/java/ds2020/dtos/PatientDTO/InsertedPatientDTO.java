package ds2020.dtos.PatientDTO;

import ds2020.entities.UserRole;

import java.sql.Date;

public class InsertedPatientDTO {

    private Long patientId;
    private String name;
    private String address;
    private Date birthDate;
    private String username;
    private String password;
    private UserRole role;
    private Long caregiverId;

    public InsertedPatientDTO() {
    }

    public InsertedPatientDTO(Long patientId, String name, String address, Date birthDate) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
    }

    public InsertedPatientDTO(Long patientId, String name, String address, Date birthDate, String username, String password, UserRole role, Long caregiverId) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.role = role;
        this.caregiverId = caregiverId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(Long caregiverId) {
        this.caregiverId = caregiverId;
    }
}
