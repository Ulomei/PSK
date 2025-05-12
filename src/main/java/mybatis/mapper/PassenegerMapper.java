package mybatis.mapper;


import java.util.List;
import mybatis.model.Passenger;
import org.mybatis.cdi.Mapper;

@Mapper
public interface PassenegerMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Passenger row);
    Passenger selectByPrimaryKey(Integer id);
    List<Passenger> selectAll();
    int updateByPrimaryKey(Passenger row);
}
