package high.mapper;

import high.entity.Emp;

import java.util.List;
import java.util.Map;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface EmpMapper {

	List<Emp> queryEmpAll();
	
	void insert(Map<String,Object> paramMap);
	
	List<Emp> queryEmpByParam(Map<String,Object> paramMap);
	
	Emp queryById(String empJobnum);
	
	void updateEmp(Map<String,Object> paramMap);
}
