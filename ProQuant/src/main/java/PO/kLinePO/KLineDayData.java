package PO.kLinePO;

import java.util.Date;

public class KLineDayData {
	Date date;
	Kline kline;
	Ma5	ma5;
	Ma10 ma10;
	Ma20 ma20;
	Macd macd;
	Kdj kdj;
	Rsi rsi;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Kline getKline() {
		return kline;
	}
	public void setKline(Kline kline) {
		this.kline = kline;
	}
	public Ma5 getMa5() {
		return ma5;
	}
	public void setMa5(Ma5 ma5) {
		this.ma5 = ma5;
	}
	public Ma10 getMa10() {
		return ma10;
	}
	public void setMa10(Ma10 ma10) {
		this.ma10 = ma10;
	}
	public Ma20 getMa20() {
		return ma20;
	}
	public void setMa20(Ma20 ma20) {
		this.ma20 = ma20;
	}
	public Macd getMacd() {
		return macd;
	}
	public void setMacd(Macd macd) {
		this.macd = macd;
	}
	public Kdj getKdj() {
		return kdj;
	}
	public void setKdj(Kdj kdj) {
		this.kdj = kdj;
	}
	public Rsi getRsi() {
		return rsi;
	}
	public void setRsi(Rsi rsi) {
		this.rsi = rsi;
	}
	@Override
	public String toString() {
		return "KLineDayData [date=" + date + ", kline=" + kline + ", ma5=" + ma5 + ", ma10=" + ma10 + ", ma20=" + ma20
				+ ", macd=" + macd + ", kdj=" + kdj + ", rsi=" + rsi + "]";
	}
	
	
}
