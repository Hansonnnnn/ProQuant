package dataTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataservice.UserTradeService;

public class UserTradeServiceImplTest {

	public static void main(String[] args) {
		UserTradeService userTradeService;
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		userTradeService = (UserTradeService) context.getBean("UserTradeService");
		System.out.println(userTradeService.getUserStockOwned("yinywf").size());
		System.out.println(userTradeService.getUserTradeRecord("yinywf").size());
	
		System.out.println(userTradeService.getUserAccount("yinywf"));
	}

}
