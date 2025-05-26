package services;

import entities.Airline;
import entities.Flight;
import persistence.AirlineDAO;
import persistence.FlightDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/flights")
public class FlightResource {

    @Inject
    private FlightDAO flightDAO;

    @Inject
    private AirlineDAO airlineDAO;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlight(@PathParam("id") Integer id) {
        Flight flight = flightDAO.find(id);
        if (flight == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Flight not found").build();
        }
        return Response.ok(flight).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFlight(Flight flight) {
        Airline airline = airlineDAO.find(2); // Replace '1' with the desired logic

        if (airline == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Airline not found").build();
        }

        flight.setAirline(airline); // Assign the airline

        flightDAO.persist(flight);
        return Response.status(Response.Status.CREATED).entity(flight).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFlight(@PathParam("id") Integer id, Flight updatedFlight) {
        Flight existing = flightDAO.find(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Flight not found").build();
        }

        existing.setDeparture(updatedFlight.getDeparture());
        existing.setDestination(updatedFlight.getDestination());
        flightDAO.update(existing);

        return Response.ok(existing).build();
    }
}
