package VO.UserVO;

/**
 * Created by xiezhenyu on 2017/6/6.
 * 该VO对应界面"交易记录"部分
 */
public class DealRecordsVO {
    //交易日期
    private String dealDate;
    //股票名称
    private String stockName;
    //交易类型（*******************返回只有两种类型：买入或卖出，bl实现用枚举类，返回界面字符串******************）
    private String dealType;
    //交易量
    private Integer dealNum;
    //均值
    private Double averagePrice;

    public DealRecordsVO() {
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
