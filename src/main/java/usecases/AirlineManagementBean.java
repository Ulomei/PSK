package usecases;

import entities.Airline;
import entities.Flight;
import lombok.Getter;
import lombok.Setter;
import lombok.var;
import persistence.AirlineDAO;
import persistence.FlightDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;

@ViewScoped
@Named("airlineManagementBean")
public class AirlineManagementBean implements Serializable{
    @Inject
    private AirlineDAO airlineDAO;

    @Inject
    private FlightDAO flightDAO;

    @Getter
    @Setter
    private Airline airline;

    @Getter
    @Setter
    private Integer airlineId;

    @Getter
    @Setter
    private Flight flight = new Flight();

    public void loadAirlineData() {
        airline = airlineDAO.find(airlineId);
    }

    public void createFlight() {
        var managedAirline = airlineDAO.find(airline.getId());
        flight.setAirline(managedAirline);
        flightDAO.persist(flight);
    }

    @Transactional
    public void deleteFlight(Integer flightId) {
        if (flightId == null)
            return;

        var flight = flightDAO.find(flightId);
        if(flight == null)
            return;

        var assignedPassengers = flight.getPassengers();
        if (!assignedPassengers.isEmpty()) {
            for (var assignedPassenger : assignedPassengers) {
                assignedPassenger.getFlights().remove(flight);
            }
        }

        flightDAO.removeById(flight.getId());
    }

    @Transactional
    public String deleteAirline() {
        if(airlineId == null)
            return null;
        var managedAir = airlineDAO.find(airlineId);
        if(managedAir == null || !managedAir.getFlights().isEmpty())
            return null;

        airlineDAO.removeById(airlineId);
        return "index?faces-redirect=true";
    }

}
