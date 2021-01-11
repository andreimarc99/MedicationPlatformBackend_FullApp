package ds2020.controllers;

import ds2020.dtos.PatientDTO.InsertedPatientDTO;
import ds2020.dtos.PatientDTO.PatientDTO;
import ds2020.dtos.PatientDTO.PatientToBeInsertedDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.entities.Patient;
import ds2020.entities.UserRole;
import ds2020.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> patients = patientService.findPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patientDTO = patientService.findPatientById(id);
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertPatient(@RequestBody InsertedPatientDTO insertedPatientDTO) {
        UserDTO userDTO = new UserDTO(insertedPatientDTO.getUsername(), insertedPatientDTO.getPassword(), insertedPatientDTO.getRole());
        PatientToBeInsertedDTO patientToBeInsertedDTO = new PatientToBeInsertedDTO(insertedPatientDTO.getPatientId(),
                insertedPatientDTO.getName(),
                insertedPatientDTO.getAddress(),
                insertedPatientDTO.getBirthDate());
        Long caregiverId = insertedPatientDTO.getCaregiverId();
        Long insertedPatientId;
        try {
            insertedPatientId = patientService.insertPatient(patientToBeInsertedDTO, userDTO, caregiverId);
            return new ResponseEntity<>(insertedPatientId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity<Long> deletePatient(@PathVariable Long patientId) {
        try {
            patientService.deletePatient(patientId);
            return new ResponseEntity<>(patientId, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Long> updatePatient(@RequestBody PatientToBeInsertedDTO patientToBeInsertedDTO) {
        try {
            patientService.updatePatient(patientToBeInsertedDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Long> updateMyselfPatient(@RequestBody InsertedPatientDTO patientToBeInsertedDTO) {
        try {
            patientService.updateMyselfPatient(patientToBeInsertedDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
