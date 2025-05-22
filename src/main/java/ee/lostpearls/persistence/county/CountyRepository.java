package ee.lostpearls.persistence.county;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County, Integer> {
}