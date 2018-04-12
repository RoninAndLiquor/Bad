package high.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(SwaggerConfig.class)
@ConditionalOnWebApplication
public class SpringBootWebSecurityConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(SpringBootWebSecurityConfiguration.class);
	//String date = "[ "+new SimpleDateFormat("yyyy-dd-MM HH:mm:ss:SSS").format(new Date())+" ]  ~  ";
	private String getDateStr(){
		return "[ "+new SimpleDateFormat("yyyy-dd-MM HH:mm:ss:SSS").format(new Date())+" ]  ~  ";
	}
	public void test(){
		System.out.println(getDateStr()+"'Conditional test'");
	}
	
}
