package VO;

import model.StockPlate;

import java.util.ArrayList;

/**
 * Created by xiezhenyu on 2017/5/27.
 * 该类为股票池的VO，存放了两个属性，一个是股票池的类型，一个是对应的股票code（主要是针对自选股）
 * 设置该类的理由：为了是传入的参数更加一致，协调全部股票、板块和自选股，设置该类
 * 对于全部股票和板块，则股票名列表为空，自选股，股票名列表放入对应的股票名集合
 */
public class StockPlateVO {
    private StockPlate stockPlate;
    private ArrayList<String> stocksNameList;

//    public StockPlateVO() {
//    }


    public StockPlateVO(StockPlate stockPlate, ArrayList<String> stocksNameList) {
        this.stockPlate = stockPlate;
        this.stocksNameList = stocksNameList;
    }

    public StockPlate getStockPlate() {
        return stockPlate;
    }

    public void setStockPlate(StockPlate stockPlate) {
        this.stockPlate = stockPlate;
    }

    public ArrayList<String> getStocksNameList() {
        return stocksNameList;
    }

    public void setStocksNameList(ArrayList<String> stocksNameList) {
        this.stocksNameList = stocksNameList;
    }
}
