package usecases;

import entities.Passenger;
import lombok.Getter;
import lombok.Setter;
import persistence.PassengerDAO;
import services.DisplayService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named("passengersBean")
public class PassengersBean implements Serializable{

    @Inject
    private PassengerDAO passengerDAO;

    @Inject
    private DisplayService displayService;

    @Getter
    @Setter
    private Passenger passenger = new Passenger();

    @Getter
    private List<Passenger> passengers;

    @PostConstruct
    public void init() {loadPassengers();}

    private void loadPassengers() {passengers = passengerDAO.findAll();}

    @Transactional
    public void create() {
        passengerDAO.persist(passenger);
        for (var pas : passenger.getFlights()) {
            displayService.addNewFlightsToPassenger(passenger.getId(), pas);
        }
    }
}
