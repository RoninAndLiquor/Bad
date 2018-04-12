package high.socketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args){
		
		try {
			Socket socket  = new Socket("127.0.0.1",10086);
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("1122334455667788");
			pw.flush();
			socket.shutdownOutput();
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String info = null;
			while((info = br.readLine())!=null){
				System.out.println("客戶端收到服務器消息："+info);
			}
			
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
