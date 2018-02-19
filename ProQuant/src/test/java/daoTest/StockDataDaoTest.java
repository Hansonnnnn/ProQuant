package daoTest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.dao.StockDataDao;
import PO.StockData;
import model.StockPlate;

public class StockDataDaoTest {

	private static StockDataDao stockDataDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		stockDataDao = (StockDataDao) context.getBean("StockDataDao");
	}

	@Test
	public void test() {
		/*Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 1);
		Date start = calendar.getTime();
		calendar.set(2015, 0, 1);
		Date end = calendar.getTime();
		
		Map<Date, StockData> map = stockDataDao.queryByHql("000001", start, end);
		System.out.println(map.size());*/
		System.out.println(stockDataDao.queryBuHql(StockPlate.CSI300, null, null));
	}

}
