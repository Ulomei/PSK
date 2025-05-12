package mybatis.mapper;

import java.util.List;
import mybatis.model.Airline;
import org.mybatis.cdi.Mapper;

@Mapper
public interface AirlineMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Airline row);
    Airline selectByPrimaryKey(Integer id);
    List<Airline> selectAll();
    int updateByPrimaryKey(Airline row);
    List<Airline> selectAllWithFlights();
}
