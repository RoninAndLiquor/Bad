package high.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;


@Configuration
public class JMSConfig {

	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory){
		DefaultJmsListenerContainerFactory  bean = new DefaultJmsListenerContainerFactory();
		bean.setPubSubDomain(true);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}
	
	
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory){
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}
	
}
