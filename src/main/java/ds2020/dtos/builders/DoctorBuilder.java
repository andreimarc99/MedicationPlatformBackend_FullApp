package ds2020.dtos.builders;

import ds2020.dtos.DoctorDTO.DoctorDTO;
import ds2020.dtos.DoctorDTO.DoctorToBeInsertedDTO;
import ds2020.dtos.DoctorDTO.InsertedDoctorDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.entities.Caregiver;
import ds2020.entities.Doctor;

public class DoctorBuilder {

    public DoctorBuilder() {
    }

    public static DoctorDTO toDoctorDTO(Doctor doctor) {
        return new DoctorDTO(doctor.getDoctorId(),
                doctor.getName(),
                doctor.getAddress(),
                doctor.getBirthDate(),
                UserBuilder.toUserDTO(doctor.getUser()));
    }

    public static Doctor toDoctor(DoctorDTO doctorDTO) {
        return new Doctor(doctorDTO.getDoctorId(),
                doctorDTO.getName(),
                doctorDTO.getAddress(),
                doctorDTO.getBirthDate(),
                UserBuilder.toUser(doctorDTO.getUser()));
    }

    public static Doctor toDoctor(DoctorToBeInsertedDTO doctorToBeInsertedDTO, UserDTO userDTO) {
        return new Doctor(doctorToBeInsertedDTO.getDoctorId(),
                doctorToBeInsertedDTO.getName(),
                doctorToBeInsertedDTO.getAddress(),
                doctorToBeInsertedDTO.getBirthDate(),
                UserBuilder.toUser(userDTO));
    }
    public static Doctor toDoctor(InsertedDoctorDTO d, UserDTO userDTO) {
        return new Doctor(d.getDoctorId(),
                d.getName(),
                d.getAddress(),
                d.getBirthDate(),
                UserBuilder.toUser(userDTO));
    }

    public static InsertedDoctorDTO toInsertedDoctorDTO(Doctor d) {
        return new InsertedDoctorDTO(d.getDoctorId(),
                d.getName(),
                d.getAddress(),
                d.getBirthDate(),
                d.getUser().getUsername(),
                d.getUser().getPassword(),
                d.getUser().getRole());
    }
}
