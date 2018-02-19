package PO.recommendedStock;

//创新高或者创新低
public class PeakPO {
	String stockId;//股票代号
	String stockName;//股票名
	String riseOrDown;//涨跌幅
	String exchange;//换手率
	String uptodate;//最新价
	String high;//前期高点/低点
	String date;//前期高点日期/前期低点日期
	public PeakPO(){
		
	}
	
	public PeakPO(String stockId, String stockName, String riseOrDown, String exchange, String uptodate, String high,
			String date) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.riseOrDown = riseOrDown;
		this.exchange = exchange;
		this.uptodate = uptodate;
		this.high = high;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
