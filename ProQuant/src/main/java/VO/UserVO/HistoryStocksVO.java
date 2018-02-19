package VO.UserVO;

/**
 * Created by xiezhenyu on 2017/6/6.
 * 该VO对应界面浏览过的股票
 */
public class HistoryStocksVO {
    //股票代码
    private String code;
    //股票名称
    private String name;

    public HistoryStocksVO() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
