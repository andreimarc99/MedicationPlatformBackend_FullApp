package ds2020.controllers;


import ds2020.dtos.MedTrackerDTO.MedTrackerDTO;
import ds2020.dtos.MedTrackerDTO.NotTakenMedTrackerDTO;
import ds2020.services.MedTakingTrackerService;
import ds2020.services.NotTakenMedTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/not_taken_med_tracker")
public class NotTakenMedTrackerController {

    private final NotTakenMedTrackerService notTakenMedTrackerService;

    @Autowired
    public NotTakenMedTrackerController(NotTakenMedTrackerService notTakenMedTrackerService) {
        this.notTakenMedTrackerService = notTakenMedTrackerService;
    }

    @GetMapping()
    public ResponseEntity<List<NotTakenMedTrackerDTO>> getNotTakenMeds() {
        List<NotTakenMedTrackerDTO> meds = notTakenMedTrackerService.getNotTakenMedicine();
        return new ResponseEntity<>(meds, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertNotTakenMed(@RequestBody NotTakenMedTrackerDTO notTakenMedTrackerDTO) {
        Long id = notTakenMedTrackerService.insertNotTakenMedTracker(notTakenMedTrackerDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}

