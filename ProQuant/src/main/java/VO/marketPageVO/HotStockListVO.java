package VO.marketPageVO;

import PO.recommendedStock.PeakPO;

import java.util.ArrayList;

/**
 * Created by xiezhenyu on 2017/6/7.
 */
public class HotStockListVO {
    private ArrayList<PeakPO> hotStocks;

    public HotStockListVO(ArrayList<PeakPO> hotStocks) {
        this.hotStocks = hotStocks;
    }

    public ArrayList<PeakPO> getHotStocks() {
        return hotStocks;
    }

    public void setHotStocks(ArrayList<PeakPO> hotStocks) {
        this.hotStocks = hotStocks;
    }
}
