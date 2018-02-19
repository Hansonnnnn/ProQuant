package bl.helper.strategy;

import java.util.Date;

import VO.StockPlateVO;
import VO.strategyPageVO.StrategyCallbackVO;
import dataservice.BenchDataService;
import dataservice.StockDataService;
import model.StockPlate;
import model.StrategyType;

public class StrategyController {
	
    public StrategyCallbackVO getStrategyResult(StockPlateVO stockPlateVO, StockPlate stockPlate,
			int possessingDays,int holdDays,int maxHoldNum, Date startDate, Date endDate,StrategyType type,
			BenchDataService benchService,StockDataService stockDataService){
    	Strategy strategy=null;
    	switch (type) {
		case ReversionDriven:
			strategy=new ReversionDrivenStrategy(stockPlateVO, stockPlate, possessingDays,holdDays, maxHoldNum, startDate, endDate, type
					,benchService,stockDataService);
			break;
		case MomentumDriven:
			strategy=new MomentumDrivenStrategy(stockPlateVO, stockPlate, possessingDays,holdDays, maxHoldNum, startDate, endDate, type
					,benchService,stockDataService);
			break;
		default:
			break;
		}
    	return strategy.getResult();
    }
}
