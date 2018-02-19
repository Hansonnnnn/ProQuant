package VO;

import java.util.Date;

/**
 * Created by xiaoJun on 2017/5/26.
 */
public class StockDataVO {
    private String name;//股票名称
    private String  id;//股票id

    private Date date;//日期

    private double highValue;//最高值
    private double lowValue;//最低值
    private double openValue;//开盘价
    private double closeValue;//收盘价
    private double turnoverRate;//换手率
    private double marketRate;//市净率
    private double priceEarningsRatio;//市盈率

    public StockDataVO(String name, String  id, Date date, double highValue, double lowValue, double openValue, double closeValue,
                       double turnoverRate, double marketRate, double priceEarningsRatio) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.highValue = highValue;
        this.lowValue = lowValue;
        this.openValue = openValue;
        this.closeValue = closeValue;
        this.turnoverRate = turnoverRate;
        this.marketRate = marketRate;
        this.priceEarningsRatio = priceEarningsRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getHighValue() {
        return highValue;
    }

    public void setHighValue(double highValue) {
        this.highValue = highValue;
    }

    public double getLowValue() {
        return lowValue;
    }

    public void setLowValue(double lowValue) {
        this.lowValue = lowValue;
    }

    public double getOpenValue() {
        return openValue;
    }

    public void setOpenValue(double openValue) {
        this.openValue = openValue;
    }

    public double getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(double closeValue) {
        this.closeValue = closeValue;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public double getMarketRate() {
        return marketRate;
    }

    public void setMarketRate(double marketRate) {
        this.marketRate = marketRate;
    }

    public double getPriceEarningsRatio() {
        return priceEarningsRatio;
    }

    public void setPriceEarningsRatio(double priceEarningsRatio) {
        this.priceEarningsRatio = priceEarningsRatio;
    }
}
