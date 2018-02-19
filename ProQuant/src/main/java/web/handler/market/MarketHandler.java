package web.handler.market;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import PO.kLinePO.KLineTimeData;
import com.google.gson.JsonObject;
import dataservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import PO.StockCurrentData;
import PO.marketInfoPO.MarketInfo;
import PO.recommendedStock.breakthroughPO;
import blservice.userDataBlService.UserDataService;
import data.RecommendedData.PeakUp;
import dataservice.recommendedData.BreakthroughService;
import model.ResultMessage;
import model.StockPlate;

/**
 * Created by xiaoJun on 2017/6/6.
 */

@Controller
@RequestMapping(value = "/MarketPage")
public class MarketHandler {
    //获得市场数据的服务
    @Autowired
    private MarketInfoService marketInfoService;
    @Autowired
    @Qualifier("PeU")
    private PeakUp peakUpService;

    @Autowired
    @Qualifier("BU")
    private BreakthroughService breakthroughService;

    //获得某一只股票数据的服务
    @Autowired
    private StockDataService stockDataService;

    //获得板块数据的服务
    @Autowired
    private BenchDataService benchDataService;

    //修改用户个人数据的服务
    @Autowired
    private UserDataService userDataService;

    //验证用户登录的服务
    @Autowired
    private UserLogService userLogService;

    @Autowired
    private KLineDataService kLineDataService;

    @RequestMapping("/toMarketPage")
    public String toMarketPage(Model model, HttpServletRequest request) {
        MarketInfo marketInfo = marketInfoService.getCurrentMarketInfo();
        model.addAttribute("marketInfo", marketInfo);

        ArrayList<breakthroughPO> tempHotStockPOS = breakthroughService.getDatas();
        ArrayList<breakthroughPO> hotStockPOS = new ArrayList<>();
        if (hotStockPOS.size() > 11) {
            for (int i = 0; i < 11; i++) {
                hotStockPOS.add(tempHotStockPOS.get(i));
            }
        } else {
            for (int i = 0; i < hotStockPOS.size(); i++) {
                hotStockPOS.add(tempHotStockPOS.get(i));
            }
        }

        model.addAttribute("hotStocks", hotStockPOS);

        Map<String, StockCurrentData> allStocks = stockDataService.getStockCurrentDataAll();
        model.addAttribute("allStocks", allStocks);
        ArrayList<StockCurrentData> arrayList = new ArrayList<>();
        ArrayList<Double> volumeArrayList = new ArrayList<>();
        ArrayList<Double> amountArrayList = new ArrayList<>();
        ArrayList<Double> changeArrayList = new ArrayList<>();
        if (allStocks != null) {
            for (Map.Entry<String, StockCurrentData> entry : allStocks.entrySet()) {
                arrayList.add(entry.getValue());
            }
        }
        formatCurrentData(arrayList, volumeArrayList, amountArrayList, changeArrayList, 0);
        request.getSession().setAttribute("allStocks", arrayList);
        request.getSession().setAttribute("allStockVolume", volumeArrayList);
        request.getSession().setAttribute("allStockAmount", amountArrayList);
        request.getSession().setAttribute("allStockChange", changeArrayList);
        //获得沪深300的股票
        ArrayList<String> hushen300 = benchDataService.getStockListOdBench(StockPlate.CSI300);
        ArrayList<StockCurrentData> hushen300Lists = new ArrayList<>();
        for (String code : hushen300) {
            hushen300Lists.add(allStocks.get(code));
        }
        ArrayList<Double> hushenVolume = new ArrayList<>();
        ArrayList<Double> hushenAmount = new ArrayList<>();
        ArrayList<Double> hushenChange = new ArrayList<>();
//        formatCurrentData(hushen300Lists, hushenVolume, hushenAmount, hushenChange, 1);
//        model.addAttribute("hushen300List", hushen300Lists);
//        request.getSession().setAttribute("hushen300Lists", hushen300Lists);
//        request.getSession().setAttribute("hushenVolume", hushenVolume);
//        request.getSession().setAttribute("hushenAmount", hushenAmount);
//        request.getSession().setAttribute("hushenChange", hushenChange);
        //获得创业板的股票
        ArrayList<String> chuangyeban = benchDataService.getStockListOdBench(StockPlate.CHINEXT);
        ArrayList<StockCurrentData> chuangyebanLists = new ArrayList<>();
        for (String code : chuangyeban) {
            chuangyebanLists.add(allStocks.get(code));
        }
        ArrayList<Double> chuangyeVolume = new ArrayList<>();
        ArrayList<Double> chuangyeAmount = new ArrayList<>();
        ArrayList<Double> chuangyeChange = new ArrayList<>();
        formatCurrentData(chuangyebanLists, chuangyeVolume, chuangyeAmount, chuangyeChange, 1);
        model.addAttribute("chuangyebanLists", chuangyebanLists);
        request.getSession().setAttribute("chuangyebanLists", chuangyebanLists);
        request.getSession().setAttribute("chuangyeVolume", chuangyeVolume);
        request.getSession().setAttribute("chuangyeAmount", chuangyeAmount);
        request.getSession().setAttribute("chuangyeChange", chuangyeChange);
        //获得中小板的股票
        ArrayList<String> zhongxiaoban = benchDataService.getStockListOdBench(StockPlate.SME);
        ArrayList<StockCurrentData> zhongxiaobanLists = new ArrayList<>();
        for (String code : zhongxiaoban) {
            zhongxiaobanLists.add(allStocks.get(code));
        }
        ArrayList<Double> zhongxiaoVolume = new ArrayList<>();
        ArrayList<Double> zhongxiaoAmount = new ArrayList<>();
        ArrayList<Double> zhongxiaoChange = new ArrayList<>();
        formatCurrentData(zhongxiaobanLists, zhongxiaoVolume, zhongxiaoAmount, zhongxiaoChange, 1);
        model.addAttribute("zhongxiaobanLists", zhongxiaobanLists);
        request.getSession().setAttribute("zhongxiaobanLists", zhongxiaobanLists);
        request.getSession().setAttribute("zhongxiaoVolume", zhongxiaoVolume);
        request.getSession().setAttribute("zhongxiaoAmount", zhongxiaoAmount);
        request.getSession().setAttribute("zhongxiaoChange", zhongxiaoChange);
        return "MarketPage";
    }


