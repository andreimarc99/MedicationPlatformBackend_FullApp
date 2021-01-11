package ds2020.dtos.DoctorDTO;

import ds2020.dtos.UserDTO.UserDTO;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

public class DoctorToBeInsertedDTO extends RepresentationModel<DoctorToBeInsertedDTO> {

    private Long doctorId;
    private String name;
    private String address;
    private Date birthDate;

    public DoctorToBeInsertedDTO() {
    }

    public DoctorToBeInsertedDTO(Long doctorId, String name, String address, Date birthDate) {
        this.doctorId = doctorId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
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

}
