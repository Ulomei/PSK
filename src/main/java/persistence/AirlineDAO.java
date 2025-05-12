package persistence;

import entities.Airline;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AirlineDAO extends BaseDAO<Airline> {

    protected AirlineDAO() { super(Airline.class); }

    @Override
    public Airline find(Object id) {
        return em.createQuery(
                "SELECT a FROM Airline a LEFT JOIN FETCH a.flights WHERE a.id = :id",
                Airline.class).setParameter("id", id).getSingleResult();
    }
}
