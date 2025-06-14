package ee.lostpearls.persistence.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {


    @Query("select g from Game g where g.id = :cityId and g.status = :status")
    Optional<Game> findGameBy(Integer cityId, String status);

    @Query("select g.location.id from Game g where g.user.id = :userId order by g.location.id")
    List<Integer> findLocationIdsBy(Integer userId);

    @Query("select g from Game g where g.user.id = :userId and g.status in :statuses")
    List<Game> findGamesByUserIdAndStatusIn(Integer userId, List<String> statuses);

    @Query("select g from Game g where g.user.id = :userId and g.status = :status order by g.id")
    List<Game> findGamesBy(Integer userId, String status);

}
