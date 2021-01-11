package ds2020.dtos.MedicineDTO;

import java.util.List;

public class MedicineDTO {
    private Long medicineId;
    private String name;
    private String description;
    private String sideEffects;

    public MedicineDTO() {
    }

    public MedicineDTO(Long medicineId, String name, String description, String sideEffects) {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.sideEffects = sideEffects;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
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

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }
}
