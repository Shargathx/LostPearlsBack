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
    List<Location> findByStatus(String status);

    @Query("select l from Location l where l.user.id = :userId and l.status = :status")
    List<Location> findLocationsByUserIdAndStatus(Integer userId, String status);

    @Query("select (count(l) > 0) from Location l where l.name = :locationName and l.county = :countyId")
    boolean locationExistsByLocationNameAndCountyId(String locationName, Integer countyId);

    @Query("select l from Location l where l.county.id = :countyId and l.status = :status")
    List<Location> findLocationsByCountyIdAndStatus(Integer countyId, String status);

    @Query("select l.id from Location l where l.county.id = :countyId and l.status = :status order by l.id")
    List<Integer> findLocationIdsByCountyIdAndStatus(Integer countyId, String status);

    @Query("select l.id from Location l where l.county.id = :countyId and l.status = :status and l.user.id != :userId order by l.id")
    List<Integer> findLocationIdsByCountyIdAndStatusAndExcludeUserId(Integer countyId, String status, Integer userId);



    @Query("SELECT l.id from Location l where l.user.id = :userId")
    List<Integer> findLocationCreatedByUser(Integer userId);


    @Query("select l from Location l where l.id = :locationId and l.status = :status")
    Optional<Location> findLocationBy(Integer locationId, String status);

}