    /**
     * 该方法为添加自选股时使用的方法
     *
     * @param request
     */
    @RequestMapping(value = "/addMyStock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject addMyStock(HttpServletRequest request){
        JsonObject jsonObject = new JsonObject();
        String stockCode = request.getParameter("key");
        String[] myStocksLists;
        if (stockCode.equals("0")) {
            myStocksLists = request.getParameter("list").split(";");
            //0001用户添加自己的自选股
            System.out.println(myStocksLists.length);
            ResultMessage resultMessage = userDataService.addUserOptionalStocks(new ArrayList<>(Arrays.asList(myStocksLists)), (String)request.getSession().getAttribute("username"));
            System.out.println(resultMessage);
            jsonObject.addProperty("r", resultMessage.toString());
        }else{
            ArrayList<String> list = new ArrayList<>();
            list.add(stockCode);
            System.out.println("这里是市场界面加入自选股的方法，要打印用户名，验证是否得到该用户名"+(String) request.getSession().getAttribute("username"));
            ResultMessage resultMessage = userDataService.addUserOptionalStocks(list,(String)request.getSession().getAttribute("username"));
            System.out.println(stockCode);
            jsonObject.addProperty("r", resultMessage.toString());
            System.out.println(resultMessage.toString());
        }
        return jsonObject;
    }


