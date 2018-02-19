package VO.UserVO;

/**
 * Created by xiezhenyu on 2017/6/6.
 * 该VO对应界面"持仓记录"部分
 */
public class OwnedStocksVO {
    //股票名称
    private String stockName;
    //股票对应的最新价
    private String code;
    private Double newestPrice;
    //对应股票的持仓数量
    private Integer ownedNum;
   

    public OwnedStocksVO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getNewestPrice() {
        return newestPrice;
    }

    public void setNewestPrice(Double newestPrice) {
        this.newestPrice = newestPrice;
    }

    public Integer getOwnedNum() {
        return ownedNum;
    }

    public void setOwnedNum(Integer ownedNum) {
        this.ownedNum = ownedNum;
    }

}
