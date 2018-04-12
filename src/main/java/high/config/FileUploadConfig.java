package high.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FileUploadConfig {

	
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("4096MB");
		factory.setMaxRequestSize("5120MB");
		return factory.createMultipartConfig();
		
	}
	
}
