package high.mapper;

import high.entity.Login;

import java.util.Map;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface LoginMapper {

	public void insert(Map<String,Object> paramMap);
	
	Login queryByNameAndPwd(Map<String,Object> paramMap);
	
}
