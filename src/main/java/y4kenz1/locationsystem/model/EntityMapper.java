package y4kenz1.locationsystem.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(source = "id", target = "id")
    UserDTO toUserDTO(User user);

    @Mapping(source = "id", target = "id")
    User toUser(UserDTO userDTO);

    @Mapping(source = "owner.id", target = "ownerId")
    LocationDTO toLocationDTO(Location location);

    @Mapping(source = "ownerId", target = "owner.id")
    Location toLocation(LocationDTO locationDTO);

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "user.id", target = "userId")
    SharedLocationDTO toSharedLocationDTO(SharedLocation sharedLocation);

    @Mapping(source = "locationId", target = "location.id")
    @Mapping(source = "userId", target = "user.id")
    SharedLocation toSharedLocation(SharedLocationDTO sharedLocationDTO);

    default SharedLocation toSharedLocation(SharedLocationDTO sharedLocationDTO, Location location, User user) {
        if (sharedLocationDTO == null) {
            return null;
        }

        SharedLocation sharedLocation = new SharedLocation();
        sharedLocation.setId(sharedLocationDTO.getId());
        sharedLocation.setLocation(location); // Location is manually set
        sharedLocation.setUser(user); // User is manually set
        sharedLocation.setAccessType(sharedLocationDTO.getAccessType());

        return sharedLocation;
    }
}