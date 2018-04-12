package high.controller;


import io.swagger.annotations.Api;
import high.entity.User;
import high.util.SendMailUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(description = "登陆注册")
@Controller
@RequestMapping("sign")
public class SignController {

	private final static String VALIDATE_CODE = "验证码";
	
	private final static String SUCCESS_STATE = "发送成功";
	
	@Autowired
	SendMailUtils mailUtil;
	
	@RequestMapping(value = "signIn.htm",method = RequestMethod.POST)
	public String loginView(){
		return "login/login";
	}
	@RequestMapping(value = "signUp.htm",method = RequestMethod.POST)
	public String registerView(){
		return "login/register";
	}
	
	/**
	 * 
	 * TODO 方法作用：邮箱验证
	 * @param user
	 * @return
	 * @Author: 蒋帅锋
	 * @Date: 2018年1月2日 上午10:07:16
	 */
	@RequestMapping(value = "mailValidate.json",method = RequestMethod.POST)
	@ResponseBody
	public String mailValidate(User user){
		String result = null;
		if(user!=null){
			String userMail = user.getUserMail();
			int ran = (int) (Math.random()*1000000);
			if(ran<100000){
				ran = ran*9;
			}
			String[] tos = {userMail};
			JavaMailSenderImpl sender = mailUtil.initJavaMailSender();
			try {
				String sendTextWithHtml = mailUtil.sendTextWithHtml(sender, tos, VALIDATE_CODE, ran+"");
				if(sendTextWithHtml.equals(SUCCESS_STATE)){
					result = ran+"";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@RequestMapping(value = "forgot.htm",method = RequestMethod.POST)
	public String forgotView(){
		return "login/forgot";
	}
	
}
