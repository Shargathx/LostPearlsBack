package ee.lostpearls.persistence.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
  @Query("select a from Answer a where a.location.id = ?1")

  //selle tegin koeraga query->find collection
  List<Answer> findKeywordByLocation(Integer id);
}