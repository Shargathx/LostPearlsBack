package ee.lostpearls.persistence.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {


    @Query("select g from Game g where g.id = :cityId and g.status = :status")
    Optional<Game> findGameBy(Integer cityId, String status);
}
