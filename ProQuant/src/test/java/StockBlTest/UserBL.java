package StockBlTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import VO.UserVO.AccountPageTotalVO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import VO.UserVO.StrategyRecordVO;
import VO.UserVO.UserOptionalStocksListVO;
import blservice.strategyBlService.StrategyService;
import blservice.userDataBlService.UserDataService;

public class UserBL {

	private static UserDataService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		service = (UserDataService) context.getBean("UserDataService");
	}
	
	@Test
	public void userTest(){
		/*System.out.println("bbb");
		ArrayList<StrategyRecordVO> list=service.getStrategyRecordVO("xie");
		if(list==null){
			System.out.println("aaaa");
			return ;
		}*/

		System.out.println(service.getUserAccountData("xie").getAccountVO());

	}
	@Test
	public void userInfoTest(){
		AccountPageTotalVO a=service.getUserAccountData("xie");
		System.out.println(a.getAccountVO().getTotalProperty());
	}
	@Test
    public void userDeleteStock(){
	    ArrayList<String> list=new ArrayList<>();
	    list.add("603799");
        System.out.println(service.deleteUserOptionalStocks(list,"xie"));
	}

	@Test
	public void usergetOption(){
    	ArrayList<UserOptionalStocksListVO> list=service.getUserOptionalStocks("xie");
		System.out.println(list.size());
	}
}
