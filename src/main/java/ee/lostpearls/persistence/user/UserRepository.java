package ee.lostpearls.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where (u.username = :username or u.email = :email) and u.password = :password and u.status = :status")
    Optional<User> findUserBy(String username, String email, String password, String status);


}
