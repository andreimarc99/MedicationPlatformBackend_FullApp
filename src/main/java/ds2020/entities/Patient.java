package ds2020.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", unique = true, nullable = false)
    private Long patientId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caregiver_id")
    private Caregiver caregiver;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<Prescription> prescriptions = new ArrayList<Prescription>();

    public Patient() {
    }

    public Patient(Long patientId, String name, String address, Date birthDate, User user, Caregiver caregiver) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.caregiver = caregiver;
        this.user = user;
    }

    public Patient(String name, String address, Date birthDate, User user, Caregiver caregiver) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.caregiver = caregiver;
        this.user = user;
    }

    public Patient(Long patientId, String name, String address, Date birthDate, User user, Caregiver caregiver, List<Prescription> prescriptions) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.caregiver = caregiver;
        this.user = user;
        this.prescriptions = prescriptions;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void addPrescription (Prescription p) {
        prescriptions.add(p);
    }
}
