package high.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler{

	private Object target;
	
	public Object getInstance(Object target){
		this.target = target;
		Class clazz = target.getClass();
		//this指一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
		Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
		return obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("DynamicProxy before handle!");
		Object invoke = method.invoke(target, args);
		System.out.println("DynamicProxy after handle!");
		return invoke;
	}

}
