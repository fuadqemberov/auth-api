package az.company.auth_api.repository;

import az.company.auth_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String name);
    Optional<User> findById(Long id);
}
