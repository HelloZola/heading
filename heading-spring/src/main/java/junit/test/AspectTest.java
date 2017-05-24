package junit.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import example.aspect.UserServiceImpl;

public class AspectTest {

	@Test
	public void test1(){
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		//UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
		UserServiceImpl userService = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
		userService.addUser();
	
	}
}
