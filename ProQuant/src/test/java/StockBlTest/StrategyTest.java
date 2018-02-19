package StockBlTest;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import VO.strategyPageVO.PrecisionVO;
import org.hibernate.boot.jaxb.SourceType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import VO.StockPlateVO;
import VO.strategyPageVO.StrategyCallbackVO;
import blservice.strategyBlService.StrategyService;
import model.StockPlate;
import model.StrategyType;

public class StrategyTest {
	private static StrategyService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		service = (StrategyService) context.getBean("StrategyService");
	}
	
	@Test
	public void mTest(){
		StockPlateVO stockPlateVO=new StockPlateVO(null,null);
		stockPlateVO.setStockPlate(StockPlate.CSI300);
		stockPlateVO.setStocksNameList(null);
		Date startDate=new Date("2016/6/13");
		Date endDate=new Date("2016/9/13");
		System.out.println(startDate);
		StrategyCallbackVO strategyCallbackVO=service.getCalResultOnExistStrategy("xie",stockPlateVO, StockPlate.CSI300,
				10, 10,5, startDate, endDate, StrategyType.ReversionDriven);

//		System.out.println(strategyCallbackVO.getParamBaseDataVO().getAlpha());
		System.out.println(strategyCallbackVO.getEarningCircleVO().getWinRate());
//		System.out.println(strategyCallbackVO.getEarningsLineVO().getStrategyEarningsData());
		System.out.println(strategyCallbackVO.getEarningCircleVO().getPeCircleNum());
		System.out.println(strategyCallbackVO.getEarningCircleVO().getNeData().size());
		System.out.println(strategyCallbackVO.getEarningCircleVO().getPeData().size());
		System.out.println(strategyCallbackVO.getParamStrategyDataVO().getAlpha());
		System.out.println(strategyCallbackVO.getEarningsLineVO().getStrategyEarningsData().size());
//        System.out.println(strategyCallbackVO.get);
//        for(Map.Entry<Date,Double> entry:strategyCallbackVO.getEarningsLineVO().getStrategyEarningsData().entrySet()){
//			System.out.println(entry.getKey().toLocaleString()+"  "+entry.getValue());
//		}
//		for(Map.Entry<Date,Double> entry:strategyCallbackVO.getEarningsLineVO().getBaseEarningsData().entrySet()){
//			System.out.println(entry.getKey().toLocaleString()+"  "+entry.getValue());
//		}
//		System.out.println(strategyCallbackVO.getEarningCircleVO().getNeData().size()+" "+strategyCallbackVO.getEarningCircleVO().getPeData().size());
//		for(Map.Entry<Integer,Integer> entry:strategyCallbackVO.getEarningCircleVO().getNeData().entrySet()){
//			System.out.println(entry.getKey()+"      "+entry.getValue());
//		}
//		System.out.println("++++++++++++++");
//		for(Map.Entry<Integer,Integer> entry:strategyCallbackVO.getEarningCircleVO().getPeData().entrySet()){
//			System.out.println(entry.getKey()+"      "+entry.getValue());
//		}
		System.out.println(strategyCallbackVO.getStrategyEvalutingVO().getAnti_riskValue());
		System.out.println(strategyCallbackVO.getStrategyEvalutingVO().getEarningsValue());
		System.out.println(strategyCallbackVO.getStrategyEvalutingVO().getMobilityValue());
		System.out.println(strategyCallbackVO.getStrategyEvalutingVO().getRealPlateValue());
		System.out.println(strategyCallbackVO.getStrategyEvalutingVO().getRobustnessValue());
	}
	@Test
	public void bptest() {
		PrecisionVO precisionVO = service.getDataForShowPrecision("603799");
		for(Map.Entry<Date,Double> entry:precisionVO.getCloseSet().entrySet()){
			System.out.println(entry.getValue()+"   "+precisionVO.getBaseCloseSet().get(entry.getKey()));
		}
		System.out.println(precisionVO.getChg());
		System.out.println(precisionVO.isIncreaseLabel());
		System.out.println(precisionVO.getRate());
	}

	@Test
	public void test1(){
		Date date=new Date();
		Date date2=new Date(date.getTime());
		System.out.println(date==date2);
	}
}
