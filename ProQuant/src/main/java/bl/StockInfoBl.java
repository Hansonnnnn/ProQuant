package bl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PO.InfoData;
import PO.StockCurrentData;
import PO.StockData;
import PO.StockScore;
import PO.kLinePO.KLineDayData;
import PO.kLinePO.KLineTimeData;
import VO.StockDataVO;
import VO.StockForCompare;
import VO.StockKLine;
import VO.StockVO;
import bl.helper.StockInfoHelper;
import bl.helper.StockPoToVo;
import blservice.StockInfoBlService.StockInfoService;
import dataservice.KLineDataService;
import dataservice.StockDataService;
import dataservice.StockScoreService;
import model.KLineType;

@Service("StockInfoService")
public class StockInfoBl implements StockInfoService {
	@Autowired
	StockDataService stockDataService;
	@Autowired
	StockScoreService stockScoreService;
    @Autowired
	KLineDataService kLineDataService;	
    
	@Override
	public StockVO getStockVO(String stockNameOrId) {
		String code=StockInfoHelper.getStockCode(stockNameOrId,stockDataService);
		StockCurrentData stockCurrent=stockDataService.getStockCurrentData(code);
		InfoData infoData=stockDataService.getStockInfo(code);
		StockScore stockScore=stockScoreService.getStockScore(code);
		return StockPoToVo.stockCurrentToStockVO(stockCurrent, infoData, stockScore);
	}

	@Override
	public ArrayList<StockKLine> getStockForKLine(String stockNameOrId, Date startDate, Date endDate
			,KLineType kLineType,boolean fq) {
        //瀵版鍩宨d閸滃ame
		String code=StockInfoHelper.getStockCode(stockNameOrId,stockDataService);
		String name=stockDataService.getName(code);
		int id=Integer.parseInt(code);
		
		//瀵版鍩屾０鍕吀閻ㄥ嫬銇夐弫锟�
        int count=1;
		Date tmpDate=new Date();
		tmpDate=startDate;
		while(tmpDate.before(endDate)){
			count++;
			tmpDate=StockInfoHelper.add(tmpDate, 1);
		}
		//娴犲孩鏆熼幑顔肩湴閸欐牗鏆熼幑锟�
		ArrayList<KLineDayData> kLineDayDatas=new ArrayList<>();
		switch (kLineType){
		case Day:kLineDayDatas=kLineDataService.getdayLine(code, endDate, count, fq);
			break;
		case Week:kLineDayDatas=kLineDataService.getweekLine(code, endDate, count, fq);
			break;
		case Mouth:kLineDayDatas=kLineDataService. getmonthLine(code, endDate, count, fq);
			break;
		}
		//閸掔姴骞撴径姘稇閻ㄥ埦o
		while(kLineDayDatas.get(0).getDate().before(startDate)){
			kLineDayDatas.remove(0);
		}
		
		//po鏉烆剙瀵查幋鎭
		ArrayList<StockKLine> kLines=new ArrayList<>();
		for(int i=0;i<kLineDayDatas.size();i++){
			StockKLine stockKLine=StockPoToVo.KLineDataToStockKLine(kLineDayDatas.get(i));
			stockKLine.setName(name);
			stockKLine.setId(id);
			kLines.add(stockKLine);
		}
		return kLines;
	}

	@Override
	public ArrayList<StockDataVO> getStockData(String stockNameOrId, int numberOfDay) {
		String code=StockInfoHelper.getStockCode(stockNameOrId,stockDataService);
		//娴犲孩鏆熼幑顔肩湴瀵版鍩岄懖锛勩偍閻ㄥ埓ap
		Date now =new Date();
		Map<Date, StockData> stockPO=stockDataService.getBasicDateStock(now, numberOfDay, code);
		String name=stockDataService.getName(code);
//		System.out.println(name);
		ArrayList<StockDataVO> stockDataVOs=new ArrayList<>();
		for (Map.Entry<Date, StockData> entry : stockPO.entrySet()) {
            StockDataVO stockDataVO=StockPoToVo.stockDataToStockDataVO(entry.getValue());
            stockDataVO.setName(name);
            stockDataVO.setId(code);
            stockDataVOs.add(stockDataVO);
		}
		return stockDataVOs;
	}

	@Override
	public ArrayList<StockForCompare> stockCompare(ArrayList<String> stockLists) {
        ArrayList<StockForCompare> result=new ArrayList<>();
		for(int i=0;i<stockLists.size();i++){
			StockForCompare stockForCompare=new StockForCompare();
			String code=stockDataService.getCode(stockLists.get(i));
			System.out.println(code);
			//得到分时图
			ArrayList<KLineTimeData> timeList=kLineDataService.getTimeLine(code);
			Map<String , Double > timeMap=new LinkedHashMap<>();
			for(int time=0;time<timeList.size();time++){
				String Time=getTime(timeList.get(time).getTime());
				double percent = formatDouble(((timeList.get(time).getPrice()/timeList.get(time).getPreClose())-1) * 100);
				timeMap.put(Time, percent);
			}
			stockForCompare.setTimeValue(timeMap);
			
			//得到涨跌图
			Map<Date, Double> increaseMap=new LinkedHashMap<>();
			Map<Date, Double> closeMap=new LinkedHashMap<>();
			Map<Date, StockData> stockInfo=stockDataService.getBasicDateStock(null, 65, code);
			for(Map.Entry<Date, StockData> entry: stockInfo.entrySet()){
				increaseMap.put(entry.getKey(), entry.getValue().getChg());
				closeMap.put(entry.getKey(), entry.getValue().getClose());
			}
			stockForCompare.setDayClose(closeMap);
			stockForCompare.setDayIncrease(increaseMap);
			result.add(stockForCompare);
		}
		return result;
	}
	private String getTime(String time){
		String format=null;
		if(time.length()==8){
			format=time.substring(0, 1)+"-"+time.substring(1,3);
		}
		if(time.length()==9){
			format=time.substring(0, 2)+"-"+time.substring(1,3);
		}
		return format;
	}

	private double formatDouble(double num){
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.parseDouble(df.format(num));
	}

}
