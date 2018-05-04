package high;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class RandomNumber {

	public static void main(String[] args){
		int count = 200;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<count;i++){
			sb.append((int)(Math.random()*10));
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("D:/randomNumber.txt"));
			os.write(sb.toString().getBytes());
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for(int i=0;i<100;i++){
			int num = (int) ((Math.random() * 9 +1) * 100000);
			System.out.println(num);
		}
		char a = 'A';
		char z = 'Z';
		System.out.println((int)a);
		System.out.println((int)z);
	}
	
}
