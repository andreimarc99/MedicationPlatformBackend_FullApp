package ds2020.dtos.ActivityDTO;

import java.util.Date;

public class ActivityDTO {

    private Long activityId;
    private Long patientId;
    private Date startTime;
    private Date endTime;
    private String activity;

    public ActivityDTO() {
    }

    public ActivityDTO(Long patientId, Date startTime, Date endTime, String activity) {
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public ActivityDTO(Long activityId, Long patientId, Date startTime, Date endTime, String activity) {
        this.activityId = activityId;
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
