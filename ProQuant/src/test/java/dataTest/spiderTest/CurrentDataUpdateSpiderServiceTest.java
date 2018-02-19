package dataTest.spiderTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import data.spider.update.CurrentBenchDataUpdateSpider;
import data.spider.update.CurrentDataUpdateSpiderService;
import data.update.BasicStockDataUpdate;

public class CurrentDataUpdateSpiderServiceTest {
	private static CurrentDataUpdateSpiderService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		service = (CurrentDataUpdateSpiderService) context.getBean("CSDUS");
	}

	@Test
	public void test() {
	//	service.updateCurrentData();
	}

}
