package high.encodeAndDecode;

import high.util.SelfEncodeAndDecode;

public class SelfDecodeMain {

	public static void main(String[] args){
		String pwd = "251603253";
		String salt = "ɲɻɮɿʀɴ";
		String decode = SelfEncodeAndDecode.decode(pwd, salt);
		System.out.println(decode);
	}
	
}
