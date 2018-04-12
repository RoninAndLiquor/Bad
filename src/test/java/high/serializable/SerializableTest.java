package high.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import high.entity.Login;
import high.entity.User;

public class SerializableTest {

	private static RuntimeSchema<User> userSchema  = RuntimeSchema.createFrom(User.class);
	
	private static RuntimeSchema<Login> loginSchema = RuntimeSchema.createFrom(Login.class);
	
	public static void main(String[] args){
		
		User user = new User();
		user.setFirstName("111");
		user.setLastName("222");
		user.setId(999L);
		user.setUserMail("100@qq.com");
		
		Login login = new Login();
		login.setLoginJobnum("123456");
		login.setLoginPass("456789");
		try {
			/*FileOutputStream fos1  = new FileOutputStream("F:/user.txt");
			ObjectOutputStream os1 = new ObjectOutputStream(fos1);
			os1.writeObject(user);
			FileOutputStream fos2  = new FileOutputStream("F:/login.txt");
			ObjectOutputStream os2 = new ObjectOutputStream(fos2);
			os2.writeObject(login);*/
			/*FileInputStream fis1 = new FileInputStream("F:/user.txt");
			ObjectInputStream os1 = new ObjectInputStream(fis1);
			User  u= (User) os1.readObject();
			System.out.println("User:"+JSON.toJSONString(u));
			FileInputStream fis = new FileInputStream("F:/login.txt");
			ObjectInputStream os = new ObjectInputStream(fis);
			Login lo = (Login) os.readObject();
			System.out.println("Login:"+JSON.toJSONString(lo));*/
			/*os1.close(); 
			fos1.close();
			os2.close();
			fos2.close();*/
			byte[] byteArray = ProtostuffIOUtil.toByteArray(user, userSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			for(int i=0;i<byteArray.length;i++){
				System.out.print(byteArray[i]);
			}
			User newMessage = userSchema.newMessage();
			ProtostuffIOUtil.mergeFrom(byteArray, newMessage, userSchema);
			String string = JSON.toJSON(newMessage).toString();
			System.out.println(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
