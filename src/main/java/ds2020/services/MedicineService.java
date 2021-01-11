package ds2020.services;

import ds2020.dtos.MedicineDTO.MedicineDTO;
import ds2020.dtos.builders.MedicineBuilder;
import ds2020.entities.Medicine;
import ds2020.repositories.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<MedicineDTO> findMedicine() {
        List<Medicine> medicineList = medicineRepository.findAll();
        return medicineList.stream()
                .map(MedicineBuilder::toMedicineDTO)
                .collect(Collectors.toList());
    }

    public MedicineDTO findById(Long medicineId) {
        Optional<Medicine> medicine = medicineRepository.findById(medicineId);
        if (!medicine.isPresent()) {
            throw new ResourceNotFoundException("Medicine with id " + medicineId + " not found.");
        }
        return MedicineBuilder.toMedicineDTO(medicine.get());
    }

    public Long insertMedicine(MedicineDTO medicineDTO) {
        Medicine medicine = MedicineBuilder.toMedicine(medicineDTO);
        medicine = medicineRepository.save(medicine);
        return medicine.getMedicineId();
    }

    public void deleteMedicine(Long medicineId) throws ResourceNotFoundException {
        Optional<Medicine> medicine = medicineRepository.findById(medicineId);
        if (!medicine.isPresent()) {
            throw new ResourceNotFoundException("Medicine with id " + medicineId + " not found.");
        }
        medicineRepository.deleteById(medicineId);
    }

    public MedicineDTO updateMedicine(MedicineDTO medicineDTO) throws ResourceNotFoundException {
        Optional<Medicine> medicine = medicineRepository.findById(medicineDTO.getMedicineId());
        if (!medicine.isPresent()) {
            throw new ResourceNotFoundException("Medicine with id " + medicineDTO.getMedicineId() + " not found.");
        }
        Medicine updatedMedicine = MedicineBuilder.toMedicine(medicineDTO);
        MedicineDTO updatedMedicineDTO = MedicineBuilder.toMedicineDTO(medicineRepository.save(updatedMedicine));
        return updatedMedicineDTO;
    }
}
