package web.handler.stock;

import PO.StockBasicIndex;
import PO.recommendedStock.PeakPO;
import VO.StockForCompare;
import blservice.StockInfoBlService.StockInfoService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dataservice.StockDataService;
import dataservice.recommendedData.PeakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by LENOVO on 2017/6/6.
 */

@Controller
@RequestMapping("/StockCompare")
public class StockCompareHandler {
    @Autowired
    @Qualifier("PeU")
    private PeakService peakService;

    @Autowired
    private StockDataService stockDataService;

    @Autowired
    private StockInfoService stockInfoService;

    @RequestMapping("/toStockCompare")
    public String toStockCompare(Model model, HttpServletRequest request){
        ArrayList<PeakPO> peakPOS = peakService.getDatas();
//        model.addAttribute("peakPOS",peakPOS);
        request.getSession().setAttribute("peakPOS",peakPOS);
        return "StockCompare";
    }

    /**
     * 获取股票对比的表格数据信息
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCompareInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonArray compareInfo(HttpServletResponse response, HttpServletRequest request){
        response.setCharacterEncoding("UTF-8");
        String stockName = request.getParameter("name");
        System.out.print("stockNameArray6  "+stockName);
        String[] stockNameArray = stockName.split(",");
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < stockNameArray.length; i++){
            list.add(stockDataService.getCode(stockNameArray[i]));
        }
        JsonArray jsonArray = new JsonArray();
//        System.out.println("aaaa     "+list.get(0)+"  "+list.get(1)+"    "+list.get(2));
        ArrayList<StockBasicIndex> stockBasicIndices = stockDataService.getStockBasicIndex(list);
//        System.out.println("basic1     "+stockBasicIndices.get(0).getWeekChangeRatio());
//        int i = 0;
        for(int i = 0; i < stockNameArray.length; i++){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("weekChangeRatio",stockBasicIndices.get(i).getWeekChangeRatio());                //一周涨跌
            jsonObject.addProperty("monthChangeRatio",stockBasicIndices.get(i).getMonthChangeRatio());              //一月涨跌
            jsonObject.addProperty("seasonChangeRatio",stockBasicIndices.get(i).getSeasonChangeRatio());          //三月涨跌
            jsonObject.addProperty("halfayearChangeRatio",stockBasicIndices.get(i).getHalfayearChangeRatio());      //半年涨跌
            jsonObject.addProperty("yearChangeRatio",stockBasicIndices.get(i).getYearChangeRatio());                //一年涨跌
            jsonObject.addProperty("pe",stockBasicIndices.get(i).getPe());                                          //市盈率
            jsonObject.addProperty("pb",stockBasicIndices.get(i).getPb());                                          //市净率
            jsonObject.addProperty("pcf",stockBasicIndices.get(i).getPcf());                                        //市现率
            jsonObject.addProperty("ps",stockBasicIndices.get(i).getPs());                                          //市销率
            jsonObject.addProperty("perShareEarnings",stockBasicIndices.get(i).getPerShareEarnings());              //每股收益
            jsonObject.addProperty("netProfitMarginOnSales",stockBasicIndices.get(i).getNetProfitMarginOnSales());  //销售净利率
            jsonObject.addProperty("roa",stockBasicIndices.get(i).getRoa());                                        //资产收益率
            jsonObject.addProperty("debtAssetRatio",stockBasicIndices.get(i).getDebtAssetRatio());                  //资产负债率
            jsonObject.addProperty("currentRatio",stockBasicIndices.get(i).getCurrentRatio());                      //流动比率
            jsonObject.addProperty("mainBusinessIncome",stockBasicIndices.get(i).getMainBusinessIncome());          //主营收益(亿)
            jsonObject.addProperty("netProfit",stockBasicIndices.get(i).getNetProfit());                            //净利润(亿)
            jsonObject.addProperty("totalAssets",stockBasicIndices.get(i).getTotalAssets());                        //总资产
            jsonObject.addProperty("shareholdersEquity",stockBasicIndices.get(i).getShareholdersEquity());          //股东权益(亿)
            jsonArray.add(jsonObject);
//            System.out.println("weekChangeRatio    "+stockBasicIndices.get(i).getWeekChangeRatio());
//            System.out.println("monthChangeRatio    "+stockBasicIndices.get(i).getMonthChangeRatio());
//            System.out.println("seasonChangeRatio    "+stockBasicIndices.get(i).getSeasonChangeRatio());
//            StockBasicIndex stockBasicIndex = stockDataService.getStockBasicIndex();
//            jsonObject.addProperty("so");
        }
//        System.out.println(jsonArray.toString());
//        System.out.println(jsonArray.size());
        return jsonArray;
    }

    /**
     * 获取股票对比的图表数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCompareData",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonArray compareData(HttpServletRequest request,HttpServletResponse response){
        String stockName = request.getParameter("stockNameArray");
        String[] stockNameArray1 = stockName.split(",");
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < stockNameArray1.length; i++){
            System.out.println(stockNameArray1[i]);
            list.add(stockNameArray1[i]);
        }
        JsonArray jsonArray = new JsonArray();
        System.out.println("size1   "+list.size());
        ArrayList<StockForCompare> stockForCompares = stockInfoService.stockCompare(list);
        if(stockForCompares == null){
            System.out.println("StockForCompare=null  ");
        }else{
            System.out.println("size2  "+stockForCompares.size());
        }
        for(int i = 0; i < stockForCompares.size(); i++){
            JsonArray jsonStockArray = new JsonArray();
            JsonArray jsonTimeArray = new JsonArray();
            JsonArray jsonTimeArray1 = new JsonArray();
            JsonArray jsonRangeArray = new JsonArray();
            JsonArray jsonRangeArray1= new JsonArray();
            JsonArray jsonPriceArray = new JsonArray();
            JsonArray jsonPriceArray1 = new JsonArray();
            //获取分时图数据
            for (String string : stockForCompares.get(i).getTimeValue().keySet()){
                jsonTimeArray.add(string);
                jsonTimeArray1.add(stockForCompares.get(i).getTimeValue().get(string));
                System.out.print("test   "+stockForCompares.get(i).getTimeValue().get(string));
            }

            //获取涨跌幅数据
            for(Date date : stockForCompares.get(i).getDayIncrease().keySet()){
                jsonRangeArray.add(DateToString(date));
                jsonRangeArray1.add(stockForCompares.get(i).getDayIncrease().get(date));
            }

            //获取收盘价数据
            for(Date date : stockForCompares.get(i).getDayClose().keySet()){
                jsonPriceArray.add(DateToString(date));
                jsonPriceArray1.add(stockForCompares.get(i).getDayClose().get(date));
            }
            jsonStockArray.add(jsonTimeArray);
            jsonStockArray.add(jsonTimeArray1);
            jsonStockArray.add(jsonRangeArray);
            jsonStockArray.add(jsonRangeArray1);
            jsonStockArray.add(jsonPriceArray);
            jsonStockArray.add(jsonPriceArray1);
            jsonArray.add(jsonStockArray);
        }
        return jsonArray;
    }

    @RequestMapping(value = "/compareSearch",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonArray compareSearch(HttpServletRequest request){
        JsonArray jsonArray = new JsonArray();
        String nameOrCode = request.getParameter("nameOrCode");
        Map<String,Integer> map = stockDataService.searchStock(nameOrCode);
        for(Map.Entry<String,Integer> entry: map.entrySet()){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name",entry.getKey());
            jsonObject.addProperty(("code"),stockDataService.getCode(entry.getKey()));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    private String DateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(date);
        return dateString;
    }
}
