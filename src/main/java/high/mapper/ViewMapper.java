package high.mapper;

import java.util.List;
import java.util.Map;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface ViewMapper {

	List<Map<String,Object>> queryAll();
	
}
