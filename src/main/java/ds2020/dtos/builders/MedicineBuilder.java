package ds2020.dtos.builders;

import ds2020.dtos.MedicineDTO.MedicineDTO;
import ds2020.entities.Medicine;

public class MedicineBuilder {

    public static MedicineDTO toMedicineDTO(Medicine medicine) {
        return new MedicineDTO(medicine.getMedicineId(),
                medicine.getName(),
                medicine.getDescription(),
                medicine.getSideEffects());
    }

    public static Medicine toMedicine(MedicineDTO medicineDTO) {
        return new Medicine(medicineDTO.getMedicineId(),
                medicineDTO.getName(),
                medicineDTO.getDescription(),
                medicineDTO.getSideEffects());
    }

}
