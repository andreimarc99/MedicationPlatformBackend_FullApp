package ds2020.services;

import ds2020.dtos.PrescriptionDTO.PrescriptionDTO;
import ds2020.dtos.PrescriptionDTO.PrescriptionWithMedicineDTO;
import ds2020.dtos.builders.DoctorBuilder;
import ds2020.dtos.builders.MedicineBuilder;
import ds2020.dtos.builders.PatientBuilder;
import ds2020.dtos.builders.PrescriptionBuilder;
import ds2020.entities.Doctor;
import ds2020.entities.Medicine;
import ds2020.entities.Patient;
import ds2020.entities.Prescription;
import ds2020.repositories.DoctorRepository;
import ds2020.repositories.PatientRepository;
import ds2020.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<PrescriptionWithMedicineDTO> getPrescriptions() {
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        return prescriptionList.stream()
                .map(PrescriptionBuilder::toPrescriptionWithMedicineDTO)
                .collect(Collectors.toList());
    }

    public Long insertPrescription(PrescriptionDTO prescriptionDTO, Long patientId) {

        Optional<Patient> patient = patientRepository.findById(patientId);
        if (!patient.isPresent()) {
            throw new ResourceNotFoundException("Patient with id " + patientId + " does not exist.");
        }
        prescriptionDTO.setPatient(PatientBuilder.toPatientDTO(patient.get()));
        Prescription prescription = PrescriptionBuilder.toPrescription(prescriptionDTO);
        prescription = prescriptionRepository.save(prescription);
        return prescription.getPrescriptionId();
    }

    public PrescriptionWithMedicineDTO updatePrescription(PrescriptionDTO prescriptionDTO, Long prescripionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescripionId);
        if (!prescription.isPresent()) {
            throw new ResourceNotFoundException("Prescription with id " + prescriptionDTO.getPrescriptionId() + " not found.");
        }
        Prescription updatedPrescription = PrescriptionBuilder.toPrescription(prescripionId, prescriptionDTO, PatientBuilder.toPatientDTO(prescription.get().getPatient()), prescription.get().getMedicine().stream().map(MedicineBuilder::toMedicineDTO).collect(Collectors.toList()));

        updatedPrescription = prescriptionRepository.save(updatedPrescription);
        PrescriptionWithMedicineDTO updatedPrescriptionDTO = PrescriptionBuilder.toPrescriptionWithMedicineDTO(updatedPrescription,
                updatedPrescription.getMedicine().stream().map(MedicineBuilder::toMedicineDTO).collect(Collectors.toList()));
        return updatedPrescriptionDTO;
    }

    public void deletePrescription(Long prescriptionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);
        if (!prescription.isPresent()) {
            throw new ResourceNotFoundException("Prescription with id " + prescriptionId + " not found.");
        }
        /*List<Medicine> medicineList = prescription.get().getMedicine();
        for (Medicine m : medicineList) {
            m.getPrescriptions().remove(prescription);
        }*/

        Patient p = prescription.get().getPatient();
        p.getPrescriptions().remove(prescription);

        prescriptionRepository.deleteById(prescriptionId);
    }
}