    @RequestMapping(value = "/checkLoginState", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public JsonArray testLogin(HttpServletRequest request){
        JsonArray jsonArray = new JsonArray();
        String data = "";
        String isLogin = request.getParameter("loginState");
        if(isLogin.equals("false")){
            data = "[{'loginState':"+isLogin+"}]";
        }
        jsonArray.add(data);
        return jsonArray;
    }

    private double formatDouble(double num) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(num));
    }

    @RequestMapping(value = "/getMarketTimeLineData", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public JsonArray getMarketTimeLine() {
        JsonArray jsonArray = new JsonArray();
        JsonArray timeArray = new JsonArray();
        JsonArray hushenArray = new JsonArray();
        JsonArray chuangyeArray = new JsonArray();
        JsonArray zhongxiaoArray = new JsonArray();
        ArrayList<KLineTimeData> hushenList = kLineDataService.getTimeLine(StockPlate.codeOf(StockPlate.CSI300));
        ArrayList<KLineTimeData> chuangyeList = kLineDataService.getTimeLine(StockPlate.codeOf(StockPlate.CHINEXT));
        ArrayList<KLineTimeData> zhongxiaoList = kLineDataService.getTimeLine(StockPlate.codeOf(StockPlate.SME));
        for (int i = 0; i < hushenList.size(); i++) {
            timeArray.add(formatTime(hushenList.get(i).getTime()));
            hushenArray.add(hushenList.get(i).getPrice());
            chuangyeArray.add(chuangyeList.get(i).getPrice());
            zhongxiaoArray.add(zhongxiaoList.get(i).getPrice());
        }
        jsonArray.add(timeArray);
        jsonArray.add(hushenArray);
        jsonArray.add(chuangyeArray);
        jsonArray.add(zhongxiaoArray);
        return jsonArray;
    }

    @RequestMapping(value = "/getMarketUpAndDownData", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public JsonArray getMarketUpAndDownData(){
        JsonArray jsonArray = new JsonArray();
        JsonArray upAndDownArray = new JsonArray();
        JsonArray timeArray = new JsonArray();
        JsonArray upStopArray = new JsonArray();
        JsonArray downStopArray = new JsonArray();
        JsonArray fiveUpDownArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        MarketInfo marketInfo = marketInfoService.getCurrentMarketInfo();
        //涨跌分布
        for(int i = 0; i < marketInfo.getZdfb_data().getZdfb().size(); i++){
            upAndDownArray.add(marketInfo.getZdfb_data().getZdfb().get(i));
        }
        //涨跌停
        for(int i = 0; i < marketInfo.getZdt_data().getZd_time().size(); i++){
            timeArray.add(marketInfo.getZdt_data().getZd_time().get(i));
            upStopArray.add(marketInfo.getZdt_data().getZtzs().get(i));
            downStopArray.add(marketInfo.getZdt_data().getDtzs().get(i));
            fiveUpDownArray.add(marketInfo.getZdt_data().getFive().get(i));
        }
        jsonObject.addProperty("upNum",marketInfo.getZdfb_data().getZnum());    //上涨股票数量
        jsonObject.addProperty("downNum",marketInfo.getZdfb_data().getDnum());  //下跌股票数量
        jsonObject.addProperty("upStopNum",marketInfo.getZdt_data().getZtzs().  //涨停股票数量
                get(marketInfo.getZdt_data().getZtzs().size() - 1));
        jsonObject.addProperty("downStopNum",marketInfo.getZdt_data().getDtzs() //跌停股票数量
                .get(marketInfo.getZdt_data().getDtzs().size() - 1));
        jsonObject.addProperty("score",marketInfo.getDppj_data());
        jsonArray.add(upAndDownArray);
        jsonArray.add(timeArray);
        jsonArray.add(upStopArray);
        jsonArray.add(downStopArray);
        jsonArray.add(fiveUpDownArray);
        jsonArray.add(jsonObject);
        return jsonArray;
    }




    /**
     * 格式当前股票数据
     *
     * @param currentDatas
     * @param volumes
     * @param amounts
     * @param changes
     * @param type
     */
    private void formatCurrentData(ArrayList<StockCurrentData> currentDatas, ArrayList<Double> volumes,
                                   ArrayList<Double> amounts, ArrayList<Double> changes, int type) {
        for (int i = 0; i < currentDatas.size(); i++) {
            currentDatas.get(i).setChangepercent(formatDouble(currentDatas.get(i).getChangepercent()));
            currentDatas.get(i).setTurnoverratio(formatDouble(currentDatas.get(i).getTurnoverratio()));
            volumes.add(formatDouble(currentDatas.get(i).getVolume() / 10000));
            amounts.add(formatDouble(currentDatas.get(i).getAmount() / 10000));
            changes.add(formatDouble(currentDatas.get(i).getTrade() - currentDatas.get(i).getSettlement()));
            if (type == 0) {
                currentDatas.get(i).setNmc(formatDouble(currentDatas.get(i).getNmc() / 10000));
            } else {
                currentDatas.get(i).setNmc(formatDouble(currentDatas.get(i).getNmc()));
            }
            currentDatas.get(i).setPer(formatDouble(currentDatas.get(i).getPer()));
            currentDatas.get(i).setPb(formatDouble(currentDatas.get(i).getPb()));
            if (type == 0) {
                currentDatas.get(i).setMktcap(formatDouble(currentDatas.get(i).getMktcap() / 10000));
            } else {
                currentDatas.get(i).setMktcap(formatDouble(currentDatas.get(i).getMktcap()));
            }
        }
    }

    private String formatTime(String time) {
        if (time.length() == 8) {
            return "0" + time.substring(0, 1) + ":" + time.substring(1, 3);
        }
        return time.substring(0, 2) + ":" + time.substring(2, 4);
    }
}
