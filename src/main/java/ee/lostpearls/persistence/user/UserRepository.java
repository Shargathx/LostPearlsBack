package ee.lostpearls.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {



    @Query("select u from User u where (u.username = :username or u.email = :email) and u.password = :password and u.status = :status")
    Optional<User> findUserBy(@Param("username") String username, @Param("email") String email, @Param("password") String password, @Param("status") String status);

    @Query("select (count(u) > 0) from User u where u.username = :username or u.email = :email")
    boolean userExistsBy(String username, String email);

    @Query("select (count(u) > 0) from User u where u.username = :username")
    boolean profileExistsBy(String username);

}
