package ds2020.services;

import ds2020.dtos.DoctorDTO.DoctorDTO;
import ds2020.dtos.DoctorDTO.DoctorToBeInsertedDTO;
import ds2020.dtos.DoctorDTO.InsertedDoctorDTO;
import ds2020.dtos.PrescriptionDTO.PrescriptionWithMedicineDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.dtos.builders.DoctorBuilder;
import ds2020.dtos.builders.PrescriptionBuilder;
import ds2020.dtos.builders.UserBuilder;
import ds2020.entities.Doctor;
import ds2020.entities.Prescription;
import ds2020.repositories.DoctorRepository;
import ds2020.repositories.PrescriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final DoctorRepository doctorRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, PrescriptionRepository prescriptionRepository) {
        this.doctorRepository = doctorRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<DoctorDTO> findDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream()
                .map(DoctorBuilder::toDoctorDTO)
                .collect(Collectors.toList());
    }

    public Long insertDoctor(InsertedDoctorDTO doctorDTO, UserDTO userDTO) {
        Doctor doctor = DoctorBuilder.toDoctor(doctorDTO, userDTO);
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was inserted in db", doctor.getDoctorId());
        return doctor.getDoctorId();
    }

    public void deleteDoctor(Long doctorId) throws ResourceNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (!doctor.isPresent()) {
            throw new ResourceNotFoundException("Doctor with id " + doctorId + " not found.");
        }
        doctorRepository.deleteById(doctorId);
    }

    public DoctorDTO updateDoctor(DoctorToBeInsertedDTO doctorToBeInsertedDTO) throws ResourceNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(doctorToBeInsertedDTO.getDoctorId());
        if (!doctor.isPresent()) {
            throw new ResourceNotFoundException("Doctor not found.");
        }
        Doctor updatedDoctor = DoctorBuilder.toDoctor(doctorToBeInsertedDTO, UserBuilder.toUserDTO(doctor.get().getUser()));
        DoctorDTO updatedDoctorDTO = DoctorBuilder.toDoctorDTO(doctorRepository.save(updatedDoctor));
        return updatedDoctorDTO;
    }

    public PrescriptionWithMedicineDTO postPrescription(PrescriptionWithMedicineDTO prescriptionWithMedicineDTO) {
        Prescription prescription = PrescriptionBuilder.toPrescription(prescriptionWithMedicineDTO);
        return PrescriptionBuilder.toPrescriptionWithMedicineDTO(prescriptionRepository.save(prescription));
    }
}
