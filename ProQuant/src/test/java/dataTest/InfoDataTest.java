package dataTest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import PO.InfoData;

public class InfoDataTest {

	private static SessionFactory sessionFactory;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		sessionFactory = (SessionFactory) context.getBean(SessionFactory.class);
	}
	
	@Test
	public void test(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<InfoData> result = session.createQuery("FROM InfoData where name like '%Ａ'", InfoData.class).list();
		for (InfoData infoData : result) {
			String name = infoData.getName().substring(0,infoData.getName().length()-1).replaceAll(" ","")+"A";
			infoData.setName(name);
			session.update(infoData);
		}
		
		transaction.commit();
		session.close();
	}
}
