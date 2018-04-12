package high.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("file:D:/WorkSpaceTest/high/src/main/webapp/img/");
		super.addResourceHandlers(registry);
		//FactoryBean
		//BeanFactory
		//JdbcTemplate
		//RdbmsOperation
		//BeanDefinition
		//ApplicationContext
		//WebApplicationContext
		//ThemeSource
		//ContextLoaderListener
		
	}

	
	
}
