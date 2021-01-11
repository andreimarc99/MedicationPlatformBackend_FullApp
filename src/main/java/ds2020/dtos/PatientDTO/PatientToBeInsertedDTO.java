package ds2020.dtos.PatientDTO;

import java.sql.Date;

public class PatientToBeInsertedDTO {

    private Long patientId;
    private String name;
    private String address;
    private Date birthDate;

    public PatientToBeInsertedDTO(Long patientId, String name, String address, Date birthDate) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
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
}
