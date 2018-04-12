package high.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import high.config.SpringBootWebSecurityConfiguration;
import high.destory.SpringContextUtil;
import high.entity.Emp;
import high.entity.Fruit;
import high.entity.Login;
import high.service.EmpService;
import high.service.LoginService;
import high.service.ViewService;
import high.util.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @包名 :high.controller
 * @文件名 :EmployeeController.java 
 * TODO 类作用：
 * @系统名称 : 测试
 * @Author: 蒋帅锋 
 * @Date: 2017年12月29日 上午10:35:02
 * @版本号   :v0.0.01
 */
@Api(description = "员工操作")
@Controller
//@Transactional
@RequestMapping("emp")
public class EmployeeController {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	@Qualifier(value="empServiceImpl1")
	private EmpService empService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ViewService view;
	
	@Autowired
	private Fruit fruit;
	
	ObjectMapper om = new ObjectMapper();
	
	/**
	 * 
	 * TODO 方法作用：查询所有员工
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @Author: 蒋帅锋
	 * @Date: 2017年12月29日 上午10:35:23
	 */
	@RequestMapping(value = "queryAll.json")
	@ResponseBody
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Emp> queryEmpAll() throws JsonGenerationException, JsonMappingException, IOException{
		List<Emp> queryEmpAll = empService.queryEmpAll();
		LOG.info("***结果集{}***"+JSON.toJSONString(queryEmpAll));
		LOG.error("****ERROR***");
		ExecutorService es = Executors.newFixedThreadPool(4);
		es.submit(new Runnable(){
			public void run(){
				LOG.info("**********线程**********"+Thread.currentThread().getName());
			}
		});
		om.writeValue(new File("F:/queryEmpAll.txt"), queryEmpAll);
		return queryEmpAll;
	}
	/**
	 * 
	 * TODO 方法作用：将查询的员工信息转换为txt文本形式存储
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @Author: 蒋帅锋
	 * @Date: 2017年12月29日 上午10:35:41
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "queryAllByTxt.json",method = RequestMethod.POST)
	@ResponseBody
	public List<Emp> queryEmpByTxt() throws JsonParseException, JsonMappingException, IOException{
		List<Emp> list = new ArrayList<Emp>();
		List<Emp> readValue = om.readValue(new File("F:/queryEmpAll.txt"), list.getClass());
		return readValue;
	}
	/**
	 * 
	 * TODO 方法作用：根据工号查询员工
	 * @param empJobnum
	 * @return
	 * @Author: 蒋帅锋
	 * @Date: 2017年12月29日 上午10:36:06
	 */
	@RequestMapping(value = "queryById.json")
	@ResponseBody
	public Emp queryById(String empJobnum){
		Emp queryById = empService.queryById(empJobnum);
		return queryById;
	}
	@RequestMapping(value = "index.htm",method = RequestMethod.POST)
	public String index(){
		String name = fruit.getName();
		String color = fruit.getColor();
		LOG.info("*** name && color *** "+name+"是"+color+"色的");
		return "index";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "save.htm",method = RequestMethod.POST)
	//@Transactional(noRollbackFor = SQLException.class)
	@Transactional
	public String save(Emp emp) throws InterruptedException, JsonProcessingException{
		LOG.info("*** emp ***"+Thread.currentThread().getName()+"  ---  "+JSON.toJSONString(emp));
		
		Date d = new Date();
		String empJobnum = (d.getYear()+1900)+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+d.getSeconds();
		if(Assert.checkParam(emp)){
			emp.setEmpJobnum(empJobnum);
			String empJson = om.writeValueAsString(emp);
			empService.insert(empJson);
			Thread.sleep(4000);
			System.out.println(">>>>>>>>>>>>>>  empJobnum="+empJobnum);
			Login login = new Login();
			login.setLoginJobnum(empJobnum);
			login.setLoginPass("123456");
			loginService.insert(login);
		}
		return "NewFile";
	}
	@ApiIgnore
	@RequestMapping(value = "view.json",method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> queryAllByView(ModelMap model){
		List<Map<String, Object>> queryAll = view.queryAll();
		model.addAttribute("test", 123456);
		return queryAll;
	}
	
	@RequestMapping(value = "new.htm")
	public String newFile(ModelMap model){
		model.addAttribute("two",222);
		return "NewFile";
	}
	
	@RequestMapping(value = "uploadView.htm")
	public String uploadView(){
		for(int i=0;i<100;i++){
			System.out.println(i);
		}
		return "upload";
	}
	@RequestMapping(value = "updateEmp.htm",method = RequestMethod.POST)
	public String updateEmpView(){
		Method[] methods = EmployeeController.class.getMethods();
		Annotation[] anno = new Annotation[100];
		int i=0;
		for(Method m:methods){
			Annotation[] annotations = m.getAnnotations();
			for(Annotation a:annotations){
				anno[i] = a;
				i++;
			}
		}
		return "updateEmp";
	}
	
	
	@RequestMapping(value = "updateEmp.json",method = RequestMethod.POST)
	@ResponseBody
	public Emp updateEmp(Emp emp) throws IOException{
		Emp updateEmp = empService.updateEmp(emp);
		return updateEmp;
	}
	
	@RequestMapping(value="queryByParam.json")
	@ResponseBody
	public List<Emp> queryByParam(HttpServletRequest request,Emp emp){
		ServletContext servletContext = request.getServletContext();
		try {
			//ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE");
			//SpringBootWebSecurityConfiguration bean = applicationContext.getBean(SpringBootWebSecurityConfiguration.class);
			SpringBootWebSecurityConfiguration bean = SpringContextUtil.getApplicationContext().getBean(SpringBootWebSecurityConfiguration.class);
			if(bean!=null){
				bean.test();
			}else{
				LOG.info("*** bean为空 ***");
			}
		} catch (Exception e) {
			LOG.info("*** Exception:bean为空 ***"+e.getMessage());
			e.printStackTrace();
		}
		return empService.queryByParam(emp);
	}
}
