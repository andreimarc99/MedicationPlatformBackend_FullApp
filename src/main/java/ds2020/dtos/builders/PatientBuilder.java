package ds2020.dtos.builders;


import ds2020.dtos.CaregiverDTO.CaregiverDTO;
import ds2020.dtos.PatientDTO.InsertedPatientDTO;
import ds2020.dtos.PatientDTO.PatientDTO;
import ds2020.dtos.PatientDTO.PatientDetailsDTO;
import ds2020.dtos.PatientDTO.PatientToBeInsertedDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.entities.Caregiver;
import ds2020.entities.Patient;
import ds2020.entities.User;
import ds2020.entities.UserRole;

public class PatientBuilder {

    public PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {
        if (patient.getCaregiver() == null) {
            return new PatientDTO(patient.getPatientId(),
                    patient.getName(),
                    patient.getAddress(),
                    patient.getBirthDate(),
                    UserBuilder.toUserDTO(patient.getUser()));
        }
        return new PatientDTO(patient.getPatientId(),
                patient.getName(),
                patient.getAddress(),
                patient.getBirthDate(),
                UserBuilder.toUserDTO(patient.getUser()),
                CaregiverBuilder.toCaregiverDTO(patient.getCaregiver()));
    }

    public static PatientDetailsDTO toPatientDetailsDTO(Patient patient) {
        return new PatientDetailsDTO(patient.getPatientId(),
                patient.getName(),
                patient.getAddress(),
                patient.getBirthDate(),
                UserBuilder.toUserDTO(patient.getUser()),
                CaregiverBuilder.toCaregiverDTO(patient.getCaregiver()),
                patient.getPrescriptions());
    }

    public static Patient toPatient(PatientDetailsDTO patient) {
        return new Patient(patient.getPatientId(),
                patient.getName(),
                patient.getAddress(),
                patient.getBirthDate(),
                UserBuilder.toUser(patient.getUser()),
                CaregiverBuilder.toCaregiver(patient.getCaregiver()),
                patient.getPrescriptionList());
    }

    public static Patient toPatient(PatientDTO patient) {
        return new Patient(patient.getPatientId(),
                patient.getName(),
                patient.getAddress(),
                patient.getBirthDate(),
                UserBuilder.toUser(patient.getUser()),
                CaregiverBuilder.toCaregiver(patient.getCaregiverDTO()));
    }

    public static Patient toPatient(PatientToBeInsertedDTO patientToBeInsertedDTO, User user, CaregiverDTO caregiverDTO) {
        return new Patient(patientToBeInsertedDTO.getPatientId(),
                patientToBeInsertedDTO.getName(),
                patientToBeInsertedDTO.getAddress(),
                patientToBeInsertedDTO.getBirthDate(),
                user,
                CaregiverBuilder.toCaregiver(caregiverDTO));
    }

    public static PatientToBeInsertedDTO toPatientToBeInsertedDTO(Patient patient) {
        return new PatientToBeInsertedDTO(patient.getPatientId(),
                patient.getName(),
                patient.getAddress(),
                patient.getBirthDate());
    }

    public static Patient toPatient(InsertedPatientDTO p, User user, Caregiver caregiver) {
        return new Patient(p.getPatientId(),
                p.getName(),
                p.getAddress(),
                p.getBirthDate(),
                user,
                caregiver);
    }

    public static InsertedPatientDTO toInsertedPatientDTO(Patient p) {
        return new InsertedPatientDTO(p.getPatientId(),
                p.getName(),
                p.getAddress(),
                p.getBirthDate(),
                p.getUser().getUsername(),
                p.getUser().getPassword(),
                UserRole.PATIENT,
                p.getCaregiver().getCaregiverId());
    }
}
