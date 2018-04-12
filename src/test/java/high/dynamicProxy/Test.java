package high.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

	public static  void main(String[] args){
		Consumer consumer = new Consumer();
		//consumer.setCash(120000);
		InvocationHandler handler = new DynamicProxyTest(consumer);
		IBuyCar buycar =  (IBuyCar) Proxy.newProxyInstance(handler.getClass().getClassLoader(), consumer.getClass().getInterfaces(), handler);
		buycar.buyCar(120001);
	}
	
}
