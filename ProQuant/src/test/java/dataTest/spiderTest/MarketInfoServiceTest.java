package dataTest.spiderTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataservice.MarketInfoService;

public class MarketInfoServiceTest {
	
	private static MarketInfoService marketInfoService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		marketInfoService = (MarketInfoService) context.getBean("MarketInfoService");
	}

	@Test
	public void test() {
		System.out.println(marketInfoService.getCurrentMarketInfo());
	}
}
