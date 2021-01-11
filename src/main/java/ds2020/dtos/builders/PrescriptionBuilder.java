package ds2020.dtos.builders;

import ds2020.dtos.DoctorDTO.DoctorDTO;
import ds2020.dtos.MedicineDTO.MedicineDTO;
import ds2020.dtos.PatientDTO.PatientDTO;
import ds2020.dtos.PrescriptionDTO.PrescriptionDTO;
import ds2020.dtos.PrescriptionDTO.PrescriptionWithMedicineDTO;
import ds2020.entities.Medicine;
import ds2020.entities.Prescription;

import java.util.List;
import java.util.stream.Collectors;

public class PrescriptionBuilder {

    public static PrescriptionDTO toPrescriptionDTO(Prescription prescription) {
        return new PrescriptionDTO(prescription.getPrescriptionId(),
                PatientBuilder.toPatientDTO(prescription.getPatient()),
                prescription.getStartTime(),
                prescription.getEndTime());
    }

    public static PrescriptionWithMedicineDTO toPrescriptionWithMedicineDTO(Prescription prescription, List<MedicineDTO> medicineDTOS) {
        return new PrescriptionWithMedicineDTO(prescription.getPrescriptionId(),
                PatientBuilder.toPatientDTO(prescription.getPatient()),
                prescription.getStartTime(),
                prescription.getEndTime(),
                medicineDTOS);
    }

    public static Prescription toPrescription(PrescriptionDTO prescription) {
        return new Prescription(prescription.getPrescriptionId(),
                PatientBuilder.toPatient(prescription.getPatient()),
                prescription.getStartTime(),
                prescription.getEndTime());
    }

    public static Prescription toPrescription(Long prescriptionId, PrescriptionDTO prescription, PatientDTO patientDTO, List<MedicineDTO> medicineDTOS) {
        return new Prescription(prescriptionId,
                PatientBuilder.toPatient(patientDTO),
                prescription.getStartTime(),
                prescription.getEndTime(),
                medicineDTOS.stream().map(MedicineBuilder::toMedicine).collect(Collectors.toList()));
    }

    public static Prescription toPrescription(PrescriptionWithMedicineDTO prescription) {
        return new Prescription(prescription.getPrescriptionId(),
                PatientBuilder.toPatient(prescription.getPatient()),
                prescription.getStartTime(),
                prescription.getEndTime(),
                prescription.getMedicine().stream().map(MedicineBuilder::toMedicine).collect(Collectors.toList()));
    }

    public static PrescriptionWithMedicineDTO toPrescriptionWithMedicineDTO(Prescription prescription) {
        return new PrescriptionWithMedicineDTO(prescription.getPrescriptionId(),
                PatientBuilder.toPatientDTO(prescription.getPatient()),
                prescription.getStartTime(),
                prescription.getEndTime(),
                prescription.getMedicine().stream().map(MedicineBuilder::toMedicineDTO).collect(Collectors.toList()));
    }
}
