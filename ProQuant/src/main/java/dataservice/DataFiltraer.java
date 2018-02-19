package dataservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import VO.StockPlateVO;
import VO.strategyPageVO.StrategyIndexVO;
import model.StrategyIndex;
/**
 * 
 * @author yk
 *  这个借口主要是用来筛选和计算（主要是求和）数据
 */

public interface DataFiltraer{
	
	/**
	 * 获取当天筛选过后的股票名称
	 * @param date 开始的日期 如果是节假日就取下一天的
	 * @param strategyStandardVOS 需要比较的指标
	 * @param stockPlateVO 股票池
	 * @return 返回股票名的list
	 */
        public ArrayList<String> filtrateData(Date date, StockPlateVO stockPlateVO,ArrayList<StrategyIndexVO> strategyStandardVOS);
        
        /**
         * 用于计算一段时间内list的某个属性的均值
         * @param startDate 开始时间
         * @param endDate  结束日期
         * @param stockList 需要计算收盘价的股票list
         * @param index 需要求和的属性
         * @return key是code，Double是均值
         */
        public Map<String ,Double> calculateAverageData(Date startDate,Date endDate,ArrayList<String> stockList,StrategyIndex index);
       
        /**
         * 用于计算一段时间内list的某个属性的结束日期减去开始日期的差值
         * @param startDate 需要判断这天是否有数据，没有就用下一天的
         * @param endDate 需要判断这天是否有数据
         * @param stockList 需要计算收盘价的股票list
         * @param index 属性
         * @return key是均值，double是计算后的差值
         */
        public Map<String,Double> calculateSubData(Date startDate,Date endDate,ArrayList<String> stockList,StrategyIndex index);
}
