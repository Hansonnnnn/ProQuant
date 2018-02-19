package data.RecommendedData;

import java.util.ArrayList;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import PO.recommendedStock.ContinuingQuantityPO;
import dataservice.recommendedData.ContinuingQuantityService;

@Service("CQD")
public class ContinuingQuantityDown extends TimerTask implements ContinuingQuantityService{
	public final String[] url={"http://data.10jqka.com.cn/rank/cxsl/field/count/order/desc/page/","/ajax/1/"};
	public final int page=10;
	public static ArrayList<ContinuingQuantityPO> arrayList =new ArrayList<>();
	
	
	public ArrayList<ContinuingQuantityPO> updateDatas(){
		ArrayList<ContinuingQuantityPO> result=new ArrayList<>();
			for (int i = 0; i <page; i++) {
				try {
					Document document = Jsoup.connect(url[0] + i + url[1]).get();
					Elements elements = document.select("tbody").select("tr");
					for (Element element : elements) {
						String[] temp = element.text().split(" ");
						ContinuingQuantityPO po = new ContinuingQuantityPO();
						if (temp[1].charAt(0) == '6') {
							temp[1] = "sh" + temp[1];
						} else {
							temp[1] = "sz" + temp[1];
						}
						if(temp.length==9){
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setRiseOrDown(temp[3]);
							po.setUptodate(temp[4]);
							po.setVolumn(temp[5]);
							po.setBaseDateVolumn(temp[6]);
							po.setDays(temp[7]);
							po.setStageRiseOrDown(temp[8]);
							po.setIndustry("");
						}else {
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setRiseOrDown(temp[3]);
							po.setUptodate(temp[4]);
							po.setVolumn(temp[5]);
							po.setBaseDateVolumn(temp[6]);
							po.setDays(temp[7]);
							po.setStageRiseOrDown(temp[8]);
							po.setIndustry(temp[9]);
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
	public ArrayList<ContinuingQuantityPO> getDatas() {
		
		return arrayList;
	}
}
