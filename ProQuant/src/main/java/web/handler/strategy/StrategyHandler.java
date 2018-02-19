package web.handler.strategy;

import VO.StockPlateVO;
import VO.strategyPageVO.ParamDataVO;
import VO.strategyPageVO.PrecisionVO;
import VO.strategyPageVO.StrategyCallbackVO;
import VO.strategyPageVO.StrategyEvalutingVO;
import blservice.strategyBlService.StrategyService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.StockPlate;
import model.StrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utility.DateHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by xiezhenyu on 2017/6/9.
 */
@Controller
@RequestMapping(value = "/Strategy")
public class StrategyHandler {
    @Autowired
    private StrategyService strategyService;


    private static final String BP = "BP";
    private static final String movement = "movement";
    private static final String average = "average";
    private static final String allStocks = "allStocks";
    private static final String hushen300 = "hushen300";
    private static final String chuangyeban = "chuangyeban";
    private static final String zhongxiaoban = "zhongxiaoban";
    private static final String zixuangu = "zixuangu";
//    private static final String BP = "BP";


    @RequestMapping(value = "/toStrategy")
    public String toStrategyPage(){
        return "StrategyPage";
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonArray callback(HttpServletRequest request){


        System.out.println("*****************");
        System.out.println("*****************");

        String username = (String) request.getSession().getAttribute("username");
        String algorithmName = request.getParameter("algorithmName");
        String stockPlate = request.getParameter("stockPlate");
        String baseStandard = request.getParameter("baseStandard");
        String processingDays = request.getParameter("processingDays");
        String holdDays = request.getParameter("holdDays");
        String maxHoldNum = request.getParameter("maxHoldNum");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");



        //默认使用动量策略
        StrategyType strategyType = StrategyType.MomentumDriven;
        StockPlate stockPlate1 = StockPlate.CSI300;
        StockPlateVO stockPlateVO = null;
        //策略基准
        StockPlate stockPlate2 = StockPlate.CSI300;

        switch (algorithmName){
            case BP : strategyType = StrategyType.BPNetwork; break;
            case movement : strategyType = StrategyType.MomentumDriven; break;
            case average : strategyType = StrategyType.ReversionDriven; break;
        }
        switch (stockPlate){
            case allStocks:
                stockPlate1 = StockPlate.ALLSTOCKS;
                stockPlateVO = new StockPlateVO(stockPlate1, new ArrayList<>());
                break;
            case hushen300:
                stockPlate1 = StockPlate.CSI300;
                stockPlateVO = new StockPlateVO(stockPlate1, new ArrayList<>());
                break;
            case chuangyeban:
                stockPlate1 = StockPlate.CHINEXT;
                stockPlateVO = new StockPlateVO(stockPlate1, new ArrayList<>());
                break;
            case zhongxiaoban:
                stockPlate1 = StockPlate.SME;
                stockPlateVO = new StockPlateVO(stockPlate1, new ArrayList<>());
                break;
            case zixuangu:
                stockPlate1 = StockPlate.POOLOFUSER;
                stockPlateVO = new StockPlateVO(stockPlate1, new ArrayList<>());
                break;
        }
        switch (baseStandard){
            case hushen300:
                stockPlate2 = StockPlate.CSI300;
                break;
            case chuangyeban:
                stockPlate2 = StockPlate.CHINEXT;
                break;
            case zhongxiaoban:
                stockPlate2 = StockPlate.SME;
                break;
        }

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, Integer.parseInt(startDate.split("-")[0]));
        gc.set(Calendar.MONTH, (Integer.parseInt(startDate.split("-")[1]) - 1));
        gc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(startDate.split("-")[2]));
        Date sDate = gc.getTime();
        System.out.println(sDate);

        gc.set(Calendar.YEAR, Integer.parseInt(endDate.split("-")[0]));
        gc.set(Calendar.MONTH, (Integer.parseInt(endDate.split("-")[1]) - 1));
        gc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDate.split("-")[2]));
        Date eDate = gc.getTime();
        System.out.println(eDate);

