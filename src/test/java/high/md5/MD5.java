package high.md5;

import high.util.Base64Util;
import high.util.SelfEncodeAndDecode;

import java.security.MessageDigest;

import org.springframework.util.DigestUtils;

public class MD5 {

	
	public static void main(String[] args){
		try {
			String pwd = "forbid404";
			String salt = "ɲɻɮɿʀɴ";
			String decode = SelfEncodeAndDecode.encode(pwd, salt);
			System.out.println(decode);
			String decode2 = SelfEncodeAndDecode.decode(decode, salt);
			System.out.println(decode2);
			/*MessageDigest digest = MessageDigest.getInstance("md5");
			String salt = "jsfwlq";˯
			String password = "wolftooth..0";ɲɻɮɿʀɴ
			String str = password+salt;˯˰˱˲˳˴˵˶˷˸˹˺˻˼˽˾˿̴̵̶̷̸̡̢̧̨̛̖̗̘̙̜̝̞̟̠̣̤̥̦̩̪̫̬̭̮̯̰̱̲̳̹̺̻̼͇͈͉͍͎̀́̂̃̄̅̆̇̈̉̊̋̌̍̎̏̐̑̒̓̔̽̾̿̀́͂̓̈́͆͊͋͌̕̚ͅ͏͓͔͕͖͙͚͐͑͒͗͛ͣͤͥͦͧͨͩͪͫͬͭͮͯ͘͜͟͢͝͞͠͡
			String md5DigestAsHex = DigestUtils.md5DigestAsHex(str.getBytes());
			System.out.println(md5DigestAsHex);
			byte[] md5Digest = DigestUtils.md5Digest(md5DigestAsHex.getBytes());
			String s = "8af44ad19d3de43296cd3b8f1d7fd278";
			String saltMD5 = MD5Utils.getSaltMD5(password);
			
			System.out.println(saltMD5);
			String getconvertMD5 = MD5Utils.getStrMD5(saltMD5);
			System.out.println(getconvertMD5);*/
			/*for(int i=75000;i<76000;i++){
				System.out.print((char)i);
			}*/
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
