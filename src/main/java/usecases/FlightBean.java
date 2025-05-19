package usecases;

import entities.Flight;
import lombok.Getter;
import lombok.Setter;
import persistence.FlightDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("flightBean")
public class FlightBean implements Serializable{

    @Inject
    private FlightDAO flightDAO;

    @Getter
    @Setter
    private Flight flight;

    @Getter
    @Setter
    private Integer flightId;

    public List<Flight> getFlights() {return flightDAO.findAll();}

    public void loadFlightData() {flight = flightDAO.find(flightId);}
}
