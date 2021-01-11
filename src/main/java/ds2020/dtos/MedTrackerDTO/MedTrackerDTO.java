package ds2020.dtos.MedTrackerDTO;

import java.util.Date;

public class MedTrackerDTO {

    private Long trackerId;
    private String med_name;
    private Long prescription_id;
    private Long patient_id;
    private Date startTime;
    private Date endTime;

    public MedTrackerDTO() {
    }
    public MedTrackerDTO(Long trackerId, String med_name, Long prescription_id, Long patient_id, Date startTime, Date endTime) {
        this.trackerId = trackerId;
        this.med_name = med_name;
        this.prescription_id = prescription_id;
        this.patient_id = patient_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public Long getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Long prescription_id) {
        this.prescription_id = prescription_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
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
