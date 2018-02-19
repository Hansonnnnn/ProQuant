package PO.recommendedStock;


//向上突破/向下突破
public class breakthroughPO {
	String stockId;//股票代码
	String stockName;//股票名
	String uptodate;//最新价
	String price;//成交价
	String volumn;//成交量
	String riseOrDown;//涨跌幅
	String exchange;//换手率
	public breakthroughPO(String stockId, String stockName, String uptodate, String price, String volumn,
			String riseOrDown, String exchange) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.uptodate = uptodate;
		this.price = price;
		this.volumn = volumn;
		this.riseOrDown = riseOrDown;
		this.exchange = exchange;
	}
	public breakthroughPO() {
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVolumn() {
		return volumn;
	}
	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}
	public String getRiseOrDown() {
		return riseOrDown;
	}
	public void setRiseOrDown(String riseOrDown) {
		this.riseOrDown = riseOrDown;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	
}
