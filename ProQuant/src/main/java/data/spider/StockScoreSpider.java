package data.spider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import PO.StockScore;
import dataservice.StockScoreService;
@Service("StockScoreService")
public class StockScoreSpider implements StockScoreService{
	
	private static final String urlprefix = "http://stockpage.10jqka.com.cn/";
	
	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";

	@Override
	public StockScore getStockScore(String code) {
		String url = urlprefix+code;
		StockScore stockScore = new StockScore();
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements analyzeNum = document.select("span[class=analyze-num]");
		stockScore.setAnalyzeNum(Double.parseDouble(analyzeNum.first().text()));
		
		Elements analyzeTips = document.select("span[class=analyze-tips]");
		String str = analyzeTips.first().text();
		int index = str.indexOf('%');
		String temp = str.substring(index-2, index);
		stockScore.setBeatNum(Integer.parseInt(temp));
		ArrayList<Double> list = new ArrayList<>();
		Elements analyzeStars = document.select("div[class=analyze-stars]");
		for (Element element : analyzeStars) {
			Element iElement = element.select("i[class=cred]").first();
			list.add(Double.parseDouble(iElement.text()));
		}
		
		stockScore.setStars(list);
		
		 
		return stockScore;
	}

}
