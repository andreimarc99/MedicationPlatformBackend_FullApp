package ds2020.dtos.builders;

import ds2020.dtos.ActivityDTO.ActivityDTO;
import ds2020.entities.Activity;

public class ActivityBuilder {

    public static Activity toActivity(ActivityDTO activityDTO) {
        return new Activity(activityDTO.getActivityId(),
                activityDTO.getPatientId(),
                activityDTO.getStartTime(),
                activityDTO.getEndTime(),
                activityDTO.getActivity());
    }

    public static ActivityDTO toActivityDTO(Activity activity) {
        return new ActivityDTO(activity.getActivityId(),
                activity.getPatientId(),
                activity.getStartTime(),
                activity.getEndTime(),
                activity.getActivity());
    }
}
