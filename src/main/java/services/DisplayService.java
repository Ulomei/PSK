package services;

import entities.Flight;

import javax.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

@ApplicationScoped
public class DisplayService implements Serializable {

    private Map<Integer, List<Flight>> newFlights = new HashMap<>();

    public List<Flight> getNewFlights(Integer id) {
        return newFlights.get(id);
    }

    public void addNewFlightsToPassenger(Integer id, Flight flight) {
        if(!newFlights.containsKey(id)) {
            List<Flight> flights = new ArrayList<>();
            flights.add(flight);
            newFlights.put(id, flights);
        }else{
            newFlights.get(id).add(flight);
        }
    }

    public void removeFlightFromPassenger(Integer passengerId, Integer flightId) {
        if (passengerId != null && newFlights.containsKey(passengerId)) {
            List<Flight> flights = newFlights.get(passengerId);
            Iterator<Flight> iterator = flights.iterator();
            while (iterator.hasNext()) {
                Flight flight = iterator.next();
                if (flight.getId().equals(flightId)) {
                    iterator.remove();
                    break;
                }
            }
            // If the list is empty after removal, remove the entry
            if (flights.isEmpty()) {
                newFlights.remove(passengerId);
            }
        }
    }

    public void removeFlightFromAllPassengers(Integer flightId) {
        for (Map.Entry<Integer, List<Flight>> entry : newFlights.entrySet()) {
            List<Flight> flights = entry.getValue();
            Iterator<Flight> iterator = flights.iterator();
            while (iterator.hasNext()) {
                Flight flight = iterator.next();
                if (flight.getId().equals(flightId)) {
                    iterator.remove();
                }
            }
        }
        // Remove any empty lists
        newFlights.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }
}
