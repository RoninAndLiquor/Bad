package high.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

public class DynamicProxyTest implements InvocationHandler{

	Object obj;
	
	public DynamicProxyTest(Object obj){
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.print("前置操作:校验参数");
		for(int i=0;i<args.length;i++){
			System.out.println(args[i]);
		}
		Object result=null;
		if(Integer.parseInt(args[0].toString())>120000){
			try {
				result = method.invoke(this.obj, args);
			} catch (Exception e) {
				System.out.println("出现异常了！！！"+e.getMessage());
			}
		}else{
			System.err.println("资金不足！");
		}
		System.out.println("后置操作:查看结果"+JSON.toJSONString(result));
		return result;
	}

}
