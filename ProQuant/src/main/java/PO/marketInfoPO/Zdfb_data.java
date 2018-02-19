package PO.marketInfoPO;

import java.util.ArrayList;

/**
 * @author 凡
 * 大盘涨跌数据情况
 */
public class Zdfb_data {
	
	/**
	 *index 0-9 分别为 
	 *？<-8% | -8%<？<-6% | -6%<？<-4% | -4%<？<-2% | -2%<？<-0% |etc...| 
	 */
	ArrayList<Integer> zdfb;
	//上涨股票数量
	int znum;
	//下跌股票数量
	int dnum;
	public ArrayList<Integer> getZdfb() {
		return zdfb;
	}
	public void setZdfb(ArrayList<Integer> zdfb) {
		this.zdfb = zdfb;
	}
	public int getZnum() {
		return znum;
	}
	public void setZnum(int znum) {
		this.znum = znum;
	}
	public int getDnum() {
		return dnum;
	}
	public void setDnum(int dnum) {
		this.dnum = dnum;
	}
	@Override
	public String toString() {
		return "Zdfb_data [zdfb=" + zdfb + ", znum=" + znum + ", dnum=" + dnum + "]";
	}
	
}
