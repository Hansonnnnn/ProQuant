package dataTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import PO.user.UserData;
import dataservice.UserLogService;

public class UserLogServiceTest {

	private static UserLogService userLogService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		userLogService = (UserLogService) context.getBean("UserLogService");
		
	}

	@Test
	public void testGetUserBasicData() {
		System.out.println(userLogService.getUserBasicData("yinywf"));
	}

	@Test
	public void testHasSigned() {
		System.out.println(userLogService.hasSigned("yinywf"));
	}

	@Test
	public void testSignIn() {
		userLogService.signIn("yinywf");
	}

	
	/*@Test
	public void testSignUp(){
		System.out.println(userLogService.signUp(new UserData("yinywf2","wf980102",null)));;
		
	}*/
	@Test
	public void testLogIn(){
		System.out.println(userLogService.logIn("yiniwf", "wf980102"));
	}
}
