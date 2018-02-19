package bl.helper.strategy;

import java.util.*;

import PO.BenchData;
import PO.StockData;
import VO.StockPlateVO;
import VO.strategyPageVO.*;
import bl.helper.StockInfoHelper;
import dataservice.BenchDataService;
import model.StockPlate;
import utility.DateHelper;

public class StrategyHelper {
	public static Map<Date, Double> holdField(){
		
		return null;
	}
	
	public static DatesAndBase stadardEarning(Date startDay,Date endDate,int holdDays,StockPlate stockPlate,BenchDataService benchService){
		Map<Date, Double > baseEarning=new LinkedHashMap<>();
		Map<Date, BenchData> benchPO=benchService.getDataByDateAndPlate(stockPlate, startDay, endDate);
		int size=benchPO.size();
		Date dates[]=new Date[size];
		Set<Date> dateSet=benchPO.keySet();
		dateSet.toArray(dates);
		BenchData benchs[]=new BenchData[size];

		int count=0;

		for (Map.Entry<Date, BenchData> entry : benchPO.entrySet()) {
		    benchs[count]=entry.getValue();
		    count++;
		}
		count=0;
		while(count<size-1){
			int temp=count;
			count+=holdDays;
			if(count>=size){
				count=size-1;
			}
			Double earning=(benchs[count].getClose()-benchs[temp].getClose())/benchs[temp].getClose();
			baseEarning.put(dates[count], earning);
		}
		DatesAndBase db=new DatesAndBase(dates, baseEarning);
		return db;
	}
	
	public static ArrayList<String> getHoldStocks(ArrayList<StrategyCompareVO> lists,int maxHoldNums){
		sort(lists, 0, lists.size()-1);
		ArrayList<String > result=new ArrayList<>();
		int size=lists.size()-1;
		for(int i=0;i<maxHoldNums;i++){
			result.add(lists.get(size-i).code);
		}
		return result;
	}
	     
	     private  static void sort(ArrayList<StrategyCompareVO> a,int low,int high){
	         int start = low;
	         int end = high;
	         double  key = a.get(low).value;
	         while(end>start){
	             //浠庡悗寰�鍓嶆瘮杈�
	             while(end>start&&a.get(end).value>=key)  //濡傛灉娌℃湁姣斿叧閿�煎皬鐨勶紝姣旇緝涓嬩竴涓紝鐩村埌鏈夋瘮鍏抽敭鍊煎皬鐨勪氦鎹綅缃紝鐒跺悗鍙堜粠鍓嶅線鍚庢瘮杈�
	                 end--;
	             if(a.get(end).value<=key){
	                 StrategyCompareVO temp = a.get(end);
	                 StrategyCompareVO temp2=a.get(start);
	                 a.set(end, temp2);
	                 a.set(start, temp);
	             }
	             //浠庡墠寰�鍚庢瘮杈�
	             while(end>start&&a.get(start).value<=key)//濡傛灉娌℃湁姣斿叧閿�煎ぇ鐨勶紝姣旇緝涓嬩竴涓紝鐩村埌鏈夋瘮鍏抽敭鍊煎ぇ鐨勪氦鎹綅缃�
	                start++;
	             if(a.get(start).value>=key){
	                 StrategyCompareVO temp = a.get(start);
	                 a.set(start, a.get(end));
//	                 a[start] = a[end];
	                 a.set(end, temp);
//	                 a[end] = temp;
	             }
	         //姝ゆ椂绗竴娆″惊鐜瘮杈冪粨鏉燂紝鍏抽敭鍊肩殑浣嶇疆宸茬粡纭畾浜嗐�傚乏杈圭殑鍊奸兘姣斿叧閿�煎皬锛屽彸杈圭殑鍊奸兘姣斿叧閿�煎ぇ锛屼絾鏄袱杈圭殑椤哄簭杩樻湁鍙兘鏄笉涓�鏍风殑锛岃繘琛屼笅闈㈢殑閫掑綊璋冪敤
	         }
	         //閫掑綊
	         if(start>low) sort(a,low,start-1);//宸﹁竟搴忓垪銆傜涓�涓储寮曚綅缃埌鍏抽敭鍊肩储寮�-1
	         if(end<high) sort(a,end+1,high);//鍙宠竟搴忓垪銆備粠鍏抽敭鍊肩储寮�+1鍒版渶鍚庝竴涓�
	     }
	     
