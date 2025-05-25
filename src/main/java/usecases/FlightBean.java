package usecases;

import entities.Flight;
import lombok.Getter;
import lombok.Setter;
import persistence.FlightDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    @Getter @Setter
    private String error;

    @Getter
    private String errorMessage;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        error = requestParameters.get("error");
        if (error != null && error.equals("optimistic-lock")) {
            errorMessage = "Someone else has updated this flight while you were editing it.";
        }
    }

    public List<Flight> getFlights() {return flightDAO.findAll();}

    public void loadFlightData() {
        flight = flightDAO.find(flightId);
    }

    public boolean isOptimisticLockError() {
        return error != null && error.equals("optimistic-lock");
    }

    public void reloadFlight() {
        flight = flightDAO.find(flightId);
        error = null;
        errorMessage = null;
    }

    public String updateFlight() {
        try {
            flightDAO.update(flight);
        } catch (OptimisticLockException e) {
            return "flightManagement.xhtml?faces-redirect=true&flightId=" + flight.getId() + "&error=optimistic-lock";
        }
        return "airlineManagement.xhtml?faces-redirect=true&airId=" + flight.getAirline().getId();
    }
}
