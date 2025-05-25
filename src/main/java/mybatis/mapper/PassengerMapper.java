package mybatis.mapper;


import java.util.List;
import mybatis.model.Passenger;
import org.mybatis.cdi.Mapper;

@Mapper
public interface PassengerMapper {
    int insert(Passenger row);
    int deleteByPrimaryKey(Integer id);
    Passenger selectByPrimaryKey(Integer id);
    List<Passenger> selectAll();
    int updateByPrimaryKey(Passenger row);
}