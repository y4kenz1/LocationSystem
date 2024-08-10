package y4kenz1.locationsystem.model;

import javax.persistence.*;

@Entity
public class SharedLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private AccessType accessType;
}

enum AccessType {
    READ_ONLY,
    ADMIN
}