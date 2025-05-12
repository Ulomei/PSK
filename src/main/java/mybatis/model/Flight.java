package mybatis.model;

import entities.Airline;
import entities.Passenger;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Flight {
    private Integer id;
    private String departure;
    private String destination;
    private Integer airlineId;
    private Airline airline;
    private List<Passenger> passengers;
}
