package y4kenz1.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import y4kenz1.locationsystem.model.SharedLocation;

import java.util.List;

public interface SharedLocationRepository extends JpaRepository<SharedLocation, Long> {
    List<SharedLocation> findByUserId(Long userId);
    List<SharedLocation> findByLocationId(Long locationId);
}