package PO.marketInfoPO;

import java.util.ArrayList;

public class Zdt_data {
	ArrayList<String> zd_time; //时间
	ArrayList<Integer> ztzs;	//涨停数量
	ArrayList<Integer> dtzs;	//跌停数量
	ArrayList<Integer> five;	//五日平均涨停数量
	
	
	public ArrayList<String> getZd_time() {
		return zd_time;
	}
	public void setZd_time(ArrayList<String> zd_time) {
		this.zd_time = zd_time;
	}
	public ArrayList<Integer> getZtzs() {
		return ztzs;
	}
	public void setZtzs(ArrayList<Integer> ztzs) {
		this.ztzs = ztzs;
	}
	public ArrayList<Integer> getDtzs() {
		return dtzs;
	}
	public void setDtzs(ArrayList<Integer> dtzs) {
		this.dtzs = dtzs;
	}
	public ArrayList<Integer> getFive() {
		return five;
	}
	public void setFive(ArrayList<Integer> five) {
		this.five = five;
	}

	
	@Override
	public String toString() {
		return "Zdt_data [zd_time=" + zd_time + ", ztzs=" + ztzs + ", dtzs=" + dtzs + ", five=" + five + "]";
	}
	
	
}
