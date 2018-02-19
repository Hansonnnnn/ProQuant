package PO.kLinePO;

public class Rsi {
	private Double rsi1;
	private Double rsi2;
	private Double rsi3;
	public Double getRsi1() {
		return rsi1;
	}
	public void setRsi1(Double rsi1) {
		this.rsi1 = rsi1;
	}
	public Double getRsi2() {
		return rsi2;
	}
	public void setRsi2(Double rsi2) {
		this.rsi2 = rsi2;
	}
	public Double getRsi3() {
		return rsi3;
	}
	public void setRsi3(Double rsi3) {
		this.rsi3 = rsi3;
	}
	@Override
	public String toString() {
		return "rsi [rsi1=" + rsi1 + ", rsi2=" + rsi2 + ", rsi3=" + rsi3 + "]";
	}
	
}
