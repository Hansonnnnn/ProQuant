package dataTest.spiderTest;

import data.spider.KLineDataSpider;
import model.StockPlate;

public class TimeLineSpiderTest {

	public static void main(String[] args) {
		KLineDataSpider spider = new KLineDataSpider();
		spider.getTimeLine(StockPlate.codeOf(StockPlate.CSI300))	;
	}
	
}
