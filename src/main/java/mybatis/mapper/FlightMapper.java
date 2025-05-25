package mybatis.mapper;

import java.util.List;
import mybatis.model.Flight;
import org.mybatis.cdi.Mapper;

@Mapper
public interface FlightMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Flight row);
    List<Flight> selectAll();
    Flight selectByPrimaryKey(Integer id);
    int updateByPrimaryKey(Flight row);
}
