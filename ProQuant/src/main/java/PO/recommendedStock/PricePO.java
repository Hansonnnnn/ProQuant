package PO.recommendedStock;


//量价齐升/量价齐降
public class PricePO {
	String stockId;//股票代码
	String stockName;//股票名
	String uptodate;//最新价
	String days;//量价齐升天数/量价齐降天数
	String stageRiseOrDown;//阶段涨幅
	String exchange;//累计换手率
	String industry;//所属行业
	public PricePO(String stockId, String stockName, String uptodate, String days, String stageRiseOrDown,
			String exchange, String industry) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.uptodate = uptodate;
		this.days = days;
		this.stageRiseOrDown = stageRiseOrDown;
		this.exchange = exchange;
		this.industry = industry;
	}
	public PricePO() {
		super();
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
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getStageRiseOrDown() {
		return stageRiseOrDown;
	}
	public void setStageRiseOrDown(String stageRiseOrDown) {
		this.stageRiseOrDown = stageRiseOrDown;
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
