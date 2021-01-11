package ds2020.dtos.DoctorDTO;

import ds2020.dtos.UserDTO.UserDTO;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

public class DoctorDTO extends RepresentationModel<DoctorDTO> {
    private Long doctorId;
    private String name;
    private String address;
    private Date birthDate;
    private UserDTO user;

    public DoctorDTO() {
    }

    public DoctorDTO(Long doctorId, String name, String address, Date birthDate,UserDTO user) {
        this.doctorId = doctorId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.user = user;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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
}
