package PO.kLinePO;

public class Macd {
	private Double diff;
	private Double dea;
	private Double macd;
	public Double getDiff() {
		return diff;
	}
	public void setDiff(Double diff) {
		this.diff = diff;
	}
	public Double getDea() {
		return dea;
	}
	public void setDea(Double dea) {
		this.dea = dea;
	}
	public Double getMacd() {
		return macd;
	}
	public void setMacd(Double macd) {
		this.macd = macd;
	}
	@Override
	public String toString() {
		return "macd [diff=" + diff + ", dea=" + dea + ", macd=" + macd + "]";
	}
	
}
