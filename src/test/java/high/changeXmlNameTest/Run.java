package high.changeXmlNameTest;

import high.entity.Emp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.catalina.startup.ContextConfig;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

public class Run {

	public static void  main(String[] args) throws IOException{
		
		
		File file = new File("src/main/resources/application.properties");
		InputStream in = new FileInputStream(file);
		Properties pro = new Properties();
		pro.load(in);
		int size = pro.size();
		System.out.println(pro.get("jdbc.url"));
		Integer next = next(0,4);
		System.out.println(next);
		System.out.println(jiecheng(4));
	}
	
	private static Integer next(Integer result,Integer param){
		if(param>1){
			if(result==0){
				result += param;
			}else{
				result = param*result;
			}
			param--;
			return next(result,param);
		}
		return result;
	}
	public static int jiecheng(int i){
        if(i>1){
            return i*jiecheng(i-1);
        }
        return 1;
    }
}
