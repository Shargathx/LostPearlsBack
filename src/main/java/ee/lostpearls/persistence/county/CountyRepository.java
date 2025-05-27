package ee.lostpearls.persistence.county;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountyRepository extends JpaRepository<County, Integer> {

    Optional<County> findCountyById(Integer id);
}