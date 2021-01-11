package ds2020.entities;

import javax.persistence.*;

@Entity
@Table(name = "not_taken_med_tracker")
public class NotTakenMedTracker {

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

    public NotTakenMedTracker(String med_name, Long prescription_id, Long patient_id) {
        this.med_name = med_name;
        this.prescription_id = prescription_id;
        this.patient_id = patient_id;
    }

    public NotTakenMedTracker(Long trackerId, String med_name, Long prescription_id, Long patient_id) {
        this.trackerId = trackerId;
        this.med_name = med_name;
        this.prescription_id = prescription_id;
        this.patient_id = patient_id;
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
}
