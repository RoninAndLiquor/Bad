package high.changeXmlNameTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import high.entity.Emp;
import high.util.Assert;

public class ReflectRun {

	public static void main(String[] args){
		
		String str = "Wildlife.wmv";
		System.out.println(str.substring(str.indexOf(".")));
		String[] split = str.split(".");
		Integer i = new Integer(3500);
		if(Assert.checkParam(i)){
			System.out.println(i);
		}
		
		int ran = (int) (Math.random()*1000000);
		System.out.println(ran);
		System.out.println(99999*9);
	}
	
}
