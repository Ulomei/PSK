package persistence;

import entities.Passenger;
import entities.Flight;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FlightDAO extends BaseDAO<Flight> {

    protected FlightDAO() {super(Flight.class);}

    @Override
    public Flight find(Object id) {
        return em.createQuery(
                "SELECT f FROM Flight f LEFT JOIN FETCH f.airline LEFT JOIN FETCH f.passengers WHERE f.id = :id",
                Flight.class).setParameter("id", id).getSingleResult();
    }
}
