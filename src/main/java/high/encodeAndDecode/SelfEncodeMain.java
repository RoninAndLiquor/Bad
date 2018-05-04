package high.encodeAndDecode;

import high.util.SelfEncodeAndDecode;

public class SelfEncodeMain {

	public static void main(String[] args){
		String pwd = "251603253";
		String salt = "ɲɻɮɿʀɴ";
		String encode = SelfEncodeAndDecode.encode(pwd, salt);
		System.out.println(encode);
	}
	
}
