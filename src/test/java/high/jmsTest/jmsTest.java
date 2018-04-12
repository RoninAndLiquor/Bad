package high.jmsTest;

import javax.jms.Destination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import high.Test.BaseTest;

public class jmsTest extends BaseTest {

	@Autowired
	private JMSProducer jmsProducer;
	
	@Test
	public void testJms() throws InterruptedException{
		//Destination destination = new ActiveMQQueue("springboot.queue.test");
		Destination destination = new ActiveMQTopic("springboot.queue.test");
		//Thread.sleep(5000000);
		for(int i=0;i<10;i++){ 
			jmsProducer.sendMessage(destination, "test"+i);
		}
	}
}
