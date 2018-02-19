package PO;

import java.util.ArrayList;

public class StockScore {

	private double analyzeNum; //分数
	private int beatNum; //打败股票数（%）
	/**
	 * index 0		1		2		3		4	
	 * 含义 技术		资金	消息	行业	基本
	 */
	private ArrayList<Double> stars;
	public double getAnalyzeNum() {
		return analyzeNum;
	}
	public void setAnalyzeNum(double analyzeNum) {
		this.analyzeNum = analyzeNum;
	}
	public int getBeatNum() {
		return beatNum;
	}
	public void setBeatNum(int beatNum) {
		this.beatNum = beatNum;
	}
	public ArrayList<Double> getStars() {
		return stars;
	}
	public void setStars(ArrayList<Double> stars) {
		this.stars = stars;
	}
	@Override
	public String toString() {
		return "StockScore [analyzeNum=" + analyzeNum + ", beatNum=" + beatNum + ", stars=" + stars + "]";
	}
	
	
}
