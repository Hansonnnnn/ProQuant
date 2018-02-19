package dataTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataservice.UserStockPoolService;

public class UserStockPoolServiceImplTest {

	private static UserStockPoolService userStockPoolService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		userStockPoolService = (UserStockPoolService) context.getBean("UserStockPoolService");
		
	}

	@Test
	public void testGetUserStockPool() {
		System.out.println(userStockPoolService.getUserStockPool("yinywf"));
	}

	@Test
	public void testAddToPool() {
		
		System.out.println(userStockPoolService.addToPool("yinywf", "600001"));
		System.out.println(userStockPoolService.addToPool("yinywf", "600176"));
	}

	@Test
	public void testRemoveFromPool() {
		System.out.println(userStockPoolService.removeFromPool("yinywf", "600176"));
	}

}
