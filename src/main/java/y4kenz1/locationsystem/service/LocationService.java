package y4kenz1.locationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y4kenz1.locationsystem.model.*;
import y4kenz1.locationsystem.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SharedLocationRepository sharedLocationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityMapper mapper;

    public LocationDTO createLocation(Long ownerId, String name, String address) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        Location location = new Location();
        location.setName(name);
        location.setAddress(address);
        location.setOwner(owner);
        locationRepository.save(location);
        return mapper.toLocationDTO(location);
    }

    public void shareLocation(Long locationId, Long friendUserId, AccessType accessType) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        User friend = userRepository.findById(friendUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        SharedLocation sharedLocation = new SharedLocation();
        sharedLocation.setLocation(location);
        sharedLocation.setUser(friend);
        sharedLocation.setAccessType(accessType);
        sharedLocationRepository.save(sharedLocation);
    }

    public List<UserDTO> getFriendsOnLocation(Long locationId) {
        List<SharedLocation> sharedLocations = sharedLocationRepository.findByLocationId(locationId);
        return sharedLocations.stream()
                .map(SharedLocation::getUser)
                .map(mapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public void manageFriendAccess(Long locationId, Long friendUserId, AccessType newAccessType) {
        SharedLocation sharedLocation = sharedLocationRepository.findByLocationId(locationId).stream()
                .filter(sl -> sl.getUser().getId().equals(friendUserId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Shared location not found"));
        sharedLocation.setAccessType(newAccessType);
        sharedLocationRepository.save(sharedLocation);
    }

    public List<LocationDTO> getLocationsByUserId(Long userId) {
        List<Location> locations = locationRepository.findByOwnerId(userId);
        List<LocationDTO> locationDTOs = locations.stream()
                .map(mapper::toLocationDTO)
                .collect(Collectors.toList());

        List<SharedLocation> sharedLocations = sharedLocationRepository.findByUserId(userId);
        locationDTOs.addAll(sharedLocations.stream()
                .map(SharedLocation::getLocation)
                .map(mapper::toLocationDTO)
                .toList());
        return locationDTOs;
    }
}