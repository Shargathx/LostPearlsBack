package ee.lostpearls.persistence.location;

import ee.lostpearls.persistence.county.County;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Override

    Optional<Location> findById(Integer locationId);



    Integer county(@NotNull County county);

    @Query("select l from Location l where l.id = :id and l.status = :status")
    List<Location> findLocationsBy(@Param("id") Integer id, @Param("status") String status);

    @Query("select l from Location l where l.status = :status")
    List<Location> findByStatus(@Param("status") String status);

    @Query("select l from Location l where l.user.id = :userId and l.status = :status")
    List<Location> findLocationByUserIdAndStatus(@Param("userId") Integer userId, @Param("status") String status);

    @Query("select (count(l) > 0) from Location l where l.locationName = ?1 and l.county.id = ?2")
    boolean locationExistsByNameAndCounty(String locationName, Integer id);
}