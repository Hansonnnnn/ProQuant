package VO;

import java.util.Date;
import java.util.Map;

public class StockForCompare {
    Map<String , Double> timeValue;//分时图，string为时间，时间从早上9点15开始 时间格式为 小时-分钟
	Map<Date, Double> dayIncrease;//每天的涨跌幅，3个月左右
    Map<Date, Double> dayClose;//每天的收盘价
    
    double weekIncrease;//周涨跌幅
    double mouthIncrease;//月涨跌幅
    double threeMouthIncrease;//三个月涨跌幅
    double sixMouthIncrease;//半年涨跌幅
    double yearIncrease;//一年涨跌幅
    double marketProfitRate;//市盈率
    double marketCleanRate;//市净率
    double marketNowRate;//实现率
    double marketSellRate;//市销率
    
    double everyStockProfit;//每股收益
    double saleCleanProfit;//销售净利润
    double propertyEarningRate;//资产收益率
    double propertyIncurRate;//资产负债率
    double flowRate;//流动比率
    double MajorProfit;//主营收入
    double cleanProfit;//净利润
    double allProfit;//总资产
    public StockForCompare(){
    	
    }
    
    public StockForCompare(Map<String, Double> timeValue, Map<Date, Double> dayIncrease, Map<Date, Double> dayClose,
			double weekIncrease, double mouthIncrease, double threeMouthIncrease, double sixMouthIncrease,
			double yearIncrease, double marketProfitRate, double marketCleanRate, double marketNowRate,
			double marketSellRate, double everyStockProfit, double saleCleanProfit, double propertyEarningRate,
			double propertyIncurRate, double flowRate, double majorProfit, double cleanProfit, double allProfit) {
    	
		this.timeValue = timeValue;
		this.dayIncrease = dayIncrease;
		this.dayClose = dayClose;
		this.weekIncrease = weekIncrease;
		this.mouthIncrease = mouthIncrease;
		this.threeMouthIncrease = threeMouthIncrease;
		this.sixMouthIncrease = sixMouthIncrease;
		this.yearIncrease = yearIncrease;
		this.marketProfitRate = marketProfitRate;
		this.marketCleanRate = marketCleanRate;
		this.marketNowRate = marketNowRate;
		this.marketSellRate = marketSellRate;
		this.everyStockProfit = everyStockProfit;
		this.saleCleanProfit = saleCleanProfit;
		this.propertyEarningRate = propertyEarningRate;
		this.propertyIncurRate = propertyIncurRate;
		this.flowRate = flowRate;
		MajorProfit = majorProfit;
		this.cleanProfit = cleanProfit;
		this.allProfit = allProfit;
	}
    
    
	public Map<String, Double> getTimeValue() {
		return timeValue;
	}
	public void setTimeValue(Map<String, Double> timeValue) {
		this.timeValue = timeValue;
	}
	public Map<Date, Double> getDayIncrease() {
		return dayIncrease;
	}
	public void setDayIncrease(Map<Date, Double> dayIncrease) {
		this.dayIncrease = dayIncrease;
	}
	public Map<Date, Double> getDayClose() {
		return dayClose;
	}
	public void setDayClose(Map<Date, Double> dayClose) {
		this.dayClose = dayClose;
	}
	public double getWeekIncrease() {
		return weekIncrease;
	}
	public void setWeekIncrease(double weekIncrease) {
		this.weekIncrease = weekIncrease;
	}
	public double getMouthIncrease() {
		return mouthIncrease;
	}
	public void setMouthIncrease(double mouthIncrease) {
		this.mouthIncrease = mouthIncrease;
	}
	public double getThreeMouthIncrease() {
		return threeMouthIncrease;
	}
	public void setThreeMouthIncrease(double threeMouthIncrease) {
		this.threeMouthIncrease = threeMouthIncrease;
	}
	public double getSixMouthIncrease() {
		return sixMouthIncrease;
	}
	public void setSixMouthIncrease(double sixMouthIncrease) {
		this.sixMouthIncrease = sixMouthIncrease;
	}
	public double getYearIncrease() {
		return yearIncrease;
	}
	public void setYearIncrease(double yearIncrease) {
		this.yearIncrease = yearIncrease;
	}
	public double getMarketProfitRate() {
		return marketProfitRate;
	}
	public void setMarketProfitRate(double marketProfitRate) {
		this.marketProfitRate = marketProfitRate;
	}
	public double getMarketCleanRate() {
		return marketCleanRate;
	}
	public void setMarketCleanRate(double marketCleanRate) {
		this.marketCleanRate = marketCleanRate;
	}
	public double getMarketNowRate() {
		return marketNowRate;
	}
	public void setMarketNowRate(double marketNowRate) {
		this.marketNowRate = marketNowRate;
	}
	public double getMarketSellRate() {
		return marketSellRate;
	}
	public void setMarketSellRate(double marketSellRate) {
		this.marketSellRate = marketSellRate;
	}
	public double getEveryStockProfit() {
		return everyStockProfit;
	}
	public void setEveryStockProfit(double everyStockProfit) {
		this.everyStockProfit = everyStockProfit;
	}
	public double getSaleCleanProfit() {
		return saleCleanProfit;
	}
	public void setSaleCleanProfit(double saleCleanProfit) {
		this.saleCleanProfit = saleCleanProfit;
	}
	public double getPropertyEarningRate() {
		return propertyEarningRate;
	}
	public void setPropertyEarningRate(double propertyEarningRate) {
		this.propertyEarningRate = propertyEarningRate;
	}
	public double getPropertyIncurRate() {
		return propertyIncurRate;
	}
	public void setPropertyIncurRate(double propertyIncurRate) {
		this.propertyIncurRate = propertyIncurRate;
	}
	public double getFlowRate() {
		return flowRate;
	}
	public void setFlowRate(double flowRate) {
		this.flowRate = flowRate;
	}
	public double getMajorProfit() {
		return MajorProfit;
	}
	public void setMajorProfit(double majorProfit) {
		MajorProfit = majorProfit;
	}
	public double getCleanProfit() {
		return cleanProfit;
	}
	public void setCleanProfit(double cleanProfit) {
		this.cleanProfit = cleanProfit;
	}
	public double getAllProfit() {
		return allProfit;
	}
	public void setAllProfit(double allProfit) {
		this.allProfit = allProfit;
	}
    
}
