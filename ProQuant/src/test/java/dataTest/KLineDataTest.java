package dataTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import PO.kLinePO.KLineTimeData;
import dataservice.KLineDataService;

public class KLineDataTest {
	private static KLineDataService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		service = (KLineDataService) context.getBean("KLineDataService");
	}
	
	@Test
	public void test(){
		ArrayList<KLineTimeData> list=service.getTimeLine("000001");
		System.out.println(list.get(0).getTime());
		System.out.println(list.get(50).getTime());
		System.out.println(list.get(0).getDate().toLocaleString());
	}

}
