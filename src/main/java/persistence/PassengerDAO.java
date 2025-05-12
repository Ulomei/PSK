package persistence;

import entities.Flight;
import entities.Passenger;
import entities.Airline;
import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class PassengerDAO extends BaseDAO<Passenger> {

    protected PassengerDAO() {super(Passenger.class);}

    @Override
    public Passenger find(Object id) {
        return em.createQuery(
                "SELECT a FROM Passenger a LEFT JOIN FETCH a.flights WHERE a.id = :id",
                Passenger.class).setParameter("id", id).getSingleResult();

    }

    public List<Flight> findFlightsNotAssignedToPassenger(Integer passengerId) {
        if (passengerId == null) {
            return Collections.emptyList();
        }
        return em.createQuery(
                "SELECT e FROM Flight e WHERE NOT EXISTS (SELECT p FROM e.passengers p WHERE p.id = :passengerId)",
                Flight.class).setParameter("passengerId", passengerId).getResultList();
    }
}
