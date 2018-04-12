package high.service;

import high.entity.Emp;

import java.util.List;

public interface EmpService {

	List<Emp> queryEmpAll();
	
	String insert(String empJson);
	
	Emp queryById(String empJobnum);
	
	Emp updateEmp(Emp emp);
	
	List<Emp> queryByParam(Emp emp);
}
