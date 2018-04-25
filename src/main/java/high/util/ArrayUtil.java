package high.util;

public class ArrayUtil {

	public static String toString(String[] str){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length;i++){
			if(i!=str.length-1){
				sb.append(str[i]+" ");
			}else{
				sb.append(str[i]);
			}
		}
		return sb.toString();
	}
	
}
