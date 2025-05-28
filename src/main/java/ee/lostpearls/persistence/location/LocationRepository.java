package ee.lostpearls.persistence.location;

import ee.lostpearls.persistence.county.County;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {



    Integer county(@NotNull County county);


    @Query("select l from Location l where l.status = :status")
    List<Location> findByStatus( String status);

    @Query("select l from Location l where l.user.id = :userId and l.status = :status")
    List<Location> findLocationByUserIdAndStatus(Integer userId, String status);

    @Query("select (count(l) > 0) from Location l where l.name = ?1 and l.county.id = ?2")
    boolean locationExistsByNameAndCounty(String name, Integer id);

    @Query("select l from Location l where l.county.id = :countyId and l.status = :status")
    List<Location> findRandomLocationByCounty(Integer countyId, String status);

    @Query("SELECT l.id from Location l where l.user.id = :userId")
    List<Integer> findLocationCreatedByUser(Integer userId);


    @Query("select l from Location l where l.id = :locationId and l.status = :status")
    Optional<Location> findLocationBy(Integer locationId, String status);
}