package bl.helper.strategy;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import PO.StockData;
import VO.StockPlateVO;
import VO.strategyPageVO.EarningsCircleVO;
import VO.strategyPageVO.EarningsLineVO;
import VO.strategyPageVO.ParamDataVO;
import VO.strategyPageVO.StrategyCallbackVO;
import VO.strategyPageVO.StrategyCompareVO;
import VO.strategyPageVO.StrategyEvalutingVO;
import bl.helper.StockInfoHelper;
import dataservice.BenchDataService;
import dataservice.KLineDataService;
import dataservice.StockDataService;
import dataservice.StockStrategyService;
import model.StockPlate;
import model.StrategyType;
import utility.DateHelper;

/**
 * 
 * @author yk
 *均值回归策略类
 */
public class ReversionDrivenStrategy extends Strategy {

	public ReversionDrivenStrategy(StockPlateVO stockPlateVO,
			StockPlate stockPlate
			, int possessingDays,int holdDays,int maxHoldNum, Date startDate, Date endDate, 
			StrategyType type,BenchDataService benchDataService,StockDataService stockDataService) {
		super(stockPlateVO, stockPlate, possessingDays, holdDays,maxHoldNum, startDate, endDate, type
				,benchDataService,stockDataService);
	}

	@Override
	public StrategyCallbackVO getResult() {
		//从数据库中得到所有的数据
		Map<String, Map<Date, StockData>> allStockPOs = new LinkedHashMap<>();
		Date date = startDate;
		for (int i = 0; i < (possessingDays + 10); i++) {
			date = DateHelper.add(date, -1);
		}
		for (int i = 0; i < stockPlateVO.getStocksNameList().size(); i++) {
			String code = stockPlateVO.getStocksNameList().get(i);
			Map<Date, StockData> temp = stockDataService.getBasicDateStock(date, endDate, code);
			allStockPOs.put(code, temp);
		}
		//从map中得到日期
		DatesAndBase db = StrategyHelper.stadardEarning(startDate, endDate, holdDays, stockPlate, benchDataService);
		Date baseDates[] = db.getDates();//基本的日期
		Set<Date> dateSet = allStockPOs.get(stockPlateVO.getStocksNameList().get(0)).keySet();
		Date dates[] = new Date[dateSet.size()];
		dateSet.toArray(dates);
		int size = dates.length;//数据的大小
		int small = baseDates.length;
		int count = size - small;//用来记录下一个持有期

		Map<Date, Double> strategyProfit = new LinkedHashMap<>();

		while (count < size - 1) {
			int temp = count;//用来记录上一个持有期结束日期
			count += holdDays;
			if (count >= size) {
				count = size - 1;
			}

			//用来进行排序筛选的list
			ArrayList<StrategyCompareVO> lists = new ArrayList<>();
			for (int i = 0; i < stockPlateVO.getStocksNameList().size(); i++) {
				String code = stockPlateVO.getStocksNameList().get(i);
				Map<Date, StockData> shaixuan = allStockPOs.get(code);

				//如果当天不交易就跳过
				if (!shaixuan.containsKey(dates[temp])) {
					continue;
				}
				//首先去掉涨停跌停的股票
				if (shaixuan.get(dates[temp]).getChg() < 10 && shaixuan.get(dates[temp]).getChg() > -10) {
					StrategyCompareVO strategyCompare = new StrategyCompareVO(shaixuan.get(dates[temp]).getId().getCode(), 0);
					double today = shaixuan.get(dates[temp]).getClose();
					double last=0;
					int actAvg=0;
					for(int num=0;num<possessingDays;num++){
						if(shaixuan.containsKey(dates[temp - num])){
							last+=shaixuan.get(dates[temp - num]).getClose();
							actAvg++;
						}else{
							continue;
						}
					}
					strategyCompare.value = (today - last) / last;
					lists.add(strategyCompare);
				}
			}

			//算出已经排好序的可以买入的股票
			ArrayList<String> resultList = StrategyHelper.getHoldStocks(lists, maxHoldNum);
			double profit = 0.0;
			int hold = 0;//真正持有的个数
			for (int i = 0; i < resultList.size(); i++) {
				double todPro = 0;
				double lastPro = 0;
				if (allStockPOs.get(resultList.get(i)).get(dates[temp]) != null && allStockPOs.get(resultList.get(i)).get(dates[count]) != null) {
					todPro = allStockPOs.get(resultList.get(i)).get(dates[count]).getClose();
					lastPro = allStockPOs.get(resultList.get(i)).get(dates[temp]).getClose();
				} else {
					continue;
				}

				profit += (todPro - lastPro) / lastPro;
				hold++;
			}
			profit /= hold;
			strategyProfit.put(dates[count], profit);
		}
//		EarningsLineVO earningsLineVO=new EarningsLineVO();
//		earningsLineVO.setBaseEarningsData(db.getBaseearning());
//		earningsLineVO.setStrategyEarningsData(strategyProfit);
//
//		EarningsCircleVO earningCircleVO=StrategyHelper.getEarningsCircleVO(earningsLineVO);
//
//		StrategyEvalutingVO strategyEvalutingVO=new StrategyEvalutingVO();
//		BaseAndStrategyParam b=StrategyHelper.getBaseAndStrategyParam(earningsLineVO,holdDays,startDate);
//		ParamDataVO paramStrategyDataVO=b.getParamStrategyDataVO();
//		ParamDataVO paramBaseDataVO=b.getParamBaseDataVO();
		EarningsLineVO earningsLineVO=new EarningsLineVO();
		earningsLineVO.setBaseEarningsData(db.getBaseearning());
		earningsLineVO.setStrategyEarningsData(strategyProfit);

		EarningsCircleVO earningCircleVO=StrategyHelper.getEarningsCircleVO(earningsLineVO);

//		StrategyEvalutingVO strategyEvalutingVO=new StrategyEvalutingVO();

		BaseAndStrategyParam b=StrategyHelper.getBaseAndStrategyParam(earningsLineVO,holdDays,startDate);
		ParamDataVO paramStrategyDataVO=b.getParamStrategyDataVO();
		ParamDataVO paramBaseDataVO=b.getParamBaseDataVO();
		StrategyEvalutingVO strategyEvalutingVO=StrategyHelper.getSEVO(earningCircleVO,earningsLineVO,paramStrategyDataVO,paramBaseDataVO);
		for(Map.Entry<Date,Double> entry: strategyProfit.entrySet()){
			strategyProfit.put(entry.getKey(),100*StockInfoHelper.format(entry.getValue()));
			db.getBaseearning().put(entry.getKey(),100*StockInfoHelper.format(db.getBaseearning().get(entry.getKey())));
		}
		earningsLineVO.setStrategyEarningsData(strategyProfit);
		earningsLineVO.setBaseEarningsData(db.getBaseearning());
		StrategyCallbackVO strategyCallbackVO=new StrategyCallbackVO(earningsLineVO, earningCircleVO,
				strategyEvalutingVO, paramStrategyDataVO, paramBaseDataVO);
		return strategyCallbackVO;
	}

}
