package services;

import entities.Flight;
import org.example.pskpirmas.HelloServlet;

import javax.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DisplayService implements Serializable {

    private Map<Integer, List<Flight>> newFlights = new HashMap<>();

    public List<Flight> getNewFlights(Integer id) {
        return newFlights.get(id);
    }



    public void addNewFlightsToPassenegr(Integer id, Flight flight) {
        if(!newFlights.containsKey(id)) {
            List<Flight> flights = new ArrayList<>();
            flights.add(flight);
            newFlights.put(id, flights);
        }else{
            newFlights.get(id).add(flight);
        }
    }
}
