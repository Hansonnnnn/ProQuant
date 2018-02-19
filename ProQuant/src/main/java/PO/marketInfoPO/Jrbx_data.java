package PO.marketInfoPO;

import java.util.ArrayList;

public class Jrbx_data {

	ArrayList<String> time; //时间
	ArrayList<Double> zdf;	//数值
	public ArrayList<String> getTime() {
		return time;
	}
	public void setTime(ArrayList<String> time) {
		this.time = time;
	}
	public ArrayList<Double> getZdf() {
		return zdf;
	}
	public void setZdf(ArrayList<Double> zdf) {
		this.zdf = zdf;
	}
	@Override
	public String toString() {
		return "Jrbx_data [time=" + time + ", zdf=" + zdf + "]";
	}
	
	
}
