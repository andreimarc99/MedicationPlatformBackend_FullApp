package ds2020.repositories;

import ds2020.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository  extends JpaRepository<Prescription, Long> {
}
