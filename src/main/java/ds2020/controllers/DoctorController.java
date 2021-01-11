package ds2020.controllers;

import ds2020.dtos.DoctorDTO.DoctorDTO;
import ds2020.dtos.DoctorDTO.DoctorToBeInsertedDTO;
import ds2020.dtos.DoctorDTO.InsertedDoctorDTO;
import ds2020.dtos.PrescriptionDTO.PrescriptionWithMedicineDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.entities.UserRole;
import ds2020.services.DoctorService;
import ds2020.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final UserService userService;

    @Autowired
    public DoctorController(DoctorService doctorService, UserService userService) {
        this.doctorService = doctorService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        List<DoctorDTO> doctors = doctorService.findDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertDoctor(@RequestBody InsertedDoctorDTO doctorDTO) {
        Long insertedDoctorId;
        UserDTO userDTO = new UserDTO(doctorDTO.getUsername(), doctorDTO.getPassword(), doctorDTO.getRole());
        try {
            insertedDoctorId = doctorService.insertDoctor(doctorDTO, userDTO);
            return new ResponseEntity<>(insertedDoctorId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Long> deleteDoctor(@PathVariable Long doctorId) {
        try {
            doctorService.deleteDoctor(doctorId);
            return new ResponseEntity<>(doctorId, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(doctorId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Long> updateDoctor(@RequestBody DoctorToBeInsertedDTO doctorToBeInsertedDTO) {
        try {
            doctorService.updateDoctor(doctorToBeInsertedDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/prescription")
    public PrescriptionWithMedicineDTO postPrescription(@RequestBody PrescriptionWithMedicineDTO prescriptionWithMedicineDTO) {
            doctorService.postPrescription(prescriptionWithMedicineDTO);
            return prescriptionWithMedicineDTO;
    }
}
