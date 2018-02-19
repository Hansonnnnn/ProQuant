package StockBlTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import PO.StockData;
import VO.StockDataVO;
import VO.StockKLine;
import blservice.StockInfoBlService.StockInfoService;
import model.KLineType;

public class StockInfoTest {
	private static StockInfoService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		service = (StockInfoService) context.getBean("StockInfoService");
	}
	
	@Test
	public void getStockVOTest(){
		System.out.println(service.getStockVO("杭州园林").getId());
	}
	
	@Test
	public void getKLineTest(){
		Date startDate=new Date("2017/4/8");
		Date endDate=new Date("2017/4/29");
		ArrayList<StockKLine> stockKLines=service.getStockForKLine("杭州园林", startDate, endDate, KLineType.Day, true);
		System.out.println(stockKLines.size());
		System.out.println(stockKLines.get(0).getDate());
	}
	
	@Test
	public void getXXXTest(){
		 ArrayList<StockDataVO> stockDataVOs=service.getStockData("杭州园林", 10);
		System.out.println(stockDataVOs.size());
		System.out.println(stockDataVOs.get(9).getDate().toLocaleString());
	}
@Test
public void getXXXXXTest(){
	ArrayList<String> arrayList=new ArrayList<>();
	arrayList.add("杭州园林");
	System.out.println(service.stockCompare(arrayList).get(0).getTimeValue().size());
}
}
