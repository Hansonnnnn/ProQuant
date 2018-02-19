package web.handler.stock;

import PO.InfoData;
import PO.StockData;
import PO.StockScore;
import PO.kLinePO.KLineDayData;
import PO.kLinePO.KLineTimeData;
import PO.recommendedStock.ContinuingTrendPO;
import VO.StockVO;
import VO.UserVO.AccountVO;
import blservice.StockInfoBlService.StockInfoService;
import blservice.userDataBlService.UserDataService;
import com.google.gson.JsonObject;
import com.sun.javafx.sg.prism.NGShape;
import dataservice.KLineDataService;
import dataservice.StockDataService;
import com.google.gson.JsonArray;
import dataservice.StockScoreService;
import dataservice.UserTradeService;
import dataservice.recommendedData.ContinuingTrendService;
import model.UserTradeSingal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by LENOVO on 2017/6/6.
 */

@Controller
@RequestMapping("/StockInfo")
public class StockInfo extends HttpServlet{

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockDataService stockDataService;

    @Autowired
    private KLineDataService kLineDataService;

    @Autowired
    private StockScoreService stockScoreService;


    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserTradeService userTradeService;

    @Autowired
    @Qualifier("CTU")
    private ContinuingTrendService continuingTrendService;

    @RequestMapping(value = "/{stockId}/toStockInfo",method = RequestMethod.GET)
    public String toStockInfo(Model model,@PathVariable String stockId,HttpServletResponse response,HttpServletRequest request){
        response.setCharacterEncoding("UTF-8");
        request.getSession().setAttribute("stockId",stockId);
        StockVO stockVO = stockInfoService.getStockVO(stockId);
        stockVO.setPrice_Of_Increase(formatDouble(stockVO.getPrice_Of_Increase()));
        stockVO.setAmount_Of_Increase(formatDouble(stockVO.getAmount_Of_Increase()));
//        stockVO.setVolume(formatDouble((double)(stockVO.getVolume()/10000)));
        stockVO.setAmplitude(formatDouble((stockVO.getAmplitude()*100)));
        stockVO.setTurnover(formatDouble((stockVO.getTurnover()/10000)));
        stockVO.setTurnoverRate(formatDouble(stockVO.getTurnoverRate()));
        stockVO.setMarketValue(formatDouble(stockVO.getMarketValue()/10000));
        stockVO.setMarketRate(formatDouble(stockVO.getMarketRate()));
        stockVO.setCirculationMarketValue(formatDouble(stockVO.getCirculationMarketValue()/10000));
        stockVO.setPriceEarningsRatio(formatDouble(stockVO.getPriceEarningsRatio()));
        model.addAttribute("stockVO",stockVO);
        request.getSession().setAttribute("stockVO",stockVO);
//        int code = stockDataService.getCode("");
        InfoData companyData = stockDataService.getStockInfo(stockId);
        model.addAttribute("companyData",companyData);

//        getStockHistoryData(request);
        Map<Date, StockData> stockDataMap = stockDataService.getBasicDateStock(new Date(),365,stockId);
        ArrayList<StockData> stockDataArrayList = new ArrayList<>();
        ArrayList<String> stockHistoryDate = new ArrayList<>();
        for (Map.Entry<Date, StockData> entry : stockDataMap.entrySet()) {
            stockDataArrayList.add(entry.getValue());
            stockHistoryDate.add(DateToString(entry.getKey()));
        }
        request.getSession().setAttribute("stockDataArrayList",stockDataArrayList);
        request.getSession().setAttribute("stockHistoryDate",stockHistoryDate);
//        System.out.println("wozaina");

        ArrayList<ContinuingTrendPO> continuingTrendPOArrayList = continuingTrendService.getDatas();

        model.addAttribute("CTU", continuingTrendPOArrayList.subList(0, 14));



        //添加创新高股票

        return "StockInfo";
    }

