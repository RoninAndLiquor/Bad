package high.dao.impl;

import high.dao.EmpDao;
import high.entity.Emp;
import high.mapper.EmpMapper;

import java.applet.Applet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDaoImpl implements EmpDao {

	@Autowired
	private EmpMapper empMapper;
	
	public List<Emp> queryEmpAll() {
		// TODO Auto-generated method stub
		return empMapper.queryEmpAll();
	}

	@Override
	public void insert(Emp emp) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(emp!=null){
			if(emp.getEmpJobnum()!=null){
				paramMap.put("empJobnum", emp.getEmpJobnum());
			}
			if(emp.getDeptId()!=null){
				paramMap.put("deptId", emp.getDeptId());
			}
			if(emp.getEmpIden()!=null){
				paramMap.put("empIden", emp.getEmpIden());
			}
			if(emp.getEmpName()!=null){
				paramMap.put("empName", emp.getEmpName());
			}
			if(emp.getEmpPhone()!=null){
				paramMap.put("empPhone", emp.getEmpPhone());
			}
			if(emp.getEmpRemark()!=null){
				paramMap.put("empRemark", emp.getEmpRemark());
			}
			if(emp.getEmpState()!=null){
				paramMap.put("empState", emp.getEmpState());
			}
			if(emp.getPostId()!=null){
				paramMap.put("postId", emp.getPostId());
			}
			if(emp.getTitleId()!=null){
				paramMap.put("titleId", emp.getTitleId());
			}
			if(paramMap!=null){
				empMapper.insert(paramMap);
			}
		}
		
	}

	@Override
	public List<Emp> queryEmpByParam(Emp emp) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(emp!=null){
			if(emp.getEmpJobnum()!=null){
				paramMap.put("empJobnum", emp.getEmpJobnum());
			}
			if(emp.getDeptId()!=null){
				paramMap.put("deptId", emp.getDeptId());
			}
			if(emp.getEmpIden()!=null){
				paramMap.put("empIden", emp.getEmpIden());
			}
			if(emp.getEmpName()!=null){
				paramMap.put("empName", emp.getEmpName());
			}
			if(emp.getEmpPhone()!=null){
				paramMap.put("empPhone", emp.getEmpPhone());
			}
			if(emp.getEmpRemark()!=null){
				paramMap.put("empRemark", emp.getEmpRemark());
			}
			if(emp.getEmpState()!=null){
				paramMap.put("empState", emp.getEmpState());
			}
			if(emp.getPostId()!=null){
				paramMap.put("postId", emp.getPostId());
			}
			if(emp.getTitleId()!=null){
				paramMap.put("titleId", emp.getTitleId());
			}
		}
		return empMapper.queryEmpByParam(paramMap);
	}

	@Override
	public Emp queryById(String empJobnum) {
		// TODO Auto-generated method stub
		if(empJobnum!=null&&!empJobnum.equals("")){
			return empMapper.queryById(empJobnum);
		}
		return null;
	}

	@Override
	public void updateEmp(Emp emp) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(emp!=null){
			if(emp.getEmpJobnum()!=null){
				paramMap.put("empJobnum", emp.getEmpJobnum());
			}else{
				return;
			}
			if(emp.getDeptId()!=null){
				paramMap.put("deptId", emp.getDeptId());
			}
			if(emp.getEmpIden()!=null){
				paramMap.put("empIden", emp.getEmpIden());
			}
			if(emp.getEmpName()!=null){
				paramMap.put("empName", emp.getEmpName());
			}
			if(emp.getEmpPhone()!=null){
				paramMap.put("empPhone", emp.getEmpPhone());
			}
			if(emp.getEmpRemark()!=null){
				paramMap.put("empRemark", emp.getEmpRemark());
			}
			if(emp.getEmpState()!=null){
				paramMap.put("empState", emp.getEmpState());
			}
			if(emp.getPostId()!=null){
				paramMap.put("postId", emp.getPostId());
			}
			if(emp.getTitleId()!=null){
				paramMap.put("titleId", emp.getTitleId());
			}
		}
		empMapper.updateEmp(paramMap);
	}

}
