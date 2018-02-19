package data.RecommendedData;

import java.util.ArrayList;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import PO.recommendedStock.breakthroughPO;
import dataservice.recommendedData.BreakthroughService;

@Service("BU")
public class breakthroughUp extends TimerTask implements BreakthroughService {
	public final String[] url={"http://data.10jqka.com.cn/rank/xstp/board/5/order/asc/page/","/ajax/1/"};
	public final int page=40;
	public static ArrayList<breakthroughPO> arrayList = new ArrayList<>();
	
	
	public ArrayList<breakthroughPO> updateDatas(){
		ArrayList<breakthroughPO> result=new ArrayList<>();
			for (int i = 0; i < page; i++) {
				try {
					Document document = Jsoup.connect(url[0] + i+ url[1]).get();
					Elements elements = document.select("tbody").select("tr");
					for (Element element : elements) {
						String[] temp = element.text().split(" ");
						breakthroughPO po = new breakthroughPO();
						if (temp[1].charAt(0) == '6') {
							temp[1] = "sh" + temp[1];
						} else {
							temp[1] = "sz" + temp[1];
						}
						po.setStockId(temp[1]);
						po.setStockName(temp[2]);
						po.setUptodate(temp[3]);
						po.setPrice(temp[4]);
						po.setVolumn(temp[5]);
						po.setRiseOrDown(temp[6]);
						po.setExchange(temp[7]);
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
	public ArrayList<breakthroughPO> getDatas() {
		return arrayList;
	}
	
	

}
