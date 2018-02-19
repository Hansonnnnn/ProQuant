package data.spider.update;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimerTask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PO.StockData;
import PO.StockDataId;

@Service("BSDUS")
public class BasicStockDataUpdateSpider extends TimerTask implements BasicDataUpdateSpiderService {

	@Autowired
	SessionFactory sessionFactory;

	public String[] URL = { "http://quotes.money.163.com/trade/lsjysj_", ".html" };

	@Override
	public void sharesCrawl(String code) {
		String url = URL[0] + code + URL[1];
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";

		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent", userAgent).timeout(3000).get();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			sharesCrawl(code);
		}
		if (document==null) {
			return;
		}
		Elements elements = document.select("table[class=table_bg001 border_box limit_sale]");
		if (elements==null||elements.size()==0) {
			return;
		}
		Elements trs = elements.first().select("tr");
		if (trs==null||trs.size()==1) {
			return;
		}
		trs.remove(0);
		StockData stockData;
		StockDataId stockDataId;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Elements tds = trs.first().select("td");
		/*if (trs.size()==1) {
			return;
		}
		Elements tds = trs.get(1).select("td");*/
		if (tds.size() != 0) {
			stockData = new StockData();
			stockDataId = new StockDataId();
			stockDataId.setCode(code);
			try {
				stockDataId.setDate(sf.parse(tds.get(0).text().replaceAll(",", "")));
				stockData.setOpen(Double.parseDouble(tds.get(1).text().replaceAll(",", "")));
				stockData.setHigh(Double.parseDouble(tds.get(2).text().replaceAll(",", "")));
				stockData.setLow(Double.parseDouble(tds.get(3).text().replaceAll(",", "")));
				stockData.setClose(Double.parseDouble(tds.get(4).text().replaceAll(",", "")));
				stockData.setNetChange(Double.parseDouble(tds.get(5).text().replaceAll(",", "")));
				stockData.setChg(Double.parseDouble(tds.get(6).text().replaceAll(",", "")));
				stockData.setVolume(Double.parseDouble(tds.get(7).text().replaceAll(",", "")));
				stockData.setTurnover(Double.parseDouble(tds.get(8).text().replaceAll(",", "")));
				stockData.setAmplitude(Double.parseDouble(tds.get(9).text().replaceAll(",", "")));
				stockData.setTurnoverRatio(Double.parseDouble(tds.get(10).text().replaceAll(",", "")));
				stockData.setId(stockDataId);
				Session session = sessionFactory.openSession();
				Transaction transaction = session.getTransaction();
				transaction.begin();
				session.merge(stockData);
				transaction.commit();
				session.close();
				// System.out.println(stockData);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void run() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<List> list = session.createQuery("select new list(I.id,I.code) from InfoData I order by I.id").list();
		for (List temp : list) {
			System.out.println("now id = " + temp.get(0));
			sharesCrawl((String) temp.get(1));
		}
		session.close();
	}

}
