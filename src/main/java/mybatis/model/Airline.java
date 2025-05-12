package mybatis.model;

import entities.Flight;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter

public class Airline {
    private int id;
    private String name;
    private List<Flight> flights;
}
