package dataTest.spiderTest;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataservice.KLineDataService;
import dataservice.StockDataService;
public class KLineDataServiceTest {

	private static KLineDataService klineDataService;
	private static StockDataService stockDataService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		klineDataService = (KLineDataService) context.getBean("KLineDataService");
		stockDataService = (StockDataService) context.getBean("StockDataService");
	}

	@Test
	public void test() {
		//List result = klineDataService.getdayLine("000001", new Date(), 0, false);
		String code = stockDataService.getCode("方盛制药");
		System.out.println(code);
		List result = klineDataService.getTimeLine(code);
		System.out.println(result.size());
		System.out.println(result);
	}

}
