package ee.lostpearls.persistence.keyword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

  @Query("select k from Keyword k where k.location.id = :locationId order by k.id")
  List<Keyword> findKeywordsBy(Integer locationId);
}