package data.spider.update;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.BenchCurrentDataDao;
import PO.BenchCurrentData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("CBDUS")
public class CurrentBenchDataUpdateSpider extends TimerTask implements CurrentDataUpdateSpiderService {
	
	private String url = "https://gupiao.baidu.com/api/rails/stockbasicbatch?from=pc&os_ver=1&cuid=xxx&vv=100&format=json&stock_code=sz399005,sh000300,sz399006";
	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	
	@Override
	public void updateCurrentData() {
		Calendar calendar = Calendar.getInstance();
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 9);
		start.set(Calendar.MINUTE,15);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 15);
		end.set(Calendar.MINUTE,0);
		
		if (calendar.before(start)||calendar.after(end)) {
			return;
		}
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonstr = document.body().text();
		JSONObject json = JSONObject.fromObject(jsonstr);	
		JSONArray jsonArray =  (JSONArray) json.get("data");
		int index = 1;
		for (Object object : jsonArray) {
			json = (JSONObject) object;
			BenchCurrentData benchCurrentData = new BenchCurrentData();
			benchCurrentData.setIndex(index);
			index++;
			benchCurrentData.setTrade(json.getDouble("close"));
			benchCurrentData.setCode(json.getString("stockCode"));
			benchCurrentData.setName(json.getString("stockName"));
			benchCurrentData.setHigh(json.getDouble("high"));
			benchCurrentData.setLow(json.getDouble("low"));
			benchCurrentData.setNetChange(json.getDouble("netChange"));
			benchCurrentData.setChg(json.getDouble("netChangeRatio"));
			benchCurrentData.setVolume(json.getLong("volume"));
			benchCurrentData.setAmplitudeRatio(json.getDouble("amplitudeRatio"));
			benchCurrentData.setTurnoverratio(json.getDouble("turnoverRatio"));
			benchCurrentData.setSettlement(json.getDouble("preClose"));
			benchCurrentData.setOpen(json.getDouble("open"));
			benchCurrentData.setDate(new Date());
			Session session= sessionFactory.openSession();
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.update(benchCurrentData);
			transaction.commit();
			session.close();
			
		}
	}

	@Override
	public void run() {
		updateCurrentData();
	}

}
