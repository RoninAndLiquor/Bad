package high.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import high.entity.Login;
import high.service.LoginService;
import high.service.ViewService;
import high.util.SendMailUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("login")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LoginService loginservice2;
	
	@Autowired
	ViewService viewService;
	
	@Autowired
	ViewService viewService2;
	
	@RequestMapping("loginView.htm")
	public String loginView(){
		LOG.info("**** singleton.equals ****"+loginService.equals(loginservice2));
		LOG.info("**** prototype.equals ****"+viewService.equals(viewService2));
		return "login/login";
	}
	
	@RequestMapping("login")
	public String user2Login(HttpServletRequest req,HttpServletResponse res,Login param,boolean remember){
		Login login = loginService.queryByNameAndPwd(param.getLoginJobnum(), param.getLoginPass());
		if(null!=login){
			if(remember){
				Cookie cookie = new Cookie(param.getLoginJobnum(),param.getLoginJobnum());
				cookie.setPath("/high/login/");
				cookie.setMaxAge(60*60*48);
				res.addCookie(cookie);
			}
			HttpSession session = req.getSession();
			String id = session.getId();
			session.setAttribute("login", param);
			String header = res.getHeader("JESSIONID");
			System.out.println("**** SESSIONID *****"+id);
			String encodeRedirectUrl = res.encodeRedirectUrl("login/register");
			LOG.info("*** encodeRedirectUrl ***"+encodeRedirectUrl);
			return "login/register";
		}
		return "login/forgot";
	}
	
	@RequestMapping("signUp.htm")
	public String registry(){
		return "login/register";
	}
	
	@RequestMapping("forgot")
	public String forgot(){
		return "login/forgot";
	}
	
	@RequestMapping("mailValidate.json")
	@ResponseBody
	public String mailValidate(String userMail){
		JavaMailSenderImpl sender = SendMailUtils.initJavaMailSender();
		String[] mail = {userMail};
		int code = (int) (Math.random()*10000);
		try {
			SendMailUtils.sendTextWithHtml(sender, mail, "验证码", String.valueOf(code));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "ERROR";
		}
		return String.valueOf(code);
	}
	
}
