package usecases;

import entities.Flight;
import entities.Passenger;
import lombok.Getter;
import lombok.Setter;
import persistence.FlightDAO;
import persistence.PassengerDAO;
import services.DisplayService;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("passengerManagementBean")
public class PassengerManagementBean implements Serializable{

    @Inject
    private PassengerDAO passengerDAO;

    @Inject
    private FlightDAO flightDAO;

    @Inject
    private DisplayService displayService;

    @Getter
    @Setter
    private Passenger passenger;

    @Getter
    @Setter
    private Integer passengerId;

    @Getter
    @Setter
    private List<Integer> selectedFlightIds = new ArrayList<>();

    public void loadPassengerData() {passenger = passengerDAO.find(passengerId);}

    public List<Flight> getNewFlight() {
        if (passenger == null || passenger.getId() == null) {
            return new ArrayList<>();
        }
        return displayService.getNewFlights(passenger.getId());
    }

    public List<Flight> getEmptyFlights() {
        if (passenger == null || passenger.getId() == null) {
            return new ArrayList<>();
        }
        return passengerDAO.findFlightsNotAssignedToPassenger(passenger.getId());
    }

    @Transactional
    public String deletePassenger() {
        if (passenger == null || passenger.getId() == null) {
            return null;
        }
        passengerDAO.removeById(passenger.getId());
        return "index?faces-redirect=true";
    }

    @Transactional
    public void addFlightsToPassenger() {
        var managedPassenegr = passengerDAO.find(passenger.getId());
        if(managedPassenegr == null)
            return;

        for(Integer employeeId : selectedFlightIds) {
            var flight = flightDAO.find(employeeId);
            if(flight != null){
                managedPassenegr.getFlights().add(flight);
                displayService.addNewFlightsToPassenger(passenger.getId(), flight);
            }
        }
    }

    @Transactional
    public void removeEmployeeFromProject(Integer flightId) {
        if (passenger == null || passenger.getId() == null || flightId == null) {
            return;
        }

        var managedPassenger = passengerDAO.find(passenger.getId());
        if (managedPassenger == null) {
            return;
        }

        var flight = flightDAO.find(flightId);
        if (flight == null) {
            return;
        }
        managedPassenger.getFlights().remove(flight);

        displayService.removeFlightFromPassenger(passenger.getId(), flightId);
    }
}
