package high.destory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;


@Component
public class MyDestroyTest1 implements DisposableBean{

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("*** SpringBoot is Destory.... ***");
	}

}
