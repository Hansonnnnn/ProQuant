package dataTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import PO.StockCurrentData;
import PO.StockData;
import dataservice.BenchDataService;
import dataservice.StockDataService;
import model.StockPlate;

public class StockDataServiceTest {

	private static StockDataService service;
	private static BenchDataService benchDataService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		service = (StockDataService) context.getBean("StockDataService");
		benchDataService = (BenchDataService) context.getBean(BenchDataService.class);
	}

	@Test
	public void test2() {
	
		System.out.println(service.searchStock("dq"));
	}
	/*
	@Test
	public void getCurrentData() {
		StockCurrentData data = service.getStockCurrentData("000050");
		System.out.println(data);
		
	}

	@Test
	public void getStockBasicIndex(){
		ArrayList<String> codes = new ArrayList<>();
		codes.add("000001");
		codes.add("000002");
		System.out.println(service.getStockBasicIndex(codes));
		
	}*/
	
	@Test
	public void test(){
		/*try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		List< String> result = benchDataService.getStockListOdBench(StockPlate.CSI300);
		Map<String,Map<Date, StockData>> map = new LinkedHashMap<>();
		
		System.out.println(new Date()+"start");
		for (String string : result) {
			System.out.println(map.size());
			map.put(string, service.getBasicDateStock(null, null, string));
		}
		//map = service.getBasicDateStock(null, null, result);

		System.out.println(new Date()+"finish");
		//service.getStocksOfPlate(null, null, StockPlate.SME);
	}
}
