package usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import mybatis.mapper.FlightMapper;
import mybatis.mapper.PassengerFlightMapper;
import mybatis.model.Flight;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("flightMyBatis")
public class FlightMyBatis implements Serializable{

    @Inject
    private FlightMapper flightMapper;

    @Inject
    private PassengerFlightMapper passengerFlightMapper;

    @Getter
    @Setter
    private Flight flight;

    public List<Flight> loadAllFlights() {return flightMapper.selectAll();}

    @Transactional
    public void insertFlight() {flightMapper.insert(flight);}

    @Transactional
    public String deleteFlight(Integer id) {
        passengerFlightMapper.deletePassengerForFlight(id);
        flightMapper.deleteByPrimaryKey(id);
        return "/mybatis/flights?faces-redirect=true";
    }
}
