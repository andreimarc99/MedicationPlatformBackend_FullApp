package ds2020.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", unique = true, nullable = false)
    private Long activityId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "activity", nullable = false)
    private String activity;

    public Activity() {
    }

    public Activity(Long patientId, Date startTime, Date endTime, String activity) {
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public Activity(Long activityId, Long patientId, Date startTime, Date endTime, String activity) {
        this.activityId = activityId;
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

    @Override
    public String toString() {
        return "Activity {" +
                "patientId = " + patientId +
                ", startTime = " + startTime +
                ", endTime = " + endTime +
                ", activity = '" + activity + '\'' +
                '}';
    }
}
