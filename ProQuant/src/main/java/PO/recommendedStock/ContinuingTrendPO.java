package PO.recommendedStock;


//连续上涨或连续下跌
public class ContinuingTrendPO {
	String stockId;//股票代号
	String stockName;//股票名
	String uptodate;//最新价
	String high;//最高价
	String low;//最低价
	String continuingDays;//持续天数
	String riseOrdown;//持续涨跌幅
	String exchange;//累计换手率
	String industry;//所属行业
	
	
	public ContinuingTrendPO() {
	}


	public ContinuingTrendPO(String stockId, String stockName, String uptodate, String high, String low,
			String continuingDays, String riseOrdown, String exchange, String industry) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.uptodate = uptodate;
		this.high = high;
		this.low = low;
		this.continuingDays = continuingDays;
		this.riseOrdown = riseOrdown;
		this.exchange = exchange;
		this.industry = industry;
	}


	public String getStockId() {
		return stockId;
	}


	public void setStockId(String stockId) {
		this.stockId = stockId;
	}


	public String getStockName() {
		return stockName;
	}


	public void setStockName(String stockName) {
		this.stockName = stockName;
	}


	public String getUptodate() {
		return uptodate;
	}


	public void setUptodate(String uptodate) {
		this.uptodate = uptodate;
	}


	public String getHigh() {
		return high;
	}


	public void setHigh(String high) {
		this.high = high;
	}


	public String getLow() {
		return low;
	}


	public void setLow(String low) {
		this.low = low;
	}


	public String getContinuingDays() {
		return continuingDays;
	}


	public void setContinuingDays(String continuingDays) {
		this.continuingDays = continuingDays;
	}


	public String getRiseOrdown() {
		return riseOrdown;
	}


	public void setRiseOrdown(String riseOrdown) {
		this.riseOrdown = riseOrdown;
	}


	public String getExchange() {
		return exchange;
	}


	public void setExchange(String exchange) {
		this.exchange = exchange;
	}


	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
}
