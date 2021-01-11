package ds2020.services;

import ds2020.dtos.ActivityDTO.ActivityDTO;
import ds2020.dtos.builders.ActivityBuilder;
import ds2020.entities.Activity;
import ds2020.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityDTO> getActivities() {
        List<Activity> activityList = activityRepository.findAll();
        return activityList.stream()
                .map(ActivityBuilder::toActivityDTO)
                .collect(Collectors.toList());
    }

    public Long insertActivity(ActivityDTO activityDTO) {
        Activity activity = ActivityBuilder.toActivity(activityDTO);
        activity = activityRepository.save(activity);
        return activity.getActivityId();
    }
}