	public static EarningsCircleVO getEarningsCircleVO(EarningsLineVO earningsLineVO){
		EarningsCircleVO earningCircleVO=new EarningsCircleVO();
		Map<Integer, Integer>peData=new LinkedHashMap<>();
		Map<Integer, Integer>neData=new LinkedHashMap<>();
		Map<Date, Double> strategyData=earningsLineVO.getStrategyEarningsData();
		Map<Date, Double> baseData=earningsLineVO.getBaseEarningsData();
		int peCircleNum=0;
		int neCircleNum=0;
		int maxProfit=0;
		for(Map.Entry<Date, Double> entry:strategyData.entrySet()){
			double profitRate=StockInfoHelper.format(entry.getValue()-baseData.get(entry.getKey()));
			if(profitRate>0){
				profitRate+=0.01;
			}else{
				profitRate-=0.01;
			}

			int key=(int)(profitRate*100);

			if(Math.abs(key)>maxProfit){
				maxProfit=Math.abs(key);
			}

			//娣诲姞鍒扮粨鏋滈噷
			if(profitRate>=0){
				peCircleNum++;
				if(peData.containsKey(key)){
					peData.put(key, peData.get(key)+1);
				}else{
					peData.put(key, 1);
				}
			}else{
				neCircleNum++;
				if(neData.containsKey(-key)){
					neData.put(-key, neData.get(-key)+1);
				}else{
					neData.put(-key, 1);
				}
			}
		}
		for(int i=1;i<=maxProfit;i++){
			if(!peData.containsKey(i)){
				peData.put(i, 0);
			}
			if(!neData.containsKey(i)){
				neData.put(i, 0);
			}
		}
		earningCircleVO.setNeCircleNum(neCircleNum);
		earningCircleVO.setPeCircleNum(peCircleNum);
		earningCircleVO.setNeData(sortMap(neData));
		earningCircleVO.setPeData(sortMap(peData));
		double x=(double)peCircleNum/(double)(peCircleNum+neCircleNum);
		x=100*(StockInfoHelper.format(x));
		earningCircleVO.setWinRate(x);
		return earningCircleVO;
	}

	public static BaseAndStrategyParam getBaseAndStrategyParam(EarningsLineVO earningsLineVO, int holdDays, Date startDate){
		ParamDataVO paramStrategyDataVO = new ParamDataVO();
		ParamDataVO paramBaseDataVO = new ParamDataVO();
		//计算年化收益率
		double baseYear=0.0;
		baseYear=(Statistics.calculateAVG(earningsLineVO.getBaseEarningsData())/holdDays)*365;
		paramBaseDataVO.setAnnualizedRateOfReturn(StockInfoHelper.format(baseYear)*100);

		double profitYear=0.0;
		profitYear=(Statistics.calculateAVG(earningsLineVO.getStrategyEarningsData())/holdDays)*365;
		paramStrategyDataVO.setAnnualizedRateOfReturn(StockInfoHelper.format(profitYear)*100);

		//计算贝塔
		double beta=0;
		beta=Statistics.calaulateCOV(earningsLineVO.getStrategyEarningsData(), earningsLineVO.getBaseEarningsData());
		beta/=Statistics.calaulateCOV(earningsLineVO.getBaseEarningsData(), earningsLineVO.getBaseEarningsData());
		paramStrategyDataVO.setBeta(StockInfoHelper.format_2(beta));
		paramBaseDataVO.setBeta(-1);

		//计算夏普比率
		double sharpRate=0;
		sharpRate=Statistics.calculateAVG(earningsLineVO.getStrategyEarningsData())-DepositRate.getRate(startDate);
		sharpRate/=Math.sqrt(Statistics.calaulateCOV(earningsLineVO.getStrategyEarningsData(), earningsLineVO.getStrategyEarningsData()));
		if(sharpRate<0){
		    sharpRate=-sharpRate;
        }
		paramStrategyDataVO.setSharpeRatio(StockInfoHelper.format_2(sharpRate));
		paramBaseDataVO.setSharpeRatio(-1);

		//计算阿尔法
		double alpha=0;
		alpha=(profitYear-DepositRate.getRate(startDate));
		double temp=( Statistics.calculateAVG(earningsLineVO.getBaseEarningsData())- DepositRate.getRate(startDate));
		alpha=alpha-(beta*temp);
		if(alpha<0){
			alpha=-alpha;
		}
		paramBaseDataVO.setAlpha(-1);
		paramStrategyDataVO.setAlpha(StockInfoHelper.format(alpha)*100);

		//最大回撤率
		double  maxBack=0;
		double  peak=0;
		ArrayList<Double> list=new ArrayList<>();

		for (Map.Entry<Date, Double > entry : earningsLineVO.getStrategyEarningsData().entrySet()) {
			list.add(entry.getValue());
		}
		for(int i=0;i<list.size();i++){
			if(list.get(i)>peak){
				peak=list.get(i);
			}
			double back=peak-list.get(i);
			if(back>maxBack){
				maxBack=back;
			}
		}
		paramStrategyDataVO.setBiggestReturn(StockInfoHelper.format(maxBack)*100);
		paramBaseDataVO.setBiggestReturn(-1);

		paramStrategyDataVO.setRateOfTotalReturn(100*StockInfoHelper.format(Statistics.calculateAll(earningsLineVO.getStrategyEarningsData())));
		paramBaseDataVO.setRateOfTotalReturn(100*StockInfoHelper.format(Statistics.calculateAll(earningsLineVO.getBaseEarningsData())));

		BaseAndStrategyParam b=new BaseAndStrategyParam(paramStrategyDataVO,paramBaseDataVO);
	    return b;
	}

