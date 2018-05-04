package high.encodeAndDecode;

import high.util.SelfEncodeAndDecode;

public class SelfDecodeMain {

	public static void main(String[] args){
		String pwd = "⢠⢩⢬⢜⢣⢞⡮⡪⡮⡊⡎⢴⣸⠞⣅⣂";
		String salt = "ɲɻɮɴɷɾɭɿʀɴ";
		String decode = SelfEncodeAndDecode.decode(pwd, salt);
		System.out.println(decode);
		char[] charArray = salt.toCharArray();
		/*for(int i=0;i<charArray.length;i++){
			System.out.print((char)(charArray[i]-520));
		}*/
	}
	
}
