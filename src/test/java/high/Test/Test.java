package high.Test;

import high.entity.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.DigestUtils;


public class Test {

	public static void main(String[] args) throws IOException{
		int[] ints = new int[7];
		ints[0] = 2;
		ints[1] = 3;
		ints[2] = 4;
		ints[3] = 5;
		ints[4] = 10;
		ints[5] =20;
		ints[6] = 230;
		int number = 6;
		int length = ints.length;
		for(int i=number-1;i<length-1;i++){
			ints[i] = ints[i+1];
		}
		ints = Arrays.copyOf(ints, length-1);
		for( int i=0;i<ints.length;i++){
			System.out.print(ints[i]+" ");
		}
		String salt = "*#320934*&#JKJCKlll<k?||{::;'...'}{[]";
		Integer id = 12345;
		String str = id + "/" + salt;
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(str.getBytes());
		System.out.println(md5DigestAsHex);
		byte[] md5Digest = DigestUtils.md5Digest(md5DigestAsHex.getBytes());
		List<String> list = new ArrayList<String>();
		List<String> list2 = list;
		list.add("22");
		list.add("333");
		list.add("sss");
		System.out.println(list2);
	}
	public void str(HttpServletRequest req ,HttpServletResponse res,User user) throws UnsupportedEncodingException{
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		res.setHeader("contentType", "text/html;charset=utf-8");
		HttpSession session = req.getSession();
		session.setAttribute(String.valueOf(user.getId()), user);
		Object attribute = session.getAttribute(String.valueOf(user.getId()));
		if(attribute!=null){
			User u = (User) attribute;
		}
		session.setMaxInactiveInterval(3600);
		Cookie[] cookies = req.getCookies();
		String firstName = user.getFirstName();
		Cookie cookie = new Cookie("id",String.valueOf(user.getId()));
		cookie.setMaxAge(-1);
		cookie.setPath("/high/");
		res.addCookie(cookie);
	}
	
}
