package mybatis.mapper;

import java.util.List;
import mybatis.model.Flight;
import org.mybatis.cdi.Mapper;

@Mapper
public interface FlightMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Flight row);
    Flight selectByPrimaryKey(Integer id);
    List<Flight> selectAll();
    int updateByPrimaryKey(Flight row);
}
