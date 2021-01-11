package ds2020.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id", unique = true, nullable = false)
    private Long prescriptionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "prescription_medication",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicine = new ArrayList<Medicine>();

    public Prescription() {
    }

    public Prescription(Long prescriptionId, Patient patient, Date startTime, Date endTime) {
        this.prescriptionId = prescriptionId;
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Prescription(Long prescriptionId, Patient patient, Date startTime, Date endTime, List<Medicine> medicine) {
        this.prescriptionId = prescriptionId;
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.medicine = medicine;
    }

    public Prescription(Patient patient,Date startTime, Date endTime, List<Medicine> medicine) {
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
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
        return prescriptionId + " - " + patient.getName() + ": " + getStartTime() + " - " + getEndTime();
    }
}
