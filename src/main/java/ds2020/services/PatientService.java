package ds2020.services;

import ds2020.dtos.PatientDTO.InsertedPatientDTO;
import ds2020.dtos.PatientDTO.PatientDTO;
import ds2020.dtos.PatientDTO.PatientToBeInsertedDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.dtos.builders.CaregiverBuilder;
import ds2020.dtos.builders.PatientBuilder;
import ds2020.dtos.builders.UserBuilder;
import ds2020.entities.Caregiver;
import ds2020.entities.Patient;
import ds2020.entities.User;
import ds2020.repositories.CaregiverRepository;
import ds2020.repositories.PatientRepository;
import ds2020.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository, CaregiverRepository caregiverRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.caregiverRepository = caregiverRepository;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(Long id) {
        Optional<Patient> p = patientRepository.findById(id);
        if (!p.isPresent()) {
            throw new ResourceNotFoundException("Patient with id " + id + " not found.");
        }
        return PatientBuilder.toPatientDTO(p.get());
    }

    public Long insertPatient(PatientToBeInsertedDTO patientToBeInsertedDTO, UserDTO userDTO,
                              Long caregiverId) {
        /*User user = userRepository.findById(userDTO.getUsername()).orElse(null);
        if (user != null) {
            throw new DuplicateKeyException("User " + userDTO.getUsername() + " already exists.");
        }
        User userToBeInserted = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
        userToBeInserted = userRepository.save(userToBeInserted);
        */
        Optional<Caregiver> caregiver = caregiverRepository.findById(caregiverId);
        if (!caregiver.isPresent()) {
            throw new ResourceNotFoundException("Caregiver with id " + caregiverId + " not found");
        }

        Patient patient = PatientBuilder.toPatient(patientToBeInsertedDTO, UserBuilder.toUser(userDTO), CaregiverBuilder.toCaregiverDTO(caregiver.get()));
        Patient insertedPatient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", insertedPatient.getPatientId());
        return insertedPatient.getPatientId();
    }

    public void deletePatient(Long patientId) throws ResourceNotFoundException {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (!patient.isPresent()) {
            throw new ResourceNotFoundException("Patient with id " + patientId + " not found.");
        }
        patientRepository.deleteById(patientId);
    }

    public PatientDTO updatePatient(PatientToBeInsertedDTO patientToBeInsertedDTO) throws ResourceNotFoundException {
        Optional<Patient> patient = patientRepository.findById(patientToBeInsertedDTO.getPatientId());
        if (!patient.isPresent()) {
            throw new ResourceNotFoundException("Patient not found.");
        }
        Patient updatedPatient = PatientBuilder.toPatient(patientToBeInsertedDTO, patient.get().getUser(),
                CaregiverBuilder.toCaregiverDTO(patient.get().getCaregiver()));
        PatientDTO updatedPatientDTO = PatientBuilder.toPatientDTO(patientRepository.save(updatedPatient));
        return updatedPatientDTO;
    }

    public InsertedPatientDTO updateMyselfPatient(InsertedPatientDTO patientToBeInsertedDTO) throws ResourceNotFoundException {
        Optional<Patient> patient = patientRepository.findById(patientToBeInsertedDTO.getPatientId());
        if (!patient.isPresent()) {
            throw new ResourceNotFoundException("Patient not found.");
        }
        Optional<User> user = userRepository.findById(patientToBeInsertedDTO.getUsername());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User not found.");
        }
        Optional<Caregiver> caregiver = caregiverRepository.findById(patientToBeInsertedDTO.getCaregiverId());
        if (!caregiver.isPresent()) {
            throw new ResourceNotFoundException("Caregiver not found.");
        }
        Patient updatedPatient = PatientBuilder.toPatient(patientToBeInsertedDTO, user.get(), caregiver.get());
        InsertedPatientDTO updatedPatientDTO = PatientBuilder.toInsertedPatientDTO(patientRepository.save(updatedPatient));
        return updatedPatientDTO;
    }
}
