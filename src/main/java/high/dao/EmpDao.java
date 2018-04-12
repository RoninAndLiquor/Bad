package high.dao;

import high.entity.Emp;

import java.util.List;
import java.util.Map;

public interface EmpDao {

	List<Emp> queryEmpAll();
	
	void insert(Emp emp);
	
	List<Emp> queryEmpByParam(Emp emp);
	
	Emp queryById(String empJobnum);
	
	void updateEmp(Emp emp);
	
}
