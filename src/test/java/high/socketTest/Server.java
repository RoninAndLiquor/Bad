package high.socketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args){
		
		try {
			ServerSocket serverSocket = new ServerSocket(10086);
			Socket socket = serverSocket.accept();
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			String info=null;
			while((info=br.readLine())!=null){
				System.out.println("服務器接收到客戶端信息："+info);
			}
			socket.shutdownInput();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("歡迎。。");
			pw.flush();
			
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
