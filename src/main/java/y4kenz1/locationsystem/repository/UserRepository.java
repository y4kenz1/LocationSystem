package y4kenz1.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import y4kenz1.locationsystem.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}