package ee.lostpearls.persistence.location;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Override
        // query siia??
    Optional<Location> findById(Integer locationId);



}