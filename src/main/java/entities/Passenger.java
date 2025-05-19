package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PASSENGER")
@NamedQueries({
        @NamedQuery(name = "Passenger.findAll", query = "select a from Passenger as a")
})

public class Passenger implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 100)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 100)
    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "PASSENGER_FLIGHT",
            joinColumns = @JoinColumn(name = "PASSENGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID"))
    private List<Flight> flights = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id) &&
                Objects.equals(firstName, passenger.firstName) &&
                Objects.equals(lastName, passenger.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
