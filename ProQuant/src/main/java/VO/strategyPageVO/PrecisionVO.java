package VO.strategyPageVO;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiezhenyu on 2017/5/25.
 * 该VO是在展示BP神经网络算法准确性时用到
 */
public class PrecisionVO {

    //预测收盘价的集合
    private Map<Date, Double> closeSet;
    //收盘价的集合
    private Map<Date, Double> baseCloseSet;
    //策略准确的把握
    private double rate;
    //涨跌值
    private double chg;
    //是否涨，true为涨，false为跌
    private boolean increaseLabel;

    public PrecisionVO(Map<Date, Double> closeSet, Map<Date, Double> baseCloseSet, double rate, double chg, boolean increaseLabel) {
        this.closeSet = closeSet;
        this.baseCloseSet = baseCloseSet;
        this.rate = rate;
        this.chg = chg;
        this.increaseLabel = increaseLabel;
    }

    public PrecisionVO() {

        this.closeSet=new LinkedHashMap<>();
        this.baseCloseSet=new LinkedHashMap<>();
    }

    public Map<Date, Double> getCloseSet() {
        return closeSet;
    }

    public void setCloseSet(Map<Date, Double> closeSet) {
        this.closeSet = closeSet;
    }

    public Map<Date, Double> getBaseCloseSet() {
        return baseCloseSet;
    }

    public void setBaseCloseSet(Map<Date, Double> baseCloseSet) {
        this.baseCloseSet = baseCloseSet;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getChg() {
        return chg;
    }

    public void setChg(double chg) {
        this.chg = chg;
    }

    public boolean isIncreaseLabel() {
        return increaseLabel;
    }

    public void setIncreaseLabel(boolean increaseLabel) {
        this.increaseLabel = increaseLabel;
    }
}
