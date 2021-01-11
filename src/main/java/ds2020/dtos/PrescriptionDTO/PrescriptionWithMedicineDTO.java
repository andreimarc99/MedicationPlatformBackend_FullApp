package ds2020.dtos.PrescriptionDTO;

import ds2020.dtos.MedicineDTO.MedicineDTO;
import ds2020.dtos.PatientDTO.PatientDTO;

import java.util.Date;
import java.util.List;

public class PrescriptionWithMedicineDTO {

    private Long prescriptionId;
    private PatientDTO patient;
    private Date startTime;
    private Date endTime;
    private List<MedicineDTO> medicine;

    public PrescriptionWithMedicineDTO() {
    }

    public PrescriptionWithMedicineDTO(Long prescriptionId, PatientDTO patient, Date startTime, Date endTime, List<MedicineDTO> medicine) {
        this.prescriptionId = prescriptionId;
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.medicine = medicine;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<MedicineDTO> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<MedicineDTO> medicine) {
        this.medicine = medicine;
    }
}
