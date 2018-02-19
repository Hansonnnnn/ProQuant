package bl.helper.strategy;

/**
 * Created by xiezhenyu on 2017/6/8.
 */
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import VO.StockPlateVO;
import VO.strategyPageVO.StrategyCallbackVO;
import dataservice.BenchDataService;
import dataservice.StockDataService;
import model.StockPlate;
import model.StrategyType;

public abstract class Strategy {
    StockPlateVO stockPlateVO;
    Date startDate;
    Date endDate;
    StockPlate stockPlate;
    int possessingDays;
    int maxHoldNum;
    StrategyType type;
    int holdDays;
	BenchDataService benchDataService;
	StockDataService stockDataService;
    public Strategy(StockPlateVO stockPlateVO, StockPlate strategyStandard,
                    int possessingDays,int holdDays, int maxHoldNum, Date startDate, Date endDate
            ,StrategyType type,BenchDataService benchService,StockDataService stockDataService){
        this.stockPlateVO=stockPlateVO;
        this.startDate=startDate;
        this.endDate=endDate;
        this.stockPlate=strategyStandard;
        this.possessingDays=possessingDays;
        this.holdDays=holdDays;
        this.maxHoldNum=maxHoldNum;
        this.type=type;
        this.benchDataService=benchService;
        this.stockDataService=stockDataService;
    }

    public abstract StrategyCallbackVO getResult();
}
