package high.proxyTest;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{

	private Object target;
	
	public CglibProxy(Object target){
		this.target = target;
	}
	
	public Object getIntence(){
		//1.工具类
		Enhancer en =  new Enhancer();
		//2.设置父类
		en.setSuperclass(target.getClass());
		//3.设置回调函数
		en.setCallback(this);
		//创建子类(代理对象)
		return en.create();
	}
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		System.out.println("Cglib before handle!");
		Object invoke = arg1.invoke(target, arg2);
		System.out.println("Cglib after handle!");
		return invoke;
	}

}
