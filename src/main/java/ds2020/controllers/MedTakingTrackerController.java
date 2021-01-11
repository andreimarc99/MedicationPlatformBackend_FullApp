package ds2020.controllers;

import ds2020.dtos.ActivityDTO.ActivityDTO;
import ds2020.dtos.MedTrackerDTO.MedTrackerDTO;
import ds2020.services.MedTakingTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/taken_med_tracker")
public class MedTakingTrackerController {

    private final MedTakingTrackerService medTakingTrackerService;

    @Autowired
    public MedTakingTrackerController(MedTakingTrackerService medTakingTrackerService) {
        this.medTakingTrackerService = medTakingTrackerService;
    }

    @GetMapping()
    public ResponseEntity<List<MedTrackerDTO>> getTakenMeds() {
        List<MedTrackerDTO> meds = medTakingTrackerService.getTakenMedicine();
        return new ResponseEntity<>(meds, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertTakenMed(@RequestBody MedTrackerDTO medTrackerDTO) {
        Long id = medTakingTrackerService.insertMedTracker(medTrackerDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
