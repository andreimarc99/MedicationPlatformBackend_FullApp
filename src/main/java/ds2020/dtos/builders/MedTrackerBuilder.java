package ds2020.dtos.builders;

import ds2020.dtos.MedTrackerDTO.MedTrackerDTO;
import ds2020.entities.MedicineTakingTracker;

public class MedTrackerBuilder {

    public static MedicineTakingTracker toMedicineTakingTracker(MedTrackerDTO medTrackerDTO) {
        return new MedicineTakingTracker(medTrackerDTO.getTrackerId(),
                medTrackerDTO.getMed_name(),
                medTrackerDTO.getPrescription_id(),
                medTrackerDTO.getPatient_id(),
                medTrackerDTO.getStartTime(),
                medTrackerDTO.getEndTime());
    }

    public static MedTrackerDTO toMedTrackerDTO(MedicineTakingTracker medicineTakingTracker) {
        return new MedTrackerDTO(medicineTakingTracker.getTrackerId(),
                medicineTakingTracker.getMed_name(),
                medicineTakingTracker.getPrescription_id(),
                medicineTakingTracker.getPatient_id(),
                medicineTakingTracker.getStartTime(),
                medicineTakingTracker.getEndTime());
    }
}
