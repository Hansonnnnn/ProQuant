package data.spider;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import PO.InfoData;

public class Main {
	/*public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		StockDataService service = context.getBean(StockDataService.class);
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(service.getStockCurrentData("200011"));
		//List<String> basicBenchCodes =  Arrays.asList("000300","399005","399006");
		List<String> basicBenchCodes =  Arrays.asList("399006");
		for (String string : basicBenchCodes) {
			basicBenchDataSpider.test(string, 2010, 2017);
		}
	}*/
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		SessionFactory sessionFactory = context.getBean(SessionFactory.class);
		BasicDataSpiderService basicStockDataSpider= (BasicDataSpiderService) context.getBean("BSDS");
		Session session= sessionFactory.openSession();
		
		List<Object[]> list = session.createQuery("select I.id,I.code from InfoData I where I.id>2879").list();
		for (Object[] objects : list) {
			System.out.println("now id = "+objects[0]);
			basicStockDataSpider.test((String) objects[1], 2008, 2017);
		}
	/*	for (InfoData infoData : infoDatas) {
			System.out.println("now id = "+infoData.getId());
			basicStockDataSpider.test(infoData.getCode(), 2008, 2017);
		}*/
	}

}
