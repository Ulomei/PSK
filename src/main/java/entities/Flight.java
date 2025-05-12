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
@Table(name = "FLIGHT")
@NamedQueries({
        @NamedQuery(name = "Flight.findAll", query = "select a from Flight as a")
})

public class Flight implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(max =100)
    @Column(name = "DEPARTURE")
    private String departure;

    @Size(max =100)
    @Column(name = "DESTINATION")
    private String destination;

    @ManyToOne
    @JoinColumn(name = "AIRLINE_ID", nullable = false)
    private Airline airline;

    @ManyToMany(mappedBy = "flights")
    private List<Passenger> tasks = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
                Objects.equals(departure, flight.departure) &&
                Objects.equals(destination, flight.destination);
    }

    @Override
    public int hashCode() {return Objects.hash(id, departure, destination);}
}
