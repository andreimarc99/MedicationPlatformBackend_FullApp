package ds2020.controllers;

import ds2020.dtos.CaregiverDTO.CaregiverDTO;
import ds2020.dtos.CaregiverDTO.CaregiverToBeInsertedDTO;
import ds2020.dtos.CaregiverDTO.InsertedCaregiverDTO;
import ds2020.dtos.PatientDTO.PatientToBeInsertedDTO;
import ds2020.dtos.UserDTO.UserDTO;
import ds2020.entities.UserRole;
import ds2020.services.CaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/caregiver")
public class CaregiverController {
    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> caregivers = caregiverService.findCaregivers();
        return new ResponseEntity<>(caregivers, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertCaregiver(@RequestBody InsertedCaregiverDTO caregiverDTO) {
        UserDTO userDTO = new UserDTO(caregiverDTO.getUsername(), caregiverDTO.getPassword(), caregiverDTO.getUserRole());
        CaregiverToBeInsertedDTO caregiverToBeInsertedDTO =
                new CaregiverToBeInsertedDTO(caregiverDTO.getCaregiverId(),
                        caregiverDTO.getName(),
                        caregiverDTO.getAddress(),
                        caregiverDTO.getBirthDate());
        Long insertedCaregiverId;
        try {
            insertedCaregiverId = caregiverService.insertCaregiver(caregiverToBeInsertedDTO, userDTO);
            return new ResponseEntity<>(insertedCaregiverId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{caregiverId}")
    public ResponseEntity<Long> deleteCaregiver(@PathVariable Long caregiverId) {
        try {
            caregiverService.deleteCaregiver(caregiverId);
            return new ResponseEntity<>(caregiverId, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(caregiverId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Long> updateCaregiver(@RequestBody CaregiverToBeInsertedDTO caregiverToBeInsertedDTO) {
        try {
            caregiverService.updateCaregiver(caregiverToBeInsertedDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
