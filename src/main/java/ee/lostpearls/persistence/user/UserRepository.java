package ee.lostpearls.persistence.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where (u.username = :username or u.email = :email) and u.password = :password and u.status = :status")
    Optional<User> findUserBy(String username, String email, String password, String status);

    @Query("select (count(u) > 0) from User u where u.username = ?1 or u.email = ?2")
    boolean userExistsBy(String username, String email);

    @Query("select (count(u) > 0) from User u where u.username = :username")
    boolean profileExistsBy(String username);

}
