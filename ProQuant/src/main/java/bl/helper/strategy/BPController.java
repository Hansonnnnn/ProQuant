package bl.helper.strategy;

import PO.StockData;
import VO.strategyPageVO.PrecisionVO;
import bl.helper.StockInfoHelper;

import java.util.*;

/**
 * Created by yk on 2017/6/13.
 */
public class BPController {
    private Map<Date,StockData> stockDataMap;
    private StockData todayData;
    private  double[][] base=new double[5][2];
    private  double[][] basePre=new double[5][2];
    private  BPSolution bpSolution_close;
    private  BPSolution bpSolution_chg;
    private  Map<Date,Double> baseMap;
    private  Map<Date,StockData> preStockMap;
    public BPController(Map<Date,StockData> stockMap,Map<Date,StockData> preMap,StockData todayData){
        this.stockDataMap=stockMap;
        this.todayData=todayData;
        int layer[]={5,13,12,1};
        bpSolution_close=new BPSolution(layer,0.3,0.4);
        bpSolution_chg=new BPSolution(layer,0.1,0.3);
        baseMap=new LinkedHashMap<>();
        this.preStockMap=stockMap;
        for(int i=0;i<base.length;i++){
            base[i]=new double[2];
        }
        for (Map.Entry<Date, StockData> entry : stockDataMap.entrySet()) {
            baseMap.put(entry.getKey(),entry.getValue().getClose());
        }
    }

    public PrecisionVO getBpResult() {
        Set<Date> set = stockDataMap.keySet();
        Date[] dates = new Date[set.size()];
        set.toArray(dates);

        double train[][]=getTrain(stockDataMap);
        double target_close[][]=new double[train.length][2];
        for(int i=0;i<train.length-1;i++){
            target_close[i]=new double[1];
            target_close[i][0]=0.75*(train[i+1][1]/2+0.125);
        }
        double target_chg[][]=getTarget();
        for(int i=0;i<train.length-1;i++){
            for(int k=0;k<100;k++) {
                bpSolution_close.train(train[i], target_close[i]);
                bpSolution_chg.train(train[i],target_chg[i]);
            }
        }


        double predict[][]=getTrain(preStockMap);
        for(int i=0;i<predict.length-1;i++){
            target_close[i]=new double[1];
            target_close[i][0]=0.75*(predict[i+1][1]/2.0+0.125);
        }

        Map<Date, Double> result = new LinkedHashMap<>();
        for(int i=0;i<train.length;i++){
            double x=0;                                                                                                                                                                                                                            bpSolution_close.train(predict[i],target_close[i]);

            x=bpSolution_close.computeOut(predict[i])[0];

            x=reverseF(x);
            x=(x-0.125)/0.75;
            x=getF(x);
            result.put(dates[i+1],x);
        }

        for(int i=0;i<4;i++) {
            bpSolution_chg.train(train[i], target_chg[i]);
        }

        double todayIN[]=new double[5];
        todayIN[0]=(todayData.getOpen()  -base[0][0])/base[0][1]*2;
        todayIN[1]=(todayData.getClose() -base[1][0])/base[1][1]*2;
        todayIN[2]=(todayData.getVolume()-base[2][0])/base[2][1]*4;
        todayIN[3]=(todayData.getHigh()  -base[3][0])/base[3][1]*2;
        todayIN[4]=(todayData.getLow()   -base[4][0])/base[4][1]*2;
        double target[]=new double[1];
        target[0]=todayIN[1]*3/8+0.125;
        for(int i=0;i<10;i++)
            bpSolution_close.train(todayIN,target);
        double label=bpSolution_chg.computeOut(todayIN)[0];
        double chgValue=bpSolution_close.computeOut(todayIN)[0];
        chgValue=((reverseF(chgValue)-0.125)/0.75)*base[1][1]+base[1][0];
        chgValue-=todayData.getClose();
        boolean tag=true;
        if(chgValue<0){
            tag=false;
            label=1-label;
        }
        PrecisionVO po=new PrecisionVO();
        po.setBaseCloseSet(baseMap);
        po.setCloseSet(result);
        po.setIncreaseLabel(tag);
        po.setChg(StockInfoHelper.format_2(chgValue));
        po.setRate(StockInfoHelper.format(label)*100);
        return po;
    }

    private double[] getBase(ArrayList<Double> list){
        double []result={10000000,0};
        for(int i=0;i<list.size();i++){
            if(result[0]>list.get(i)){
                result[0]=list.get(i);
            }
            if(result[1]<list.get(i)){
                result[1]=list.get(i);
            }
        }
        result[1]=result[1]-result[0];
        return result;
    }

    private double reverseF(double x){
        return -Math.log(1/x-1);
    }

    private double getF(double x){
        return x*base[1][1]+base[1][0];
    }



    private double[][] getTrain(Map<Date, StockData> stockDataMap){
        ArrayList<Double> openList = new ArrayList<>();
        ArrayList<Double> overList = new ArrayList<>();
        ArrayList<Double> volList = new ArrayList<>();
        ArrayList<Double> highList = new ArrayList<>();
        ArrayList<Double> lowList = new ArrayList<>();
        ArrayList<Double> chgList = new ArrayList<>();
        //初始化数据
        for (Map.Entry<Date, StockData> entry : stockDataMap.entrySet()) {
            openList.add(entry.getValue().getOpen());
            overList.add(entry.getValue().getClose());
            volList.add(entry.getValue().getVolume());
            highList.add(entry.getValue().getHigh());
            lowList.add(entry.getValue().getLow());
            if (entry.getValue().getChg() > 0) {
                chgList.add(1.0);
            } else {
                chgList.add(0.0);
            }
            baseMap.put(entry.getKey(),entry.getValue().getClose());
        }
        base[0]=getBase(openList);
        base[1]=getBase(overList);
        base[2]=getBase(volList);
        base[3]=getBase(highList);
        base[4]=getBase(lowList);
        double train[][]=new double[overList.size()-2][];
        for(int i=0;i<train.length;i++){
            train[i]=new double[5];
            train[i][0]=2*((openList.get(i)-base[0][0])/base[0][1]);
            train[i][1]=2*((overList.get(i)-base[1][0])/base[1][1]);
            train[i][2]=2*((volList.get(i) -base[2][0])/base[2][1]);
            train[i][3]=2*((highList.get(i)-base[3][0])/base[3][1]);
            train[i][4]=2*((lowList.get(i) -base[4][0])/base[4][1]);

        }
        return train;
    }

    private double[][] getTarget(){

        ArrayList<Double> chgList = new ArrayList<>();
        //初始化数据
        for (Map.Entry<Date, StockData> entry : stockDataMap.entrySet()) {
            if (entry.getValue().getChg() > 0) {
                chgList.add(1.0);
            } else {
                chgList.add(0.0);
            }
        }
        double target[][]=new double[chgList.size()-2][];
        for(int i=0;i<target.length;i++){
            target[i]=new double[1];
            target[i][0]=chgList.get(i+1);
        }
        return target;
    }

}
