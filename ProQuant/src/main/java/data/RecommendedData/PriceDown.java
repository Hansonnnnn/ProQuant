package data.RecommendedData;

import java.util.ArrayList;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import PO.recommendedStock.PricePO;
import dataservice.recommendedData.PriceService;

@Service("PrD")
public class PriceDown extends TimerTask implements PriceService{
	public final String[] url={"http://data.10jqka.com.cn/rank/ljqd/field/count/order/desc/page/","/ajax/1/"};
	public final int page=30;
	public static ArrayList<PricePO> arrayList = new ArrayList<>();
	
	public ArrayList<PricePO> updateDatas(){
		ArrayList<PricePO> result=new ArrayList<>();
			for (int i = 0; i < page; i++) {
				try {
					Document document = Jsoup.connect(url[0] + i+ url[1]).get();
					Elements elements = document.select("tbody").select("tr");
					for (Element element : elements) {
						String[] temp = element.text().split(" ");
						PricePO po = new PricePO();
						if (temp[1].charAt(0) == '6') {
							temp[1] = "sh" + temp[1];
						} else {
							temp[1] = "sz" + temp[1];
						}
						if (temp.length==8) {
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setUptodate(temp[3]);
							po.setDays(temp[4]);
							po.setStageRiseOrDown(temp[5]);
							po.setExchange(temp[6]);
							po.setIndustry(temp[7]);
						}else {
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setUptodate(temp[3]);
							po.setDays(temp[4]);
							po.setStageRiseOrDown(temp[5]);
							po.setExchange(temp[6]);
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
		arrayList=updateDatas();
	}

	@Override
	public ArrayList<PricePO> getDatas() {
		return arrayList;
	}
	
	

}
