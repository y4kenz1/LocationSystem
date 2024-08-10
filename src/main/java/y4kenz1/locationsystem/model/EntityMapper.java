package y4kenz1.locationsystem.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);

    LocationDTO toLocationDTO(Location location);
    Location toLocation(LocationDTO locationDTO);

    SharedLocationDTO toSharedLocationDTO(SharedLocation sharedLocation);
    SharedLocation toSharedLocation(SharedLocationDTO sharedLocationDTO);
}
