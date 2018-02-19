package PO.kLinePO;

public class Kline {
	
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double volume;
	private Double amount;
	private Double ccI;
	private Double preClose;
	private Double netChangeRatio;
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getCcI() {
		return ccI;
	}
	public void setCcI(Double ccI) {
		this.ccI = ccI;
	}
	public Double getPreClose() {
		return preClose;
	}
	public void setPreClose(Double preClose) {
		this.preClose = preClose;
	}
	public Double getNetChangeRatio() {
		return netChangeRatio;
	}
	public void setNetChangeRatio(Double netChangeRatio) {
		this.netChangeRatio = netChangeRatio;
	}
	@Override
	public String toString() {
		return "Kline [open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume
				+ ", amount=" + amount + ", ccI=" + ccI + ", preClose=" + preClose + ", netChangeRatio="
				+ netChangeRatio + "]";
	}
	
	
}
