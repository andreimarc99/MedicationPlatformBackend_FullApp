package ds2020.services;

import ds2020.dtos.MedTrackerDTO.MedTrackerDTO;
import ds2020.dtos.MedTrackerDTO.NotTakenMedTrackerDTO;
import ds2020.dtos.builders.MedTrackerBuilder;
import ds2020.dtos.builders.NotTakenMedTrackerBuilder;
import ds2020.entities.MedicineTakingTracker;
import ds2020.entities.NotTakenMedTracker;
import ds2020.repositories.MedicineTakingTrackerRepository;
import ds2020.repositories.NotTakenMedTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotTakenMedTrackerService {
    private final NotTakenMedTrackerRepository notTakenMedTrackerRepository;

    @Autowired
    public NotTakenMedTrackerService(NotTakenMedTrackerRepository notTakenMedTrackerRepository) {
        this.notTakenMedTrackerRepository = notTakenMedTrackerRepository;
    }

    public List<NotTakenMedTrackerDTO> getNotTakenMedicine() {
        List<NotTakenMedTracker> medList = notTakenMedTrackerRepository.findAll();
        return medList.stream()
                .map(NotTakenMedTrackerBuilder::toNotTakenMedTrackerDTO)
                .collect(Collectors.toList());
    }

    public Long insertNotTakenMedTracker(NotTakenMedTrackerDTO notTakenMedTrackerDTO) {
        NotTakenMedTracker med = NotTakenMedTrackerBuilder.toNotTakenMedTracker(notTakenMedTrackerDTO);
        med = notTakenMedTrackerRepository.save(med);
        return med.getTrackerId();
    }
}
