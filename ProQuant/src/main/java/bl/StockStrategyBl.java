package bl;

import java.util.*;

import PO.StockData;
import PO.user.UserStrategyRecord;
import VO.UserVO.UserOptionalStocksListVO;
import bl.helper.strategy.BPController;
import blservice.userDataBlService.UserDataService;
import dataservice.StockStrategyService;
import model.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VO.StockPlateVO;
import VO.strategyPageVO.PrecisionVO;
import VO.strategyPageVO.StrategyCallbackVO;
import VO.strategyPageVO.StrategyIndexVO;
import bl.helper.strategy.StrategyController;
import blservice.strategyBlService.StrategyService;
import dataservice.BenchDataService;
import dataservice.StockDataService;
import model.StockPlate;
import model.StrategyType;
import utility.DateHelper;

@Service("StrategyService")
public class StockStrategyBl implements StrategyService {
	@Autowired
	BenchDataService benchService;
	@Autowired
	StockDataService stockDataService;
	@Autowired
	UserDataService userDataService;
	@Autowired
	StockStrategyService strategyService;
	@Override
	public StrategyCallbackVO getCalResultOnExistStrategy(String username, StockPlateVO stockPlateVO, StockPlate stockPlate,
			int possessingDays, int holdDays,int maxHoldNum, Date startDate, Date endDate,StrategyType type) {
		StrategyController strategyController=new StrategyController();
		ArrayList<String> stockList=new ArrayList<>();
		switch(stockPlateVO.getStockPlate()){
            case ALLSTOCKS:
			case CHINEXT:
			case SME:
			case CSI300:
			     stockList=benchService.getStockListOdBench(stockPlateVO.getStockPlate());
		         break;
            case POOLOFUSER:
                 ArrayList<UserOptionalStocksListVO> optionList=userDataService.getUserOptionalStocks(username);
                 for(int i=0;i<optionList.size();i++) {
                     stockList.add(optionList.get(i).getId());
                 }
                 break;
		}
		stockPlateVO.setStocksNameList(stockList);
		return strategyController.getStrategyResult(stockPlateVO,stockPlate, possessingDays,holdDays, maxHoldNum, startDate, endDate, type,benchService,stockDataService);
	}

	@Override
	public StrategyCallbackVO getCalResultOnNewStrategy(StockPlateVO stockPlateVO, StockPlate stockPlate,
			int possessingDays, int maxHoldNum, Date startDate, Date endDate,
			ArrayList<StrategyIndexVO> strategyStandardVOS) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrecisionVO getDataForShowPrecision(String code) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR,2016);//设置年
		gc.set(Calendar.MONTH, 0);//这里0是1月..以此向后推
		gc.set(Calendar.DAY_OF_MONTH, 3);//设置天

		Date testDay=gc.getTime();
		Date today=new Date();
		gc.set(Calendar.YEAR,2015);
		Date preDate=gc.getTime();
		Map<Date,StockData> map=stockDataService.getBasicDateStock(testDay,265,code);
		Map<Date,StockData> map2=stockDataService.getBasicDateStock(preDate,265,code);
		Map<Date,StockData> todays=stockDataService.getBasicDateStock(null,1,code);
		StockData todayDate=new StockData();
		Set<Date> set=todays.keySet();
		Date dates[]=new Date[set.size()];
		set.toArray(dates);

		BPController bpController=new BPController(map,map2,todays.get(dates[0]));

		return bpController.getBpResult();
	}


	@Override
	public ResultMessage addStrategyRecord(String username, String algorithmName, String stockPlate, String baseStandard, String processingDays, String holdDays, String maxHoldNum, String startDate, String endDate) {
		UserStrategyRecord record=new UserStrategyRecord(0,username,StrategyType.valueOf(algorithmName).getOrder(), DateHelper.stringToDate(startDate)
		,DateHelper.stringToDate(endDate),Integer.parseInt(holdDays),Integer.parseInt(processingDays),
				StockPlate.valueOf(startDate).getOrder(),StockPlate.valueOf(baseStandard).getOrder()
		,Integer.parseInt(maxHoldNum));
		strategyService.persist(record);
		return ResultMessage.success;
	}
}
