package ds2020.dtos.CaregiverDTO;

import ds2020.dtos.UserDTO.UserDTO;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;


public class CaregiverToBeInsertedDTO extends RepresentationModel<CaregiverToBeInsertedDTO> {

    private Long caregiverId;
    private String name;
    private String address;
    private Date birthDate;

    public CaregiverToBeInsertedDTO() {
    }

    public CaregiverToBeInsertedDTO(Long caregiverId, String name, String address, Date birthDate) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
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
}
