package PO.recommendedStock;


//持续放量/持续缩量
public class ContinuingQuantityPO {
	String stockId;//股票代码
	String stockName;//股票名
	String riseOrDown;//涨跌率
	String uptodate;//最新价
	String volumn;//成交量
	String baseDateVolumn;//基准日成交量
	String days;//放量天数/缩量天数
	String stageRiseOrDown;//阶段涨跌幅
	String industry;//所属行业
	
	
	public ContinuingQuantityPO() {
	}


	public ContinuingQuantityPO(String stockId, String stockName, String riseOrDown, String uptodate, String volumn,
			String baseDateVolumn, String days, String stageRiseOrDown, String industry) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.riseOrDown = riseOrDown;
		this.uptodate = uptodate;
		this.volumn = volumn;
		this.baseDateVolumn = baseDateVolumn;
		this.days = days;
		this.stageRiseOrDown = stageRiseOrDown;
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


	public String getRiseOrDown() {
		return riseOrDown;
	}


	public void setRiseOrDown(String riseOrDown) {
		this.riseOrDown = riseOrDown;
	}


	public String getUptodate() {
		return uptodate;
	}


	public void setUptodate(String uptodate) {
		this.uptodate = uptodate;
	}


	public String getVolumn() {
		return volumn;
	}


	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}


	public String getBaseDateVolumn() {
		return baseDateVolumn;
	}


	public void setBaseDateVolumn(String baseDateVolumn) {
		this.baseDateVolumn = baseDateVolumn;
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


	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
}
