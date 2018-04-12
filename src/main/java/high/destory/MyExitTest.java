package high.destory;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class MyExitTest implements ExitCodeGenerator{

	@Override
	public int getExitCode() {
		// TODO Auto-generated method stub
		System.out.println("*** 返回退出码 ***");
		return 0;
	}

}
