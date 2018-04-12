package high;

import java.io.IOException;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;


public class MyTest {

	public static void main(String[] args) throws ClientProtocolException, IOException{
	
		/*String asString = Request.Get("http://blog.csdn.net/vector_yi")  
        .connectTimeout(1000)  
        .socketTimeout(1000)  
        .execute().returnContent().asString(); */ 
		
		String asString = Request.Get("http://www.qq.com")
				.connectTimeout(1000)
				.socketTimeout(1000)
				.execute()
				.returnContent()
				.asString();
		System.out.println(asString);
		
		String asString2 = Request.Post("http://127.0.0.1:8081/high/emp/uploadView.htm")
				.useExpectContinue()
				.version(HttpVersion.HTTP_1_1)
				.bodyString("important stuff", ContentType.DEFAULT_TEXT)
				.execute()
				.returnContent()
				.asString();
		System.out.println(asString2);
		
	}
	
}
