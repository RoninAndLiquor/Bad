package high.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import high.entity.Emp;
import high.service.EmpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(description="Swagger测试Controller")
@RestController
@RequestMapping("swagger")
public class SwaggerController {

	@Autowired
	@Qualifier(value="empServiceImpl1")
	EmpService empService;
	
	@ApiOperation(value="获取所有的员工列表",notes="获取所有的员工列表")
	@RequestMapping(value={"queryAll.json"},method = RequestMethod.GET)
	public List<Emp> queryAll(){
		List<Emp> queryEmpAll = empService.queryEmpAll();
		return queryEmpAll;
	}
	
	@ApiOperation(value="获取单个员工信息",notes="根据员工的编号获取员工信息")
	@ApiImplicitParam(name="empJobnum",value="员工编号",required=true,dataType="String",paramType="path")
	@RequestMapping(value="queryById/{empJobnum}.json",method = RequestMethod.GET)
	public Emp queryById(@PathVariable String empJobnum){
		Emp queryById = empService.queryById(empJobnum);
		return queryById;
	}
	
}
