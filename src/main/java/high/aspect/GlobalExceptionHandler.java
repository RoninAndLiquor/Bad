package high.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class GlobalExceptionHandler {

	@Pointcut("execution(public * high..*.*(..))")
	public void execute(){};
	
	@AfterThrowing(pointcut="execute()",throwing="e")
	public void exceptionHandle(JoinPoint jp,Throwable e){
		if(e!=null){
			Logger logger = LoggerFactory.getLogger(jp.getSignature().getClass());
			logger.error(e.getMessage(),e);
		}
		System.out.println("**** Exception **** "+e.getMessage());
	}
	
}
