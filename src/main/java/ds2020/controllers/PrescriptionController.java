package ds2020.controllers;

import ds2020.dtos.PrescriptionDTO.PrescriptionDTO;
import ds2020.dtos.PrescriptionDTO.PrescriptionWithMedicineDTO;
import ds2020.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping()
    public ResponseEntity<List<PrescriptionWithMedicineDTO>> getPrescriptions() {
        List<PrescriptionWithMedicineDTO> prescriptionDTOS = prescriptionService.getPrescriptions();
        return new ResponseEntity<>(prescriptionDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/{patientId}")
    public ResponseEntity<Long> insertPrescription(@RequestBody PrescriptionDTO prescriptionDTO, @PathVariable Long patientId) {
        Long prescriptionId = prescriptionService.insertPrescription(prescriptionDTO, patientId);
        return new ResponseEntity<>(prescriptionId, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{prescriptionId}")
    public ResponseEntity<Long> deletePrescription(@PathVariable Long prescriptionId) {
        try {
            prescriptionService.deletePrescription(prescriptionId);
            return new ResponseEntity<>(prescriptionId, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{prescriptionId}")
    public ResponseEntity<Long> updatePrescription(@RequestBody PrescriptionDTO prescriptionDTO, @PathVariable Long prescriptionId) {
        try {
            PrescriptionWithMedicineDTO updatedPrescriptionDTO = prescriptionService.updatePrescription(prescriptionDTO, prescriptionId);
            return new ResponseEntity<>(prescriptionDTO.getPrescriptionId(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
