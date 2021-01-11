package ds2020.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id", unique = true, nullable = false)
    private Long medicineId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "side_effects")
    private String sideEffects;

    @ManyToMany(mappedBy = "medicine")
    private List<Prescription> prescriptions = new ArrayList<>();

    public Medicine() {
    }

    public Medicine(Long medicineId, String name, String description, String sideEffects) {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.sideEffects = sideEffects;
    }

    public Medicine(String name, String description, String sideEffects) {
        this.name = name;
        this.description = description;
        this.sideEffects = sideEffects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
