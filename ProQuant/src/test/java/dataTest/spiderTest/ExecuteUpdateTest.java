package dataTest.spiderTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import data.update.BasicStockDataUpdate;


public class ExecuteUpdateTest {

	private static BasicStockDataUpdate executeUpdate;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		executeUpdate = context.getBean(BasicStockDataUpdate.class);
	}


	@Test
	public void test() {
		executeUpdate.start();
	}

}
