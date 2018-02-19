package data.spider.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import PO.StockBasicIndex;
@Service("BSIUS")
public class BasicStockIndexUpdateSpider extends TimerTask implements BasicDataUpdateSpiderService{
	@Autowired
	SessionFactory sessionFactory;
	
	private String[] URL = { "http://quotes.money.163.com/tools/stockcomp/", ".html" };
	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";

	@Override
	public void sharesCrawl(String code) {
		Document document = null;
		try {
			document = Jsoup.connect(getUrl(code)).header("User-Agent", userAgent).timeout(3000).get();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			sharesCrawl(code);
		}
		if (document==null) {
			return;
		}
		
		Elements elements = document.select("table[class=sc_data_table]");
		if (elements==null||elements.size()==0) {
			return;
		}
		Element tbody = elements.first().select("tbody").first();
		Elements trs = tbody.select("tr");
		if (trs==null||trs.size()==0) {
			return;
		}
		StockBasicIndex stockBasicIndex = new StockBasicIndex(code);
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < trs.size(); i++) {
			Element td = trs.get(i).select("td").first();
			if (!td.text().equals("--")) {
				map.put(i, td.text());
			}
		}
		
		stockBasicIndex.setWeekChangeRatio(parseDouble(map.get(0)));;
		stockBasicIndex.setMonthChangeRatio(parseDouble(map.get(1)));
		stockBasicIndex.setSeasonChangeRatio(parseDouble(map.get(2)));
		stockBasicIndex.setHalfayearChangeRatio(parseDouble(map.get(3)));
		stockBasicIndex.setYearChangeRatio(parseDouble(map.get(4)));
		stockBasicIndex.setPe(parseDouble(map.get(5)));
		stockBasicIndex.setPb(parseDouble(map.get(6)));
		stockBasicIndex.setPcf(parseDouble(map.get(7)));
		stockBasicIndex.setPs(parseDouble(map.get(8)));
		stockBasicIndex.setPerShareEarnings(parseDouble(map.get(9)));
		stockBasicIndex.setNetProfitMarginOnSales(parseDouble(map.get(10)));
		stockBasicIndex.setRoa(parseDouble(map.get(11)));
		stockBasicIndex.setDebtAssetRatio(parseDouble(map.get(12)));
		stockBasicIndex.setCurrentRatio(parseDouble(map.get(13)));
		stockBasicIndex.setMainBusinessIncome(parseDouble(map.get(14)));
		stockBasicIndex.setNetProfit(parseDouble(map.get(15)));
		stockBasicIndex.setTotalAssets(parseDouble(map.get(16)));
		stockBasicIndex.setShareholdersEquity(parseDouble(map.get(17)));
		Session session = sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.merge(stockBasicIndex);
		transaction.commit();
		session.close();
	}

	
	/*public StockBasicIndex spide(String code) {
		Document document = null;
		try {
			document = Jsoup.connect(getUrl(code)).header("User-Agent", userAgent).timeout(3000).get();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			spide(code);
		}
		if (document==null) {
			return null;
		}
		
		Elements elements = document.select("table[class=sc_data_table]");
		if (elements==null||elements.size()==0) {
			return null;
		}
		Element tbody = elements.first().select("tbody").first();
		Elements trs = tbody.select("tr");
		if (trs==null||trs.size()==0) {
			return null;
		}
		StockBasicIndex stockBasicIndex = new StockBasicIndex(code);
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < trs.size(); i++) {
			Element td = trs.get(i).select("td").first();
			if (!td.text().equals("--")) {
				map.put(i, td.text());
			}
		}
		
		stockBasicIndex.setWeekChangeRatio(parseDouble(map.get(0)));;
		stockBasicIndex.setMonthChangeRatio(parseDouble(map.get(1)));
		stockBasicIndex.setSeasonChangeRatio(parseDouble(map.get(2)));
		stockBasicIndex.setHalfayearChangeRatio(parseDouble(map.get(3)));
		stockBasicIndex.setYearChangeRatio(parseDouble(map.get(4)));
		stockBasicIndex.setPe(parseDouble(map.get(5)));
		stockBasicIndex.setPb(parseDouble(map.get(6)));
		stockBasicIndex.setPcf(parseDouble(map.get(7)));
		stockBasicIndex.setPs(parseDouble(map.get(8)));
		stockBasicIndex.setPerShareEarnings(parseDouble(map.get(9)));
		stockBasicIndex.setNetProfitMarginOnSales(parseDouble(map.get(10)));
		stockBasicIndex.setRoa(parseDouble(map.get(11)));
		stockBasicIndex.setDebtAssetRatio(parseDouble(map.get(12)));
		stockBasicIndex.setCurrentRatio(parseDouble(map.get(13)));
		stockBasicIndex.setMainBusinessIncome(parseDouble(map.get(14)));
		stockBasicIndex.setNetProfit(parseDouble(map.get(15)));
		stockBasicIndex.setTotalAssets(parseDouble(map.get(16)));
		stockBasicIndex.setShareholdersEquity(parseDouble(map.get(17)));
		
		return stockBasicIndex;
	}
*/

	/*public void runTemp() {
		System.out.println(new Date()+"updateStockBasicIndex=========start");
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<String> list = session.createQuery("select code from InfoData").list();
		session.close();
		sharesCrawl(list);
	}*/

	/*public void sharesCrawl(List<String> list) {
		ArrayList<StockBasicIndex> result = new ArrayList<>();
		for (String code : list) {
			result.add(spide(code));
		}
		updateUtils.updateStockBasicIndex(result);
		System.out.println(new Date()+"updateStockBasicIndex=======finished");
	}
	*/
	private String getUrl(String code){
		String url = URL[0]+((code.startsWith("6")||code.startsWith("9"))?"0"+code:"1"+code)+URL[1];
		return url;
	}
	
	private Double parseDouble(String string){
		if (string ==null) {
			return null;
		}
		char c = string.charAt(string.length()-1);
		if (c<'0'||c>'9') {
			try {
				return Double.parseDouble(string.substring(0, string.length() - 1).replaceAll(",", ""));
			} catch (NumberFormatException e) {
				return Double.parseDouble(string.substring(0, string.length() - 2).replaceAll(",", ""))*10000;
			}
		}else {
			return Double.parseDouble(string.replaceAll(",",""));
		}
	}
	
	@Override
	public void run(){
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<List> list = session.createQuery("select new list(I.id,I.code) from InfoData I order by I.id").list();
		for (List temp : list) {
			System.out.println("now id = " + temp.get(0));
			sharesCrawl((String) temp.get(1));
		}
		session.close();
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		((BasicStockIndexUpdateSpider)context.getBean("BSIUS")).run();;
	}
}
