package high.service.impl;

import java.io.IOException;
import java.util.List;

import high.controller.EmployeeController;
import high.dao.EmpDao;
import high.entity.Emp;
import high.service.EmpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmpServiceImpl1 implements EmpService{

	private static final Logger LOG = LoggerFactory.getLogger(EmpServiceImpl1.class);
	
	@Autowired
	private EmpDao empDao;

	public EmpServiceImpl1() {
		super();
		LOG.info("****** EmpServiceImpl 初始化 ******");
	}

	@Transactional
	@Cacheable(value="queryEmpAll",keyGenerator="wiselyKeyGenerator")
	public List<Emp> queryEmpAll() {
		// TODO Auto-generated method stub
		LOG.info("*** 进入第一个类的方法 ***");
		return empDao.queryEmpAll();
	}

	@Transactional
	@CachePut(cacheNames="high.service.impl.EmpServiceImpl1queryEmpAll")
	public String insert(String empJson) {
		// TODO Auto-generated method stub
		LOG.info("*** 进入第一个类的方法 ***");
		try {
			empDao.insert(new ObjectMapper().readValue(empJson, Emp.class));
			return empJson;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Cacheable(value="queryById",keyGenerator="wiselyKeyGenerator")
	public Emp queryById(String empJobnum) {
		return empDao.queryById(empJobnum);
	}
	@CachePut(cacheNames="queryById",keyGenerator="wiselyKeyGenerator")
	public Emp updateEmp(Emp emp){
		empDao.updateEmp(emp);
		return emp;
	}
	
	public List<Emp> queryByParam(Emp emp){
		return empDao.queryEmpByParam(emp);
	}
}
