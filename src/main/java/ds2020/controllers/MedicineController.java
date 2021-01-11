package ds2020.controllers;

import ds2020.dtos.MedicineDTO.MedicineDTO;
import ds2020.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicineDTO>> getMedicine() {
        List<MedicineDTO> medicine = medicineService.findMedicine();
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertMedicine(@RequestBody MedicineDTO medicineDTO) {
        Long medicineId = medicineService.insertMedicine(medicineDTO);
        return new ResponseEntity<>(medicineId, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{medicineId}")
    public ResponseEntity<Long> deletePatient(@PathVariable Long medicineId) {
        try {
            medicineService.deleteMedicine(medicineId);
            return new ResponseEntity<>(medicineId, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Long> updateMedicine(@RequestBody MedicineDTO medicineDTO) {
        try {
            medicineService.updateMedicine(medicineDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
