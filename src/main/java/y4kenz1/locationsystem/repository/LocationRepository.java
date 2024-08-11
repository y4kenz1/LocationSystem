package y4kenz1.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import y4kenz1.locationsystem.model.Location;

import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByOwnerId(Long ownerId);
}