    @RequestMapping(value = "/{stockId}/getStockTimeLine.do",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public JsonArray getTimeLine(@PathVariable String stockId){
        JsonArray jsonArray = new JsonArray();
        ArrayList<KLineTimeData> kLineTimeDatas = kLineDataService.getTimeLine(stockId);
        JsonArray jsonTimeArray = new JsonArray();
        JsonArray jsonPriceArray = new JsonArray();
        JsonArray jsonVolumeArray = new JsonArray();
        int size = kLineTimeDatas.size();
        for(int i = 0; i < size; i++){
            jsonTimeArray.add(formaterTime(kLineTimeDatas.get(i).getTime()));
            jsonPriceArray.add(kLineTimeDatas.get(i).getPrice());
            jsonVolumeArray.add(kLineTimeDatas.get(i).getVolume());
        }
        jsonArray.add(jsonTimeArray);
        jsonArray.add(jsonPriceArray);
        jsonArray.add(jsonVolumeArray);
        return  jsonArray;
    }

    /**
     * 获取日K线数据
     * @return
     */
    @RequestMapping(value = "/{stockId}/getStockDayKLine",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public JsonArray getDayKLine(@PathVariable String stockId){
        ArrayList<KLineDayData> kLineDayDatas = kLineDataService.getdayLine(stockId,null,365,false);
        int size = kLineDayDatas.size();
        JsonArray jsonArray = new JsonArray();
        JsonArray jsonDateArray = new JsonArray();
        JsonArray jsonValueArray = new JsonArray();
        JsonArray jsonVolumeArray = new JsonArray();
        JsonArray jsonM5Array = new JsonArray();
        JsonArray jsonM10Array = new JsonArray();
        JsonArray jsonM20Array = new JsonArray();
        for(int i = size - 1; i >= 0; i--){
            JsonArray jsonArray1 = new JsonArray();
            jsonDateArray.add(DateToString(kLineDayDatas.get(i).getDate()));
            jsonArray1.add(kLineDayDatas.get(i).getKline().getOpen());
            jsonArray1.add(kLineDayDatas.get(i).getKline().getClose());
            jsonArray1.add(kLineDayDatas.get(i).getKline().getLow());
            jsonArray1.add(kLineDayDatas.get(i).getKline().getHigh());
            jsonVolumeArray.add(kLineDayDatas.get(i).getKline().getVolume());
            jsonValueArray.add(jsonArray1);
            jsonM5Array.add(kLineDayDatas.get(i).getMa5().getAvgPrice());
            jsonM10Array.add(kLineDayDatas.get(i).getMa10().getAvgPrice());
            jsonM20Array.add(kLineDayDatas.get(i).getMa20().getAvgPrice());
        }
        jsonArray.add(jsonDateArray);       //下标0为日期
        jsonArray.add(jsonValueArray);      //下标1为开收低高数据
        jsonArray.add(jsonVolumeArray);     //下标2为成交量
        jsonArray.add(jsonM5Array);         //下标3为五日均线值
        jsonArray.add(jsonM10Array);        //下标4为十日均线值
        jsonArray.add(jsonM20Array);        //下标5为二十日均线值
        return jsonArray;
    }

    /**
     * 获取股票周K数据
     * @return
     */
    @RequestMapping(value = "/{stockId}/getStockWeekKLine",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public JsonArray getWeekKLine(@PathVariable String stockId){
        ArrayList<KLineDayData> kLineWeekDatas = kLineDataService.getweekLine(stockId,null,300,false);
        int size = kLineWeekDatas.size();
        JsonArray jsonArray = new JsonArray();
        JsonArray jsonDateArray = new JsonArray();
        JsonArray jsonValueArray = new JsonArray();
        JsonArray jsonVolumeArray = new JsonArray();
        JsonArray jsonM5Array = new JsonArray();
        JsonArray jsonM10Array = new JsonArray();
        JsonArray jsonM20Array = new JsonArray();
        for(int i = size - 1; i >= 0; i--){
            JsonArray jsonArray1 = new JsonArray();
            jsonDateArray.add(DateToString(kLineWeekDatas.get(i).getDate()));
            jsonArray1.add(kLineWeekDatas.get(i).getKline().getOpen());
            jsonArray1.add(kLineWeekDatas.get(i).getKline().getClose());
            jsonArray1.add(kLineWeekDatas.get(i).getKline().getLow());
            jsonArray1.add(kLineWeekDatas.get(i).getKline().getHigh());
            jsonVolumeArray.add(kLineWeekDatas.get(i).getKline().getVolume());
            jsonM5Array.add(kLineWeekDatas.get(i).getMa5().getAvgPrice());
            jsonM10Array.add(kLineWeekDatas.get(i).getMa10().getAvgPrice());
            jsonM20Array.add(kLineWeekDatas.get(i).getMa20().getAvgPrice());
            jsonValueArray.add(jsonArray1);

        }
        jsonArray.add(jsonDateArray);       //下标0为日期
        jsonArray.add(jsonValueArray);      //下标1为开收低高数据
        jsonArray.add(jsonVolumeArray);     //下标2为成交量
        jsonArray.add(jsonM5Array);         //下标3为五日均线值
        jsonArray.add(jsonM10Array);        //下标4为十日均线值
        jsonArray.add(jsonM20Array);        //下标5为二十日均线值
        return jsonArray;
    }

    /**
     * 获取股票周K数据
     * @return
     */
    @RequestMapping(value = "/{stockId}/getStockMonthKLine",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public JsonArray getMonthKLine(@PathVariable String stockId){
        ArrayList<KLineDayData> kLineWeekDatas = kLineDataService.getmonthLine(stockId,null,100,false);
        int size = kLineWeekDatas.size();
        JsonArray jsonArray = new JsonArray();
        JsonArray jsonDateArray = new JsonArray();
        JsonArray jsonValueArray = new JsonArray();
        JsonArray jsonVolumeArray = new JsonArray();
        JsonArray jsonM5Array = new JsonArray();
        JsonArray jsonM10Array = new JsonArray();
        JsonArray jsonM20Array = new JsonArray();
        for(int i = size - 1; i >= 0; i--){
            JsonArray jsonArray1 = new JsonArray();
            jsonDateArray.add(DateToString(kLineWeekDatas.get(i).getDate()));
            jsonArray1.add(kLineWeekDatas.get(i).getKline().getOpen());
            jsonArray1.add(kLineWeekDatas.get(i).getKline().getClose());
            jsonArray1.add(kLineWeekDatas.get(i).getKline().getLow());
            jsonArray1.add(kLineWeekDatas.get(i).getKline().getHigh());
            jsonVolumeArray.add(kLineWeekDatas.get(i).getKline().getVolume());
            jsonM5Array.add(kLineWeekDatas.get(i).getMa5().getAvgPrice());
            jsonValueArray.add(jsonArray1);
            jsonM10Array.add(kLineWeekDatas.get(i).getMa10().getAvgPrice());
            jsonM20Array.add(kLineWeekDatas.get(i).getMa20().getAvgPrice());
        }
        jsonArray.add(jsonDateArray);       //下标0为日期
        jsonArray.add(jsonValueArray);      //下标1为开收低高数据
        jsonArray.add(jsonVolumeArray);     //下标2为成交量
        jsonArray.add(jsonM5Array);         //下标3为五日均线值
        jsonArray.add(jsonM10Array);        //下标4为十日均线值
        jsonArray.add(jsonM20Array);        //下标5为二十日均线值
        return jsonArray;
    }

    /**
     * 获取个股信息中股票评分数据
     * @return
     */
    @RequestMapping(value = "/getStockScore",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject getStockScore(HttpServletRequest request){
        String stockId = (String)request.getSession().getAttribute("stockId");
        StockScore stockScore = stockScoreService.getStockScore(stockId);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("score",stockScore.getAnalyzeNum());
        jsonObject.addProperty("beat",stockScore.getBeatNum());
        jsonObject.addProperty("technology",stockScore.getStars().get(0));
        jsonObject.addProperty("fund",stockScore.getStars().get(1));
        jsonObject.addProperty("info",stockScore.getStars().get(2));
        jsonObject.addProperty("industry",stockScore.getStars().get(3));
        jsonObject.addProperty("base",stockScore.getStars().get(4));
        return jsonObject;
    }


    public void getStockHistoryData(HttpServletRequest request){

    }


    /**
     * 将date转string
     * @param date
     * @return
     */
    private String DateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    private String formaterTime(String time){
        if(time.length() == 8){
            return "0"+time.substring(0,1)+":"+time.substring(1,3);
        }
        return time.substring(0,2)+":"+time.substring(2,4);
    }

    private double formatDouble(double num){
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(num));
    }


    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject getUserAccountInfo(HttpServletRequest request){

        String price = request.getParameter("price");
        System.out.println("System.out.println(price);" + price);
        String username = request.getParameter("username");
        System.out.println(username);
        AccountVO accountVO = userDataService.getUserAccountData(username).getAccountVO();
        System.out.println("执行完成bl方法");
//        System.out.println(accountVO == null);
        double availableProperty = accountVO.getAvailableProperty();
//        System.out.println(availableProperty);
        double p = Double.parseDouble(price);
//        double p = 13.4;
        int maxNum = (int)(availableProperty/p);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ap", availableProperty);
        jsonObject.addProperty("maxNum", maxNum);
        jsonObject.addProperty("tempNum", 0);

        return jsonObject;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject buy(HttpServletRequest request){
        JsonObject jsonObject = new JsonObject();
        String username = request.getParameter("username");
        String code = request.getParameter("code");
        String num = request.getParameter("num");


        UserTradeSingal userTradeSingal = userTradeService.buy(username, code, Integer.parseInt(num));
        jsonObject.addProperty("singal", userTradeSingal.toString());
        return jsonObject;
    }
}
