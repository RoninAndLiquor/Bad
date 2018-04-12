package high.socketTest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo1 {
	
	public static void main(String[] args){
		
		try {
			InetAddress address = InetAddress.getLocalHost();
			String hostName = address.getHostName();
			String hostAddress = address.getHostAddress();
			String canonicalHostName = address.getCanonicalHostName();
			InetAddress[] allByName = InetAddress.getAllByName("USER-6ANCK71L0E");
			for(int i=0;i<allByName.length;i++){
				System.out.println(allByName[i].getHostAddress());
			}
			System.out.println(hostName);
			System.out.println(hostAddress);
			System.out.println(canonicalHostName);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
