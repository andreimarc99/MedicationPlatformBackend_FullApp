package ds2020.repositories;

import ds2020.entities.NotTakenMedTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotTakenMedTrackerRepository extends JpaRepository<NotTakenMedTracker, Long> {
}