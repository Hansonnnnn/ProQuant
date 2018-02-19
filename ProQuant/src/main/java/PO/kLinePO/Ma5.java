package PO.kLinePO;

public class Ma5 {
	private Double volume;
	private Double avgPrice;
	private Double ccI;
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public Double getCcI() {
		return ccI;
	}
	public void setCcI(Double ccI) {
		this.ccI = ccI;
	}
	@Override
	public String toString() {
		return "ma10 [volume=" + volume + ", avgPrice=" + avgPrice + ", ccI=" + ccI + "]";
	}
	
	
}
