package ee.lostpearls.persistence.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

  @Query("select a from Answer a where a.location.id = :locationId")
  List<Answer> findKeywordsBy(Integer locationId);

  Answer keyword(String keyword);
}