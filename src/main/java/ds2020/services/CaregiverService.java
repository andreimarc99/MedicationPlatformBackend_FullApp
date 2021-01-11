package ds2020.services;

import ds2020.dtos.CaregiverDTO.CaregiverDTO;
import ds2020.dtos.CaregiverDTO.CaregiverToBeInsertedDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.dtos.builders.CaregiverBuilder;
import ds2020.dtos.builders.PatientBuilder;
import ds2020.dtos.builders.UserBuilder;
import ds2020.entities.Caregiver;
import ds2020.entities.Patient;
import ds2020.repositories.CaregiverRepository;
import ds2020.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CaregiverService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final CaregiverRepository caregiverRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository, PatientRepository patientRepository) {
        this.caregiverRepository = caregiverRepository;
        this.patientRepository = patientRepository;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> caregiverList = caregiverRepository.findAll();
        return caregiverList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public Long insertCaregiver(CaregiverToBeInsertedDTO caregiverDTO, UserDTO userDTO) {

        Caregiver caregiver = CaregiverBuilder.toCaregiver(caregiverDTO, userDTO);
        Caregiver insertedCaregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Caregiver with id {} was inserted in db", insertedCaregiver.getCaregiverId());
        return insertedCaregiver.getCaregiverId();
    }

    public void deleteCaregiver(Long caregiverId) throws ResourceNotFoundException {
        Optional<Caregiver> caregiver = caregiverRepository.findById(caregiverId);
        if (!caregiver.isPresent()) {
            throw new ResourceNotFoundException("Caregiver with id " + caregiverId + " not found.");
        }
        for (Patient p : caregiver.get().getPatients()) {
            p.setCaregiver(null);
            patientRepository.save(p);
        }
        caregiverRepository.deleteById(caregiverId);
    }

    public CaregiverDTO updateCaregiver(CaregiverToBeInsertedDTO caregiverToBeInsertedDTO) throws ResourceNotFoundException {
        Optional<Caregiver> caregiver = caregiverRepository.findById(caregiverToBeInsertedDTO.getCaregiverId());
        if (!caregiver.isPresent()) {
            throw new ResourceNotFoundException("Caregiver not found.");
        }
        Caregiver updatedCaregiver = CaregiverBuilder.toCaregiver(caregiverToBeInsertedDTO, UserBuilder.toUserDTO(caregiver.get().getUser()));
        CaregiverDTO updatedCaregiverDTO = CaregiverBuilder.toCaregiverDTO(caregiverRepository.save(updatedCaregiver));
        for (Patient p : caregiver.get().getPatients()) {
            p.setCaregiver(caregiver.get());
            patientRepository.save(p);
        }
        return updatedCaregiverDTO;
    }
}
