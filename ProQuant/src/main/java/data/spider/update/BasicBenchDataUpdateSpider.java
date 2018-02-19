package data.spider.update;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.BenchDataDao;
import PO.BenchData;
import PO.BenchDataId;
@Service("BBDUS")
public class BasicBenchDataUpdateSpider extends TimerTask implements BasicDataUpdateSpiderService {
	
	@Autowired 
	SessionFactory sessionFactory;
	public String[] URL=
		{"http://quotes.money.163.com/trade/lsjysj_zhishu_",
		 ".html"};
	
	/**
		 (non-Javadoc)
	 * @see data.spider.update.BasicDataUpdateSpiderService#sharesCrawl(java.lang.String)
	 */
	
	@Override
	public void sharesCrawl(String code) {
		String url = URL[0]+code+URL[1];
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";
		try {
			Document document =  Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
			Elements elements = document.select("table[class=table_bg001 border_box limit_sale]");
			Elements trs = elements.first().select("tr");
			trs.remove(0);
			BenchData benchData;
			BenchDataId benchDataId;
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			Elements tds = trs.first().select("td");
//			Elements tds = trs.get(1).select("td");
			
			if (tds.size()!=0) {
				benchData = new BenchData();
				benchDataId = new BenchDataId();
				benchDataId.setCode(code);
				try {
					benchDataId.setDate(sf.parse(tds.get(0).text().replaceAll(",","")));
					benchData.setOpen(Double.parseDouble(tds.get(1).text().replaceAll(",","")));
					benchData.setHigh(Double.parseDouble(tds.get(2).text().replaceAll(",","")));
					benchData.setLow(Double.parseDouble(tds.get(3).text().replaceAll(",","")));
					benchData.setClose(Double.parseDouble(tds.get(4).text().replaceAll(",","")));
					benchData.setNetChange(Double.parseDouble(tds.get(5).text().replaceAll(",","")));
					benchData.setChg(Double.parseDouble(tds.get(6).text().replaceAll(",","")));
					benchData.setVolume(Double.parseDouble(tds.get(7).text().replaceAll(",","")));
					benchData.setTurnover(Double.parseDouble(tds.get(8).text().replaceAll(",","")));
					benchData.setId(benchDataId);
					Session session= sessionFactory.openSession();
					Transaction transaction = session.getTransaction();
					transaction.begin();
					session.merge(benchData);
					transaction.commit();
					session.close();
					//System.out.println(benchData);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sharesCrawl(code);
		}
	}

	@Override
	public void run() {
		List<String> basicBenchCodes =  Arrays.asList("000300","399005","399006");
		for (String string : basicBenchCodes) {
			sharesCrawl(string);
		}
	}

	
}
