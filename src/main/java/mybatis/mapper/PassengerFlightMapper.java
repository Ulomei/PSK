package mybatis.mapper;

import java.util.List;
import mybatis.model.PassengerFlight;
import org.mybatis.cdi.Mapper;

@Mapper
public interface PassengerFlightMapper {
    int insert(PassengerFlight row);
    List<PassengerFlight> selectAll();
    int deleteAllPassengersForFlight(Integer flightId);
    int deletePassengerForFlight(PassengerFlight row);
    int deleteAllFlightsForPassenger(Integer passengerId);
}
