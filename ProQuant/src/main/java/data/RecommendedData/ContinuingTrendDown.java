package data.RecommendedData;

import java.util.ArrayList;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import PO.recommendedStock.ContinuingTrendPO;
import dataservice.recommendedData.ContinuingTrendService;

@Service("CTD")
public class ContinuingTrendDown extends TimerTask implements ContinuingTrendService{
	public final String[] url={"http://data.10jqka.com.cn/rank/lxxd/field/lxts/order/desc/page/","/ajax/1/"};
	public final int page=20;
	public static ArrayList<ContinuingTrendPO> arrayList = new ArrayList<>();
	
	public ArrayList<ContinuingTrendPO> updateDatas(){
		ArrayList<ContinuingTrendPO> result=new ArrayList<>();
			for (int i = 0; i < page; i++) {
				try {
					Document document = Jsoup.connect(url[0] + i+ url[1]).get();
					Elements elements = document.select("tbody").select("tr");
					for (Element element : elements) {
						String[] temp = element.text().split(" ");
						ContinuingTrendPO po = new ContinuingTrendPO();
						if (temp[1].charAt(0) == '6') {
							temp[1] = "sh" + temp[1];
						} else {
							temp[1] = "sz" + temp[1];
						}
						if (temp.length==10) {
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setUptodate(temp[3]);
							po.setHigh(temp[4]);
							po.setLow(temp[5]);
							po.setContinuingDays(temp[6]);
							po.setRiseOrdown(temp[7]);
							po.setExchange(temp[8]);
							po.setIndustry(temp[9]);
						}else {
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setUptodate(temp[3]);
							po.setHigh(temp[4]);
							po.setLow(temp[5]);
							po.setContinuingDays(temp[6]);
							po.setRiseOrdown(temp[7]);
							po.setExchange(temp[8]);
							po.setIndustry("");
						}
	
						result.add(po);
					} 
				} catch (Exception e) {
				}
			}
		return result;
	}
	

	@Override
	public void run() {
		arrayList = updateDatas();
	}

	@Override
	public ArrayList<ContinuingTrendPO> getDatas() {
		
		return arrayList;
	}
	
	

}