	private static Map<Integer,Integer> sortMap(Map<Integer,Integer> map){
		List<Map.Entry<Integer, Integer>> infos = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());

		//对list排序,实现新的比较器

		Collections.sort(infos, new Comparator<Map.Entry<Integer, Integer>>(){

			@Override

			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {

				return o1.getKey() - o2.getKey();

			}

		});

		//申明新的有序 map,根据放入的数序排序

		Map<Integer, Integer> lhm = new LinkedHashMap<Integer, Integer>();

		//遍历比较过后的map,将结果放到LinkedHashMap

		for(Map.Entry<Integer, Integer> entry:infos){

			lhm.put(entry.getKey(), entry.getValue());

		}
		return lhm;
	}

	public static StrategyEvalutingVO getSEVO(EarningsCircleVO earningCircleVO,EarningsLineVO earningsLineVO
        ,ParamDataVO paramStrategyDataVO,ParamDataVO paramBaseDataVO){
	    //计算收益指数
	    double x1=0;
	    if(paramStrategyDataVO.getAnnualizedRateOfReturn()>10){
	        x1=60;
	        if(paramStrategyDataVO.getAnnualizedRateOfReturn()-10<40) {
                x1 += (paramStrategyDataVO.getAnnualizedRateOfReturn() - 10);
            }else{
	            x1+=32;
            }
        }
        StrategyEvalutingVO s=new StrategyEvalutingVO();
	    s.setEarningsValue((int)x1);

	    //计算抗风险
        double x2=0;
        x2=earningCircleVO.getWinRate()+100-(2*paramStrategyDataVO.getBiggestReturn());
        s.setAnti_riskValue((int) x2);

        //实盘分数
        double x3=0;
        x3=paramStrategyDataVO.getRateOfTotalReturn()-paramBaseDataVO.getRateOfTotalReturn();
        x3=earningCircleVO.getWinRate()+0.1*x3;
        s.setRealPlateValue((int) x3);

        //稳定性分析
        double x4=0;
        double cov= Statistics.calCOVMap(earningsLineVO.getBaseEarningsData(),earningsLineVO.getStrategyEarningsData());
        if(cov<2){
            x4=40;
        }else if(cov<4){
            x4=30;
        }else{
            x4=20;
        }
        x4+=paramStrategyDataVO.getBiggestReturn();
        s.setRobustnessValue((int)x4);

        //流动性分析
        double x5=0.0;
        if(earningsLineVO.getBaseEarningsData().size()<30){
            x5=earningsLineVO.getBaseEarningsData().size()+20;
        }else{
            x5=50;
        }
        x5+=10*paramStrategyDataVO.getSharpeRatio();
        s.setMobilityValue((int)x5);
        return s;
	}
}
