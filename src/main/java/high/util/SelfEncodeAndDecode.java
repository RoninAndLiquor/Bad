package high.util;

import java.util.Random;

public class SelfEncodeAndDecode {

	public static String encode(String pwd,String salt){
		if(!ValidateUtils.ASCII(pwd)||!Assert.checkParam(salt)){
			return "密码或盐值格式有误";
		}
		char[] charArray = pwd.toCharArray();
		char[] charArray2 = salt.toCharArray();
		int mul = 1;
		for(int i=0;i<charArray2.length;i++){
			mul+=(int)charArray2[i];
		}
		mul = (int) (mul*12.02);
		System.out.println("mul="+mul);
		StringBuffer sb = new StringBuffer();
		int l[] = new int[charArray.length];
		for(int i=0;i<charArray.length;i++){
			l[i] = charArray[i]+mul;
			sb.append((char)l[i]);
		}
		if(sb.length()<16){
			sb.append((char)(mul+16));
			Random random = new Random();
			if(sb.length()<16){
				for(int i=sb.length();i<16;i++){
					sb.append((char)(random.nextInt(250)+75776));
				}
			}
		}
		return sb.toString();
	}
	
	public static String decode(String str,String salt){
		if(!Assert.checkParam(str)||!Assert.checkParam(salt)){
			return "参数有误";
		}
		char[] charArray2 = salt.toCharArray();
		int mul = 1;
		for(int i=0;i<charArray2.length;i++){
			mul+=(int)charArray2[i];
		}
		mul = (int) (mul*12.02);
		StringBuffer sb = new StringBuffer();
		char[] charArray = str.toCharArray();
		Integer flag = null;
		for(int i=0;i<charArray.length;i++){
			System.out.print((int)charArray[i]+" ");
			if((charArray[i]-mul)==-65520||(charArray[i]-mul)==16){
				flag = i;
				break;
			}
		}
		System.out.println();
		if(flag!=null){
			str = str.substring(0, flag);
		}
		char[] strArr = str.toCharArray();
		for(int i=0;i<strArr.length;i++){
			sb.append((char)(charArray[i]-mul));
		}
		return sb.toString();
	}
	
}