//        String username, StockPlateVO stockPlateVO, StockPlate stockPlate,
//        int possessingDays,int holdDays, int maxHoldNum, Date startDate, Date endDate,StrategyType type);
        StrategyCallbackVO strategyCallbackVO = strategyService.getCalResultOnExistStrategy(username, stockPlateVO, stockPlate2, Integer.parseInt(processingDays),
                Integer.parseInt(holdDays), Integer.parseInt(maxHoldNum), sDate, eDate, strategyType);

        System.out.println("666666666666");
        System.out.println((strategyCallbackVO==null));
        System.out.println("888888888888");


        JsonArray totalJsonArray = new JsonArray();
        JsonArray earningsLineArray = new JsonArray();
        JsonArray earningCircleArray = new JsonArray();
        JsonArray strategyEvalutingArray = new JsonArray();
        JsonArray tableArray = new JsonArray();


        //为earningsLineArray填充数据
        JsonArray baseEarningsDataDoubleArray = new JsonArray();
        JsonArray strategyEarningsDataDoubleArray = new JsonArray();
        JsonArray earningsLineDateArray = new JsonArray();
        Map<Date, Double> baseEarningsMap = strategyCallbackVO.getEarningsLineVO().getBaseEarningsData();
        Map<Date, Double> strategyEarningsMap = strategyCallbackVO.getEarningsLineVO().getStrategyEarningsData();

        for(Date date:baseEarningsMap.keySet()){
            System.out.println(date);
            baseEarningsDataDoubleArray.add(baseEarningsMap.get(date));//装基准收益率的值
            strategyEarningsDataDoubleArray.add(strategyEarningsMap.get(date));//装策略收益率的值
            earningsLineDateArray.add(DateHelper.dateToStringTwo(date));//装两个值对应的值
        }

        earningsLineArray.add(baseEarningsDataDoubleArray);
        earningsLineArray.add(strategyEarningsDataDoubleArray);
        earningsLineArray.add(earningsLineDateArray);

        //为earningsLineArray填充数据完成

        //为earningCircleArray填充数据
        JsonArray peDataLogRateArray = new JsonArray();//装正收益率的值
        JsonArray peDataTimesArray = new JsonArray();//装正收益率值对应的频数
        JsonArray neDataLogRateArray = new JsonArray();//装负收益率的值
        JsonArray neDataTimesArray = new JsonArray();//装负收益率对应的频数
        JsonArray peCircleNumArray = new JsonArray();//装正收益周期数
        JsonArray neCircleNumArray = new JsonArray();//装负收益周期数
        JsonArray winRateArray = new JsonArray();//装赢率，是一个值

        Map<Integer, Integer> peDataMap = strategyCallbackVO.getEarningCircleVO().getPeData();
        Map<Integer, Integer> neDataMap = strategyCallbackVO.getEarningCircleVO().getNeData();
        for(Integer logRate : peDataMap.keySet()){
            System.out.println("这是正收益率" + logRate);
            peDataLogRateArray.add(logRate);
            peDataTimesArray.add(peDataMap.get(logRate));
        }
        for (Integer logRate : neDataMap.keySet()){
            neDataLogRateArray.add(logRate);
            neDataTimesArray.add(neDataMap.get(logRate));
        }
        peCircleNumArray.add(strategyCallbackVO.getEarningCircleVO().getPeCircleNum());
        neCircleNumArray.add(strategyCallbackVO.getEarningCircleVO().getNeCircleNum());
        winRateArray.add(strategyCallbackVO.getEarningCircleVO().getWinRate());

        earningCircleArray.add(peDataLogRateArray);
        earningCircleArray.add(peDataTimesArray);
        earningCircleArray.add(neDataLogRateArray);
        earningCircleArray.add(neDataTimesArray);
        earningCircleArray.add(peCircleNumArray);
        earningCircleArray.add(neCircleNumArray);
        earningCircleArray.add(winRateArray);
        //为earningCircleArray填充数据完成


        //为strategyEvalutingArray填充数据
        StrategyEvalutingVO strategyEvalutingVO = strategyCallbackVO.getStrategyEvalutingVO();
        JsonObject strategyEvalutingObject = new JsonObject();
        strategyEvalutingObject.addProperty("earningsValue", strategyEvalutingVO.getEarningsValue());
        strategyEvalutingObject.addProperty("realPlateValue", strategyEvalutingVO.getRealPlateValue());
        strategyEvalutingObject.addProperty("robustnessValue",strategyEvalutingVO.getRobustnessValue());
        strategyEvalutingObject.addProperty("mobilityValue", strategyEvalutingVO.getMobilityValue());
        strategyEvalutingObject.addProperty("anti_riskValue", strategyEvalutingVO.getAnti_riskValue());
        strategyEvalutingArray.add(strategyEvalutingObject);
        //为strategyEvalutingArray填充数据完成

        //为tableArray填充数据
        ParamDataVO paramStrategyDataVO = strategyCallbackVO.getParamStrategyDataVO();
        ParamDataVO paramBaseDataVO = strategyCallbackVO.getParamBaseDataVO();

        JsonObject paramStrategyDataObject = new JsonObject();
        JsonObject paramBaseDataObject = new JsonObject();
        paramStrategyDataObject.addProperty("alpha", paramStrategyDataVO.getAlpha());
        paramStrategyDataObject.addProperty("beta", paramStrategyDataVO.getBeta());
        paramStrategyDataObject.addProperty("annualizedRateOfReturn", paramStrategyDataVO.getAnnualizedRateOfReturn());
        paramStrategyDataObject.addProperty("sharpeRatio", paramStrategyDataVO.getSharpeRatio());
        paramStrategyDataObject.addProperty("biggestReturn", paramStrategyDataVO.getBiggestReturn());
        paramStrategyDataObject.addProperty("rateOfTotalReturn", paramStrategyDataVO.getRateOfTotalReturn());

        paramBaseDataObject.addProperty("alpha",paramBaseDataVO.getAlpha());
        paramBaseDataObject.addProperty("beta",paramBaseDataVO.getBeta());
        paramBaseDataObject.addProperty("annualizedRateOfReturn",paramBaseDataVO.getAnnualizedRateOfReturn());
        paramBaseDataObject.addProperty("sharpeRatio",paramBaseDataVO.getSharpeRatio());
        paramBaseDataObject.addProperty("biggestReturn",paramBaseDataVO.getBiggestReturn());
        paramBaseDataObject.addProperty("rateOfTotalReturn",paramBaseDataVO.getRateOfTotalReturn());

        tableArray.add(paramStrategyDataObject);
        tableArray.add(paramBaseDataObject);
        //为tableArray填充数据完成

        //为总数组填充数据
        totalJsonArray.add(earningsLineArray);
        totalJsonArray.add(earningCircleArray);
        totalJsonArray.add(strategyEvalutingArray);
        totalJsonArray.add(tableArray);
        //为总数组填充数据完成

        return totalJsonArray;
    }


    @RequestMapping(value = "/bp" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonArray getPrecisionVO(HttpServletRequest request){
        System.out.println("getPrecisionVO********************");
        String code = request.getParameter("code");
        System.out.println(code);
        PrecisionVO precisionVO = strategyService.getDataForShowPrecision(code);
        //private Map<Date, Double> closeSet;

        JsonArray totalArray = new JsonArray();
        //日期对应的数组，两组数据（预测收盘价和真实收盘对应一组日期数据）
        JsonArray dateArray = new JsonArray();
        //预测收盘价的数组
        JsonArray doubleArray = new JsonArray();
        //真实收盘价的数组
        JsonArray baseDoubleArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("up",precisionVO.isIncreaseLabel());
        jsonObject.addProperty("rate",precisionVO.getRate());
        jsonObject.addProperty("change",precisionVO.getChg());

        Map<Date, Double> closeSet = precisionVO.getCloseSet();
        Map<Date, Double> baseCloseSet = precisionVO.getBaseCloseSet();

        for(Date date:closeSet.keySet()){
            dateArray.add(DateHelper.dateToStringTwo(date));
            doubleArray.add(closeSet.get(date));
            baseDoubleArray.add(baseCloseSet.get(date));
        }
        totalArray.add(dateArray);
        totalArray.add(doubleArray);
        totalArray.add(baseDoubleArray);
        totalArray.add(jsonObject);
        System.out.println("执行完毕！！！！！！！！！！！！！！");
        System.out.println(totalArray.toString());
        return totalArray;
    }

    @RequestMapping(value = "/addStrategyRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject addStrategyRecord(HttpServletRequest request){
        JsonObject jsonObject = new JsonObject();

        return jsonObject;
    }
}
