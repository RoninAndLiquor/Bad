package high.controller;

import java.awt.List;
import java.sql.Clob;
import java.util.LinkedHashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import high.entity.Address;
import high.entity.Emp;
import high.entity.User;
import high.service.impl.DemoServiceImpl;
import high.util.SendMailUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(description="邮件测试")
@Controller
@RequestMapping("demo")
public class DemoController {

	@Autowired
	DemoServiceImpl demoService;
	
	@RequestMapping(value = "putCache.json",method = RequestMethod.POST)
	@ResponseBody
	public String putCache(){
		demoService.findUser(11L, "wang", "shi");
		demoService.findAddress(12L, "henan", "jiaxian");
		System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
		return "ok";
	}
	@RequestMapping(value = "testCache.json",method = RequestMethod.POST)
	@ResponseBody
	public String testCache(){
		User user = demoService.findUser(11L, "wang", "shi");
		Address address = demoService.findAddress(12L, "henan", "jiaxian");
		System.out.println("我这里没执行查询");  
	    System.out.println("user:"+"/"+user.getFirstName()+"/"+user.getLastName());  
	    System.out.println("address:"+"/"+address.getProvince()+"/"+address.getCity());  
	    return "ok";  
	}
	
	@RequestMapping(value = "testMail.json",method = RequestMethod.POST)
	@ResponseBody
	public String testMail() throws Exception{
		JavaMailSenderImpl javaMailSender = SendMailUtils.initJavaMailSender();
		String[] ss = { "1037322988@qq.com" };  
		String subject = "测试邮件";
		String text = "<a href='http://127.0.0.1:8081/high/sign/signIn.htm/'>test</a>测试我的简单邮件发送机制！！";
		SendMailUtils.sendTextWithHtml(javaMailSender, ss, subject, text);
		return null;
	}
	@RequestMapping(value="face.htm")
	public String faceTest(){
		return "face";
	}
	@RequestMapping(value="exception.json",produces="application/json;charset=iso-8859-1")
	@ResponseBody
	public Map<String,Object> exception(){
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("NO.1", "这里是中文");
		map.put("NO.2", "SECOND COMING");
		map.put("NO.3", "THREE IS GONE");
		return map;
	}
	
}
