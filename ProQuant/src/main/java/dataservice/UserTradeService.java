package dataservice;

import java.util.List;

import PO.user.UserAccount;
import PO.user.UserStockOwned;
import PO.user.UserTradeRecord;
import model.UserTradeSingal;

public interface UserTradeService {

	
	
	/**
	 * @TODO：获得用户账户信息
	 * @param username
	 * @return
	 */
	public UserAccount getUserAccount(String username);
	
	/**
	 * @TODO：获得用户所持股票信息
	 * @param username
	 * @return
	 */
	public List<UserStockOwned> getUserStockOwned(String username);
	
	
	/**
	 * @TODO：获得用户模拟买卖交易记录
	 * @param username
	 * @return
	 */
	public List<UserTradeRecord> getUserTradeRecord(String username);
	
	/**
	 * @TODO：买入股票
	 * @param username
	 * @param code
	 * @param num
	 * @return 是否买卖成功 
	 */
	public UserTradeSingal buy(String username,String code,int num);
	
	public UserTradeSingal sell(String username,String code,int num);
	
}
