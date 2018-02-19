package VO.UserVO;

/**
 * Created by xiezhenyu on 2017/6/6.
 * 该VO用来存放用户账户的信息
 */
public class AccountVO {
    //用户名
    private String userName;
    //总资产
    private double totalProperty;
    //可用资产
    private double availableProperty;
    //总盈亏
    private double totalProfitAndLoss;
    //今日盈亏
    private double dayProfitAndLoss;


    public AccountVO(String userName,double toalPro,double aviPro,double totalProfit,double dayProfit) {
        this.userName=userName;
        this.totalProperty=toalPro;
        this.availableProperty=aviPro;
        this.totalProfitAndLoss=totalProfit;
        this.dayProfitAndLoss=dayProfit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getTotalProperty() {
        return totalProperty;
    }

    public void setTotalProperty(double totalProperty) {
        this.totalProperty = totalProperty;
    }

    public double getAvailableProperty() {
        return availableProperty;
    }

    public void setAvailableProperty(double availableProperty) {
        this.availableProperty = availableProperty;
    }

    public double getTotalProfitAndLoss() {
        return totalProfitAndLoss;
    }

    public void setTotalProfitAndLoss(double totalProfitAndLoss) {
        this.totalProfitAndLoss = totalProfitAndLoss;
    }

    public double getDayProfitAndLoss() {
        return dayProfitAndLoss;
    }

    public void setDayProfitAndLoss(double dayProfitAndLoss) {
        this.dayProfitAndLoss = dayProfitAndLoss;
    }
}
