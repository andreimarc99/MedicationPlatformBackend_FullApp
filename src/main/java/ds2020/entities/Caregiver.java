package ds2020.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "caregiver")
public class Caregiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caregiver_id", unique = true, nullable = false)
    private Long caregiverId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = false)
    @JoinColumn(name = "caregiver_id")
    private List<Patient> patients = new ArrayList<>();

    public Caregiver() {
    }

    public Caregiver(Long id) {
        this.caregiverId = id;
    }

    public Caregiver(Long id, User user) {
        this.caregiverId = id;
        this.user = user;
    }

    public Caregiver(Long caregiverId, String name, String address, Date birthDate, User user) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.user = user;
    }

    public Long getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(Long caregiverId) {
        this.caregiverId = caregiverId;
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
