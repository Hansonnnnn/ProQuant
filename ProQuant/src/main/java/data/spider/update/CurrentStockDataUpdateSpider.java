package data.spider.update;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import PO.StockCurrentData;
import net.sf.json.JSONArray;

@Service("CSDUS")
public class CurrentStockDataUpdateSpider extends TimerTask implements CurrentDataUpdateSpiderService{

	@Autowired
	private StockUpdateUtils utils;
	
	private String hs_a_url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?num=100&node=hs_a&page=";
	private String hs_b_url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?num=100&node=hs_b";
	private String shfxjs_url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?num=100&node=shfxjs";
	
	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";

	
	
	private static Map<String,StockCurrentData> result;
	
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
			//return;
		}
		
		Document document = null;
		ArrayList<StockCurrentData> result = new ArrayList<>();
		try {
			for (int i = 1; i <33 ; i++) {
				document = Jsoup.connect(hs_a_url+i).header("User-Agent",userAgent).timeout(5000).get();
				String jsonstr = document.body().text();
				JSONArray jsonArray = JSONArray.fromObject(jsonstr);
				Collection<StockCurrentData> temp = JSONArray.toCollection(jsonArray, StockCurrentData.class);
				result.addAll(temp);
			}
			//updateByJDBC(result);
			//updateByHibernate(result);
			document = Jsoup.connect(hs_b_url).header("User-Agent",userAgent).timeout(5000).get();
			String jsonstr = document.body().text();
			JSONArray jsonArray = JSONArray.fromObject(jsonstr);
			Collection<StockCurrentData> temp = JSONArray.toCollection(jsonArray, StockCurrentData.class);
			result.addAll(temp);
			document = Jsoup.connect(shfxjs_url).header("User-Agent",userAgent).timeout(5000).get();
			jsonstr = document.body().text();
			jsonArray = JSONArray.fromObject(jsonstr);
			temp = JSONArray.toCollection(jsonArray, StockCurrentData.class);
			result.addAll(temp);
			utils.updateByJDBC(result);
	
			
			CurrentStockDataUpdateSpider.result = result.stream().collect(Collectors.toMap(StockCurrentData::getCode,(p)->p ));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static StockCurrentData getResult(String code) {
		return result.get(code);
	}
	public static Map<String, StockCurrentData> getAll() {
		return result;
	}
	@Override
	public void run() {

		updateCurrentData();
	}

}
