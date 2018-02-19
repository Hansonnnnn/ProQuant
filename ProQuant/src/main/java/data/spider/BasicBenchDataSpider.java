package data.spider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.BenchDataDao;
import PO.BenchData;
import PO.BenchDataId;
@Service("BBDS")
public class BasicBenchDataSpider implements BasicDataSpiderService{
	
	@Autowired
	BenchDataDao benchDataDao;
	
	public String[] URL=
		{"http://quotes.money.163.com/trade/lsjysj_zhishu_",
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
	
	public void sharesCrawl(String code,int year,int season) {
		String url = URL[0]+code+URL[1]+year+URL[2]+season;
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";
		try {
			Document document =  Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
			Elements elements = document.select("table[class=table_bg001 border_box limit_sale]");
			Elements trs = elements.first().select("tr");
			trs.remove(0);
			BenchData benchData;
			BenchDataId benchDataId;
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			for (Element tr : trs) {
				Elements tds = tr.select("td");
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
						if (!tds.get(5).text().equals("--")) {
							benchData.setNetChange(Double.parseDouble(tds.get(5).text().replaceAll(",","")));
						}
						if (!tds.get(6).text().equals("--")) {
							benchData.setChg(Double.parseDouble(tds.get(6).text().replaceAll(",","")));
						}
						benchData.setVolume(Double.parseDouble(tds.get(7).text().replaceAll(",","")));
						if (!tds.get(8).text().equals("--")) {
							benchData.setTurnover(Double.parseDouble(tds.get(8).text().replaceAll(",","")));
							
						}
						benchData.setId(benchDataId);
						benchDataDao.persist(benchData);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sharesCrawl(code,year,season);
		}
	}
	public static void main(String[] args) {
		new BasicBenchDataSpider().test("399005", 2016, 2016);
	}
	
	
}
