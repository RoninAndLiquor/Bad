package high.util;

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
		mul = mul*20;
		StringBuffer sb = new StringBuffer();
		int l[] = new int[charArray.length];
		for(int i=0;i<charArray.length;i++){
			l[i] = charArray[i]+mul;
			sb.append((char)l[i]);
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
		StringBuffer sb = new StringBuffer();
		char[] charArray = str.toCharArray();
		for(int i=0;i<charArray.length;i++){
			sb.append((char)(charArray[i]-mul*20));
		}
		return sb.toString();
	}
	
}
