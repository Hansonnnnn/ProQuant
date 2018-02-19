package data.RecommendedData;

import java.util.ArrayList;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import PO.recommendedStock.PeakPO;
import dataservice.recommendedData.PeakService;

@Service("PeU")
public class PeakUp extends TimerTask implements PeakService{
	public final String[] url={"http://data.10jqka.com.cn/rank/cxg/board/4/field/stockcode/order/asc/page/","/ajax/1/"};
	public final int page=10;
	public static ArrayList<PeakPO> arrayList = new ArrayList<>();
	
	
	
	public ArrayList<PeakPO> updateDatas(){
		ArrayList<PeakPO> result=new ArrayList<>();
			for (int i = 0; i < page; i++) {
				try {
					Document document = Jsoup.connect(url[0] + i+ url[1]).get();
					Elements elements = document.select("tbody").select("tr");
					for (Element element : elements) {
						String[] temp = element.text().split(" ");
						PeakPO po = new PeakPO();
						if (temp[1].charAt(0) == '6') {
							temp[1] = "sh" + temp[1];
						} else {
							temp[1] = "sz" + temp[1];
						}
						po.setStockId(temp[1]);
						po.setStockName(temp[2]);
						po.setRiseOrDown(temp[3]);
						po.setExchange(temp[4]);
						po.setUptodate(temp[5]);
						po.setHigh(temp[6]);
						po.setDate(temp[7]);
	
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
	public ArrayList<PeakPO> getDatas() {
		
		return arrayList;
	}
	
	

}
