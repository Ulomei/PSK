package usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import mybatis.mapper.AirlineMapper;
import mybatis.model.Airline;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("airlineMyBatis")
public class AirlineMyBatis implements Serializable{

    @Inject
    private AirlineMapper airlineMapper;

    @Getter
    @Setter
    private Airline airline = new Airline();

    public List<Airline> loadAllAirlines() {return airlineMapper.selectAllWithFlights();}

    @Transactional
    public String addAirline() {
        airlineMapper.insert(airline);
        return "/mybatis/airlines?faces-redirect=true";
    }
}
