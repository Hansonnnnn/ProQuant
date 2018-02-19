package dataTest;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import PO.BenchCurrentData;
import dataservice.BenchDataService;
import dataservice.StockDataService;
import model.StockPlate;

public class BenchDataServiceTest {

	private static BenchDataService service;
	private static StockDataService stockService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		service = (BenchDataService) context.getBean("BenchDataService");
		stockService = (StockDataService) context.getBean("StockDataService");
		
	}


	/*@Test
	public void testGetStocksByDateAndPlate() {
		Map<Date,BenchData> map = service.getDataByNumAndPlate(null, 100, StockPlate.CSI300);
		
		System.out.println(map);
	}
*/
	@Test
	public void testGetBenchCurrentData() {
		
		BenchCurrentData benchData = service.getBenchCurrentData(StockPlate.CHINEXT);
		System.out.println(benchData.getDate());
		System.out.println(benchData.getDate().getClass());
		Timestamp timestamp = new Timestamp(2016, 0, 0, 0, 0, 0, 0);
		System.out.println(new Date(benchData.getDate().getTime()));
	}

/*	
	@Test
	public void testGetStockListOfBench(){
		List<String> result = service.getStockListOdBench(StockPlate.SME);
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, StockCurrentData> map = stockService.getStockCurrentDataAll();
		List<StockCurrentData> list = new ArrayList<>();
		for (String string : result) {
			list.add(map.get(string));
		}
		System.out.println(list);
	}*/
}
