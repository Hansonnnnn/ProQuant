package dataservice;

import java.util.ArrayList;
import java.util.Date;

import PO.kLinePO.KLineDayData;
import PO.kLinePO.KLineTimeData;

/**
 * @author 凡
 *
 */
public interface KLineDataService {

	/**
	 * @TODO：获取画K线图所需数据
	 * @param code	股票的编号
	 * @param start	获取数据的开始时间，若为null则从当天向前
	 * @param count	获取数据条数, 若为0则默认300条
	 * 
	 * eg：start=null count = 160 ,则取已收盘最新天及之前160天数据
	 * @param fq	是否复权
	 * @return 相关PO的集合，小俊和凯子讨论怎么用
	 * 			若达到小俊要求的数据格式，建议重写VO的toString方法
	 */
	ArrayList<KLineTimeData> getTimeLine(String code);

	ArrayList<KLineDayData> getdayLine(String code, Date start, int count, boolean fq);

	ArrayList<KLineDayData> getweekLine(String code, Date start, int count, boolean fq);

	ArrayList<KLineDayData> getmonthLine(String code, Date start, int count, boolean fq);

}