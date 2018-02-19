package blservice.userDataBlService;

import VO.UserVO.*;
import model.ResultMessage;

import java.util.ArrayList;

/**
 * Created by xiezhenyu on 2017/5/29.
 */
public interface UserDataService {
    /**
     * 返回用户中心-------个人账户界面的总数据
      * @param userName 用户名
     * @return
     */
    public AccountPageTotalVO getUserAccountData(String userName);



    /**
     * 返回用户中心---------自选股
     * @param userName 用户名
     * @return
     */
    public ArrayList<UserOptionalStocksListVO> getUserOptionalStocks(String userName);

    /**
     * 当用户删除自选股记录时，调用该方法修改数据
     * @param newOptionalStocksList 修改过的自选股列表（仅有股票的code）
     * @return
     */
    public ResultMessage deleteUserOptionalStocks(ArrayList<String> newOptionalStocksList,String userName);

    /**
     * 返回用户中心---------使用过的策略
     * @param userName 用户名
     * @return
     */
    public ArrayList<StrategyRecordVO> getStrategyRecordVO(String userName);

   
    /**
     * 该方法当用户添加自选股时使用
     * @param stockCodes 传入加入自选股的code
     * @return
     */
    public ResultMessage addUserOptionalStocks(ArrayList<String> stockCodes,String userName);

}
