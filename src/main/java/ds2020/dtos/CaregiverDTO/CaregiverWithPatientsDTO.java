package ds2020.dtos.CaregiverDTO;

import ds2020.dtos.PatientDTO.PatientDTO;
import ds2020.dtos.UserDTO.UserDTO;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;
import java.util.List;

public class CaregiverWithPatientsDTO extends RepresentationModel<CaregiverWithPatientsDTO> {
    private Long caregiverId;
    private String name;
    private String address;
    private Date birthDate;
    private UserDTO user;
    private List<PatientDTO> patients;

    public CaregiverWithPatientsDTO() {
    }

    public CaregiverWithPatientsDTO(Long caregiverId, String name, String address, Date birthDate, UserDTO user, List<PatientDTO> patients) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.user = user;
        this.patients = patients;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<PatientDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDTO> patients) {
        this.patients = patients;
    }
}
