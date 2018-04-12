package high.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException{
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(ia);
		InetAddress iad = InetAddress.getByName("www.baidu.com");
		System.out.println(iad);
	}
	
}
