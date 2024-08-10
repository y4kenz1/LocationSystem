package y4kenz1.locationsystem.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Location {
    //TODO: Location should have at least location name and address in US format with blank validation for required fields.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}