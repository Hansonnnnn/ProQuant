package bl.helper;

import PO.InfoData;
import PO.StockCurrentData;
import PO.StockData;
import PO.StockScore;
import PO.kLinePO.KLineDayData;
import VO.StockDataVO;
import VO.StockKLine;
import VO.StockVO;

public class StockPoToVo{
	
     public static StockDataVO stockDataToStockDataVO(StockData stockData){
         StockDataVO stockDataVO=new StockDataVO(null, stockData.getId().getCode(), stockData.getId().getDate()
        		 , stockData.getHigh(), stockData.getLow()
        		 ,stockData.getOpen(), stockData.getClose(), stockData.getTurnoverRatio(), 0.0,0.0);		
    	 return stockDataVO;
     }
     
     public static StockKLine KLineDataToStockKLine(KLineDayData kLineDayData){
    	 StockKLine kLine=new StockKLine(null, 0, kLineDayData.getDate(), kLineDayData.getKline().getHigh(), kLineDayData.getKline().getLow()
    			 , kLineDayData.getKline().getOpen(),kLineDayData.getKline().getClose());
    	 return kLine;
     }
     
     public static StockVO stockCurrentToStockVO(StockCurrentData stockCurrentData
    		 ,InfoData infoData,StockScore stockScore){
    	 double amilipse=(stockCurrentData.getHigh()-stockCurrentData.getLow())/stockCurrentData.getSettlement();
    	 double amount_Of_Increase=stockCurrentData.getTrade()-stockCurrentData.getOpen();
    	 StockVO sVo=new StockVO(stockCurrentData.getName(), Integer.parseInt(stockCurrentData.getCode()),
    			 stockCurrentData.getTrade(), stockCurrentData.getHigh(), stockCurrentData.getLow(),
    			 stockCurrentData.getOpen(), stockCurrentData.getSettlement(),amount_Of_Increase,
    			 stockCurrentData.getChangepercent()
    			 , stockCurrentData.getVolume().intValue(), stockCurrentData.getAmount(),
    			 stockCurrentData.getMktcap(), stockCurrentData.getNmc(), amilipse
    			 , stockCurrentData.getTurnoverratio(),
    			 stockCurrentData.getPb(), stockCurrentData.getPer(), 
    			 infoData.getCpInfo(), infoData.getCpBusiness(), stockScore.getAnalyzeNum(), stockScore.getBeatNum());

           return sVo;
     }
}
