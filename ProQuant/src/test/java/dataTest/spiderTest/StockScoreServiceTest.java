package dataTest.spiderTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataservice.StockScoreService;

public class StockScoreServiceTest {

	private static StockScoreService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		service = (StockScoreService) context.getBean("StockScoreService");
	}

	@Test
	public void test() {
		System.out.println(service.getStockScore("000049"));
	}

}
