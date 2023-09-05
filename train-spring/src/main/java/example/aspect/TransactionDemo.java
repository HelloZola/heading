package example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionDemo {

	@Pointcut("execution(* example.aspect.UserServiceImpl.addUser(..))")
	private void pointCutMethod() {
		
	}

	// 前置通知
	@Before("pointCutMethod()")
	public void startTransaction() {
		System.out.println("begin transaction ");
	}

	// 后置通知
	@AfterReturning(pointcut = "pointCutMethod()", returning = "result")
	public void commitTransaction() {
		System.out.println("commit transaction ");
	}

	// 环绕通知
	@Around("pointCutMethod()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("begin transaction");
		joinPoint.proceed();
		System.out.println("commit transaction");
	}
	
	//@AfterThrowing("pointCutMethod()")
	/*@AfterThrowing(throwing="ex",pointcut="pointCutMethod()")  
	public void handerException(Throwable ex){
		System.out.println("目标方法中抛出的异常:"+ex);  
        System.out.println("模拟抛出异常后的增强处理...");  
	}*/
	
}
