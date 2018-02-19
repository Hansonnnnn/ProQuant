package daoTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.dao.InfoDataDao;

public class InfoDataDaoTest {

	private static InfoDataDao infoDataDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		infoDataDao = (InfoDataDao) context.getBean("InfoDataDao");
	}

	@Test
	public void test() {
		System.out.println(infoDataDao.queryByHql("600184"));
	//	System.out.println(infoDataDao.getCode("光电股份"));
	}

}
