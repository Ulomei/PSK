package usecases;

import entities.Airline;
import lombok.Getter;
import lombok.Setter;
import persistence.AirlineDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named("airlineBean")
public class AirlineBean implements Serializable{

    @Inject
    private AirlineDAO airlineDAO;

    @Setter
    @Getter
    private Airline airline = new Airline();
    @Getter
    private List<Airline> airlines;

    @PostConstruct
    public void init() {
        loadAirlines();};

    @Transactional
    public void create() {
        airlineDAO.persist(airline);
    }

    private void loadAirlines() {
        airlines = airlineDAO.findAll();
    }

}
