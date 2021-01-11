package ds2020.dtos.PrescriptionDTO;

import ds2020.dtos.DoctorDTO.DoctorDTO;
import ds2020.dtos.PatientDTO.PatientDTO;

import java.util.Date;

public class PrescriptionDTO {
    private Long prescriptionId;
    private PatientDTO patient;
    private Date startTime;
    private Date endTime;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(Long prescriptionId, PatientDTO patient, Date startTime, Date endTime) {
        this.prescriptionId = prescriptionId;
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
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
}
