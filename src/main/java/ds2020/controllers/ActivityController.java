package ds2020.controllers;

import ds2020.dtos.ActivityDTO.ActivityDTO;
import ds2020.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/activity")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping()
    public ResponseEntity<List<ActivityDTO>> getActivities() {
        List<ActivityDTO> activities = activityService.getActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertActivity(@RequestBody ActivityDTO activityDTO) {
        Long id = activityService.insertActivity(activityDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
