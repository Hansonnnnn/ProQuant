package VO.UserVO;


/**
 * Created by xiezhenyu on 2017/6/6.
 * 该VO对应用户界面的自选股
 */
public class UserOptionalStocksListVO {
    //股票ID
    private String id;
    //股票名称
    private String name;
    //股票现价
    private double currentPrice;
    //涨幅跌幅
    private double chg;
    //成交量
    private int  volume;
    public UserOptionalStocksListVO(String id,String name,double trade,double chg,double volume){
    	this.id=id;
    	this.name=name;
    	this.chg=chg;
    	this.currentPrice=trade;
    	this.volume=(int)volume;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getChg() {
        return chg;
    }

    public void setChg(double chg) {
        this.chg = chg;
    }

    public int  getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = (int)volume;
    }
}
