package VO;

import java.util.Date;

/**
 * Created by LENOVO on 2017/5/26.
 */
public class StockKLine {
    private String name;//股票名称
    private int id;//股票id

    private Date date;//日期

    private double highValue;//最高值
    private double lowValue;//最低值
    private double openValue;//开盘价
    private double closeValue;//收盘价

    public StockKLine(String name, int id, Date date, double highValue, double lowValue, double openValue, double closeValue) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.highValue = highValue;
        this.lowValue = lowValue;
        this.openValue = openValue;
        this.closeValue = closeValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
