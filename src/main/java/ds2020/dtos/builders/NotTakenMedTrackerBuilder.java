package ds2020.dtos.builders;

import ds2020.dtos.MedTrackerDTO.MedTrackerDTO;
import ds2020.dtos.MedTrackerDTO.NotTakenMedTrackerDTO;
import ds2020.entities.MedicineTakingTracker;
import ds2020.entities.NotTakenMedTracker;

public class NotTakenMedTrackerBuilder {
    public static NotTakenMedTracker toNotTakenMedTracker(NotTakenMedTrackerDTO medTrackerDTO) {
        return new NotTakenMedTracker(medTrackerDTO.getTrackerId(),
                medTrackerDTO.getMed_name(),
                medTrackerDTO.getPrescription_id(),
                medTrackerDTO.getPatient_id());
    }

    public static NotTakenMedTrackerDTO toNotTakenMedTrackerDTO(NotTakenMedTracker medicineTakingTracker) {
        return new NotTakenMedTrackerDTO(medicineTakingTracker.getTrackerId(),
                medicineTakingTracker.getMed_name(),
                medicineTakingTracker.getPrescription_id(),
                medicineTakingTracker.getPatient_id());
    }
}
