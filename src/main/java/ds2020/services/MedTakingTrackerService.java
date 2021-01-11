package ds2020.services;

import ds2020.dtos.MedTrackerDTO.MedTrackerDTO;
import ds2020.dtos.builders.MedTrackerBuilder;
import ds2020.entities.MedicineTakingTracker;
import ds2020.repositories.MedicineTakingTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedTakingTrackerService {
    private final MedicineTakingTrackerRepository medicineTakingTrackerRepository;

    @Autowired
    public MedTakingTrackerService(MedicineTakingTrackerRepository medicineTakingTrackerRepository) {
        this.medicineTakingTrackerRepository = medicineTakingTrackerRepository;
    }

    public List<MedTrackerDTO> getTakenMedicine() {
        List<MedicineTakingTracker> medList = medicineTakingTrackerRepository.findAll();
        return medList.stream()
                .map(MedTrackerBuilder::toMedTrackerDTO)
                .collect(Collectors.toList());
    }

    public Long insertMedTracker(MedTrackerDTO medTrackerDTO) {
        MedicineTakingTracker med = MedTrackerBuilder.toMedicineTakingTracker(medTrackerDTO);
        med = medicineTakingTrackerRepository.save(med);
        return med.getTrackerId();
    }
}
