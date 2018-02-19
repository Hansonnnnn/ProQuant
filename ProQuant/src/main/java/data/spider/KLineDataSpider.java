package data.spider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import PO.kLinePO.KLineDayData;
import PO.kLinePO.KLineTimeData;
import dataservice.KLineDataService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("KLineDataService")
public class KLineDataSpider implements KLineDataService {
	
	private static final String[] basic ={ "https://gupiao.baidu.com/api/stocks/",
		"?from=pc&os_ver=1&cuid=xxx&vv=100&format=json&stock_code=",
		"&step=3&start=",
		"&count=",
		"&fq_type="};
	
	private List<String> type = Arrays.asList("stocktimeline","stockdaybar","stockweekbar","stockmonthbar");
		
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
	
	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";

	/**
		 (non-Javadoc)
	 * @see dataservice.KLineDataService#getTimeLine(java.lang.String, java.util.Date, int, boolean)
	 */
	@Override
	public ArrayList<KLineTimeData> getTimeLine(String code) {
		String url = getUrl(0, code, null,0, false );
		ArrayList<KLineTimeData> result = new ArrayList<>();
		try {
			Document document =  Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
			String jsonstr = document.body().text();
			JSONObject json = JSONObject.fromObject(jsonstr);	
			JSONArray jsonArray =  (JSONArray) json.get("timeLine");
			Gson gson = new GsonBuilder().setDateFormat("yyyyMMdd").create();
			
			for (Object object : jsonArray) {
				result.add(gson.fromJson( object.toString(), KLineTimeData.class));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getTimeLine(code);
		}
		return result;
	}
	
	/**
		 (non-Javadoc)
	 * @see dataservice.KLineDataService#getdayLine(java.lang.String, java.util.Date, int, boolean)
	 */
	@Override
	public ArrayList<KLineDayData> getdayLine(String code,Date start,int count,boolean fq) {
		String url = getUrl(1, code, start, count, fq);
		//System.out.println(url);
		return getDayLineData(url);
	}	
	/**
		 (non-Javadoc)
	 * @see dataservice.KLineDataService#getweekLine(java.lang.String, java.util.Date, int, boolean)
	 */
	@Override
	public ArrayList<KLineDayData> getweekLine(String code,Date start,int count,boolean fq) {
		String url = getUrl(2, code, start, count, fq);
		return getDayLineData(url);
	}
	
	/**
		 (non-Javadoc)
	 * @see dataservice.KLineDataService#getmonthLine(java.lang.String, java.util.Date, int, boolean)
	 */
	@Override
	public ArrayList<KLineDayData> getmonthLine(String code,Date start,int count,boolean fq) {
		String url = getUrl(3, code, start, count, fq);
		return getDayLineData(url);
	}
	
	
	private String getUrl(int type,String code,Date start,int count,boolean fq){
		String temp = (code.startsWith("6")||code.startsWith("9"))?"sh"+code:"sz"+code;
		if (code.equals("000300")) {
			temp = "sh"+code;
		}
		StringBuilder url = new StringBuilder();
		url.append(basic[0]).append(this.type.get(type)).append(basic[1]).append(temp).append(basic[2]);
		if (start!=null) {
			url.append(sf.format(start));
		}
		url.append(basic[3]);
		if (count != 0) {
			url.append(count);
		}
		url.append(basic[4]);
		
		url.append(fq?"yes":"no");
	
		return url.toString();
	}
	
	private ArrayList<KLineDayData> getDayLineData(String url) {
		ArrayList<KLineDayData> result = new ArrayList<>();
		try {
			Document document =  Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
			String jsonstr = document.body().text();
			JSONObject json = JSONObject.fromObject(jsonstr);	
			JSONArray jsonArray =  (JSONArray) json.get("mashData");
			Gson gson = new GsonBuilder().setDateFormat("yyyyMMdd").create();
			if (jsonArray==null) {
				return result;
			}
			for (Object object : jsonArray) {
				result.add(gson.fromJson( object.toString(), KLineDayData.class));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getDayLineData(url);
		}
		return result;
	}
	public static void main(String[] args) {
		ArrayList<KLineDayData> result = new KLineDataSpider().getweekLine("000001", null, 160, false);
		System.out.println(result);
	}
}
