package ds2020.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "med_tracker")
public class MedicineTakingTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_id", unique = true, nullable = false)
    private Long trackerId;

    @Column(name = "med_name")
    private String med_name;

    @Column(name = "prescription_id")
    private Long prescription_id;

    @Column(name = "patient_id")
    private Long patient_id;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    public MedicineTakingTracker(String med_name, Long prescription_id, Long patient_id, Date startTime, Date endTime) {
        this.med_name = med_name;
        this.prescription_id = prescription_id;
        this.patient_id = patient_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public MedicineTakingTracker(Long trackerId, String med_name, Long prescription_id, Long patient_id, Date startTime, Date endTime) {
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

    public String toString() {
        return prescription_id + " - patient " + patient_id + ": med - " + med_name + "; startTime: " + startTime + ", endTime: " + endTime;
    }
}
