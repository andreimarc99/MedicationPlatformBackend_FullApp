package ds2020.dtos.PatientDTO;

import ds2020.dtos.CaregiverDTO.CaregiverDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.dtos.UserDTO.UserDetailsDTO;
import ds2020.entities.Prescription;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;
import java.util.List;

public class PatientDetailsDTO extends RepresentationModel<PatientDetailsDTO> {
    private Long patientId;
    private String name;
    private String address;
    private Date birthDate;
    private UserDTO user;
    private CaregiverDTO caregiver;
    private List<Prescription> prescriptionList;

    public PatientDetailsDTO() {
    }

    public PatientDetailsDTO(Long patientId, String name, String address, Date birthDate, UserDTO user, CaregiverDTO caregiver, List<Prescription> prescriptionList) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.user = user;
        this.caregiver = caregiver;
        this.prescriptionList = prescriptionList;
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

    public CaregiverDTO getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(CaregiverDTO caregiver) {
        this.caregiver = caregiver;
    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }
}
