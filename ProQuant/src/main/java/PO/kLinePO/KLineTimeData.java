package PO.kLinePO;

import java.util.Date;

public class KLineTimeData {

	Date date;
	String time;
	double price;
	long volume;
	double avgPrice;
	int ccI;
	double netChangeRatio;
	double preClose;
	double  amount;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public int getCcI() {
		return ccI;
	}
	public void setCcI(int ccI) {
		this.ccI = ccI;
	}
	public double getNetChangeRatio() {
		return netChangeRatio;
	}
	public void setNetChangeRatio(double netChangeRatio) {
		this.netChangeRatio = netChangeRatio;
	}
	public double getPreClose() {
		return preClose;
	}
	public void setPreClose(double preClose) {
		this.preClose = preClose;
	}
	public double  getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "KLineData [date=" + date + ", time=" + time + ", price=" + price + ", volume=" + volume + ", avgPrice="
				+ avgPrice + ", ccI=" + ccI + ", netChangeRatio=" + netChangeRatio + ", preClose=" + preClose
				+ ", amount=" + amount + "]";
	}
	
	
	
}
