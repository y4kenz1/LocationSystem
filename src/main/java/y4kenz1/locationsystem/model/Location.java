package y4kenz1.locationsystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Location name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    @Pattern(regexp = "^[0-9]+\\s[A-Za-z]+\\s[A-Za-z]+,\\s[A-Za-z]+,\\s[A-Z]{2}\\s[0-9]{5}$", message = "Invalid US address format")
    private String address;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}