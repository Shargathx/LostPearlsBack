package ee.lostpearls.persistence.location;

import ee.lostpearls.persistence.county.County;
import ee.lostpearls.status.LocationStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Override
        // query siia??
    Optional<Location> findById(Integer locationId);



    Integer county(@NotNull County county);

    @Query("select l from Location l where l.id = :id and l.status = :status")
    List<Location> findLocationsBy(@Param("id") Integer id, @Param("status") String status);


}