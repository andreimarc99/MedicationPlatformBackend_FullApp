package ds2020.repositories;

import ds2020.entities.Activity;
import ds2020.entities.MedicineTakingTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineTakingTrackerRepository extends JpaRepository<MedicineTakingTracker, Long> {
}
