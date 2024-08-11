package y4kenz1.locationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import y4kenz1.locationsystem.model.AccessType;
import y4kenz1.locationsystem.model.LocationDTO;
import y4kenz1.locationsystem.model.UserDTO;
import y4kenz1.locationsystem.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        LocationDTO createdLocation = locationService.createLocation(locationDTO.getOwnerId(), locationDTO.getName(), locationDTO.getAddress());
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @PostMapping("/{locationId}/share")
    public ResponseEntity<Void> shareLocation(@PathVariable Long locationId, @RequestParam Long friendUserId, @RequestParam AccessType accessType) {
        locationService.shareLocation(locationId, friendUserId, accessType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{locationId}/friends")
    public ResponseEntity<List<UserDTO>> getFriendsOnLocation(@PathVariable Long locationId) {
        List<UserDTO> friends = locationService.getFriendsOnLocation(locationId);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PutMapping("/{locationId}/friends/{friendUserId}/access")
    public ResponseEntity<Void> manageFriendAccess(@PathVariable Long locationId, @PathVariable Long friendUserId, @RequestParam AccessType newAccessType) {
        locationService.manageFriendAccess(locationId, friendUserId, newAccessType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getLocationsForUser(@RequestParam Long userId) {
        List<LocationDTO> locations = locationService.getLocationsByUserId(userId);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}

