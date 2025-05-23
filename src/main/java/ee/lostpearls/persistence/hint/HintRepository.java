package ee.lostpearls.persistence.hint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HintRepository extends JpaRepository<Hint, Integer> {

    @Query("select h from Hint h where h.location.id = :locationId")
    List<Hint> findLocationsBy(Integer locationId);
}