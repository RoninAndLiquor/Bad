package high.jmsTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer {

	private final static Logger LOG = LoggerFactory.getLogger(JMSConsumer.class);
	
	@JmsListener(destination="springboot.queue.test")
	public void receiveQueue(String msg){
		LOG.info("**************  第一個方法接收到消息{}："+msg);
	}	
}
