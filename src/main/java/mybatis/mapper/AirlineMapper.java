package mybatis.mapper;

import java.util.List;
import mybatis.model.Airline;
import org.mybatis.cdi.Mapper;

@Mapper
public interface AirlineMapper {
    int insert(Airline row);
    List<Airline> selectAllWithFlights();
    int deleteByPrimaryKey(Integer id);
    Airline selectByPrimaryKey(Integer id);
    List<Airline> selectAll();
    int updateByPrimaryKey(Airline row);

}
