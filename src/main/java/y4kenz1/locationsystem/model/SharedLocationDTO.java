package y4kenz1.locationsystem.model;

import lombok.Getter;

@Getter
public class SharedLocationDTO {
    private Long id;
    private Long locationId;
    private Long userId;
    private AccessType accessType;
}
