package by.mysecurityspringgit.repositories;

import by.mysecurityspringgit.entities.Authorities;
import by.mysecurityspringgit.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,String>
{
    Optional<Users> findUsersByUsername(String username);

}
