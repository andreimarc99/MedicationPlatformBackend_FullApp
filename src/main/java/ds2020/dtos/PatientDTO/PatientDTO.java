package ds2020.dtos.PatientDTO;

import ds2020.dtos.CaregiverDTO.CaregiverDTO;
import ds2020.dtos.UserDTO.UserDTO;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

public class PatientDTO extends RepresentationModel<PatientDTO> {
    private Long patientId;
    private String name;
    private String address;
    private Date birthDate;
    private UserDTO user;
    private CaregiverDTO caregiverDTO;

    public PatientDTO() {
    }

    public PatientDTO(Long patientId, String name, String address, Date birthDate, UserDTO user) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.user = user;
    }

    public PatientDTO(Long patientId, String name, String address, Date birthDate, UserDTO user, CaregiverDTO caregiverDTO) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.user = user;
        this.caregiverDTO = caregiverDTO;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CaregiverDTO getCaregiverDTO() {
        return caregiverDTO;
    }

    public void setCaregiverDTO(CaregiverDTO caregiverDTO) {
        this.caregiverDTO = caregiverDTO;
    }
}
