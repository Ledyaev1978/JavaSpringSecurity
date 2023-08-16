package by.mysecurityspringgit.repositories;

import by.mysecurityspringgit.entities.Authorities;
import by.mysecurityspringgit.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authorities,String> {
   Optional<Authorities> findUsersByUsername(String username);
}
