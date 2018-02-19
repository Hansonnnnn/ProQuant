package data.spider;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.StockDataDao;
import PO.StockData;
import PO.StockDataId;

@Service("BSDS")
public class BasicStockDataSpider implements BasicDataSpiderService {

	@Autowired
	StockDataDao stockDataDao;
	
	public String[] URL=
		{"http://quotes.money.163.com/trade/lsjysj_",
		 ".html?year=",
		 "&season="};
	/**
		 (non-Javadoc)
	 * @see data.spider.BasicDataSpiderService#test(java.lang.String, int, int)
	 */
	@Override
	@Transactional
	public void test(String code,int start,int end) {
		for(int i=start;i<=end;i++){
			System.out.println(i+" is going");
			for (int j = 1; j < 5; j++) {
				sharesCrawl(code, i, j);
			}
		}
	}

	/**
		 (non-Javadoc)
	 * @see data.spider.BasicDataSpiderService#sharesCrawl(java.lang.String, int, int)
	 */
	@Override
	public void sharesCrawl(String code,int year,int season) {
		String url = URL[0]+code+URL[1]+year+URL[2]+season;
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";
		try {
			Document document =  Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
			Elements elements = document.select("table[class=table_bg001 border_box limit_sale]");
			Elements trs = elements.first().select("tr");
			StockData stockData;
			StockDataId stockDataId;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			for (Element tr : trs) {
				Elements tds = tr.select("td");
				if (tds.size()!=0) {
					stockData = new StockData();
					stockDataId = new StockDataId();
					stockDataId.setCode(code);
					try {
						stockDataId.setDate(sf.parse(tds.get(0).text().replaceAll(",","")));
						stockData.setOpen(Double.parseDouble(tds.get(1).text().replaceAll(",","")));
						stockData.setHigh(Double.parseDouble(tds.get(2).text().replaceAll(",","")));
						stockData.setLow(Double.parseDouble(tds.get(3).text().replaceAll(",","")));
						stockData.setClose(Double.parseDouble(tds.get(4).text().replaceAll(",","")));
						stockData.setNetChange(Double.parseDouble(tds.get(5).text().replaceAll(",","")));
						stockData.setChg(Double.parseDouble(tds.get(6).text().replaceAll(",","")));
						stockData.setVolume(Double.parseDouble(tds.get(7).text().replaceAll(",","")));
						stockData.setTurnover(Double.parseDouble(tds.get(8).text().replaceAll(",","")));
						stockData.setAmplitude(Double.parseDouble(tds.get(9).text().replaceAll(",","")));
						stockData.setTurnoverRatio(Double.parseDouble(tds.get(10).text().replaceAll(",","")));
						stockData.setId(stockDataId);
						stockDataDao.merge(stockData);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sharesCrawl(code,year,season);
		}
	}
	
	
}
