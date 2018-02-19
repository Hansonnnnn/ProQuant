package dataTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import data.update.UserUpdateService;

public class SaveUserYesterdayPrincipalTest {
	
	private static UserUpdateService save;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		save =  context.getBean(UserUpdateService.class);
	}

	@Test
	public void testRun() {
		save.run();
	}

}
