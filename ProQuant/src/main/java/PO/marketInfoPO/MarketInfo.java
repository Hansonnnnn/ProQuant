package PO.marketInfoPO;

public class MarketInfo {
	Zdt_data zdt_data;			//表格数据
	Zdfb_data zdfb_data;		//大盘涨跌数据
	Jrbx_data jrbx_data;		//昨日涨停今日收益
	Double dppj_data;			//大盘评分
	public Zdt_data getZdt_data() {
		return zdt_data;
	}
	public void setZdt_data(Zdt_data zdt_data) {
		this.zdt_data = zdt_data;
	}
	public Double getDppj_data() {
		return dppj_data;
	}
	public void setDppj_data(Double dppj_data) {
		this.dppj_data = dppj_data;
	}
	
	
	public Zdfb_data getZdfb_data() {
		return zdfb_data;
	}
	public void setZdfb_data(Zdfb_data zdfb_data) {
		this.zdfb_data = zdfb_data;
	}
	public Jrbx_data getJrbx_data() {
		return jrbx_data;
	}
	public void setJrbx_data(Jrbx_data jrbx_data) {
		this.jrbx_data = jrbx_data;
	}
	@Override
	public String toString() {
		return "MarketInfo [zdt_data=" + zdt_data + ", zdfb_data=" + zdfb_data + ", jrbx_data=" + jrbx_data
				+ ", dppj_data=" + dppj_data + "]";
	}
	
	
	
}
