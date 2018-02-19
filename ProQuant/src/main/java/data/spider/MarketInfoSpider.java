package data.spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import PO.marketInfoPO.MarketInfo;
import dataservice.MarketInfoService;

@Service("MarketInfoService")
public class MarketInfoSpider implements MarketInfoService{
	private static final String url = "http://q.10jqka.com.cn/api.php?t=indexflash&";
	
	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";

	@Override
	public MarketInfo getCurrentMarketInfo() {
		
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonstr = document.body().text();
		
		Gson gson = new Gson();
		MarketInfo marketInfo = gson.fromJson(jsonstr, MarketInfo.class);
	
		return marketInfo;
	}
}
