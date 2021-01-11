package ds2020.dtos.builders;

import ds2020.dtos.CaregiverDTO.CaregiverDTO;
import ds2020.dtos.CaregiverDTO.CaregiverToBeInsertedDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.entities.Caregiver;

public class CaregiverBuilder {
    public static Caregiver toCaregiver(CaregiverDTO caregiverDTO) {
        return new Caregiver(caregiverDTO.getCaregiverId(),
                caregiverDTO.getName(),
                caregiverDTO.getAddress(),
                caregiverDTO.getBirthDate(),
                UserBuilder.toUser(caregiverDTO.getUser()));
    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {
        return new CaregiverDTO(caregiver.getCaregiverId(),
                caregiver.getName(),
                caregiver.getAddress(),
                caregiver.getBirthDate(),
                UserBuilder.toUserDTO(caregiver.getUser()));
    }

    public static Caregiver toCaregiver(CaregiverToBeInsertedDTO caregiverDTO, UserDTO userDTO) {
        return new Caregiver(caregiverDTO.getCaregiverId(),
                caregiverDTO.getName(),
                caregiverDTO.getAddress(),
                caregiverDTO.getBirthDate(),
                UserBuilder.toUser(userDTO));
    }
}
