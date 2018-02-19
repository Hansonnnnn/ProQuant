package data;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.StockCurrentDataDao;
import DAO.dao.UserAccountDao;
import DAO.dao.UserStockOwnedDao;
import DAO.dao.UserTradeRecordDao;
import PO.user.UserAccount;
import PO.user.UserStockOwned;
import PO.user.UserStockOwnedId;
import PO.user.UserTradeRecord;
import dataservice.UserTradeService;
import model.UserTradeSingal;

/**
·代码概述：
该类的基本功能：该类为整个项目中的一个工具类，将多个与用户交易相关的多个功能封装在该类当中

·实际代码行数（不包含注释，注释都是之后加的）：120行

·优点：
1.该类每个子程序都实现了较好的功能内聚性，对外提供单一的功能
2.该类封装性较高，所有数据成员均设置为private，不对外开放引用，均采用get()方法获得其使用权
3.因为采用分层的架构模式，使用接口分离了抽象与具体实现，提高了子程序的可扩展性和可维护性
4.使用枚举类来限定状态的集合：成功UserTradeSingal.Success、失败UserTradeSingal.error、余额不足UserTradeSingal.Insufficient，避免使用字符常量来表示，提高了程序的后期可维护性

·缺点：
1.类职责不唯一：负责对外提供获取用户账户、获取用户交易记录、获取用户持有股票数据等多个功能
2.数据成员变量和行为方法均无注释加以解释，再次理解代码含义时难度较大
3.多次使用类似
String code = userStockOwned.getId().getStockcode();
double price = currentDataDao.queryByHql(code).getTrade()*num;
4.子程序的名字并没有完全描述它所做的所有事情，如UserAccount getUserAccount(String username)方法，不仅对外提供获取用户账户数据操控类的功能，内部还承载了设置用户多项数据的职责，这些职责应该独立为一个单独的子程序
5.变量命名不清晰，无法准确描述该变量所承载的含义

·改进意见：
1.将该类的子程序做进一步的抽象，将获取数据的子程序封装在一个类当中，实际交易行为的子程序封装在另一个子程序当中
即getUserAccount(String username)、getUserStockOwned(String username)、getUserTradeRecord(String username)封装在一个类当中，buy(String username, String code, int num)、sell(String username, String code, int num)封装在一个类当中，提高各自类的功能内聚性
2.更改变量名称，使其对代码阅读者更加友好，如storagePrincipal表示已用于购买的金额，可更改为amountPurchased
3.为类的数据成员变量和方法添加必要的解释性说明
4.避免使用类似String code = userStockOwned.getId().getStockcode();这样的方法，使用中间变量进行承接，减少各程序之间的耦合性
5.编写代码之前的思考和设计工作做得不够，应该在每次编写代码前进行必要且有效的设计工作，进行更好的类设计
*/

@Transactional
@Service("UserTradeService")
public class UserTradeServiceImpl implements UserTradeService{
	//用户账户的数据操控类--后加
	@Autowired
	private UserAccountDao accountDao;
	//用户持有股票相关数据的数据操控类--后加
	@Autowired
	private UserStockOwnedDao stockOwnedDao;
	//用户交易记录的数据操控类--后加
	@Autowired
	private UserTradeRecordDao tradeRecordDao;
	//用户当前持有股票的数据操控类--后加--该类职责定义不明确，导致后期再次理解代码时有难度
	@Autowired
	private StockCurrentDataDao currentDataDao;

	//子程序的名字并没有完全描述它所做的所有事情
	@Override
	public UserAccount getUserAccount(String username) {
		List<UserStockOwned> userStockOwneds = getUserStockOwned(username);
		UserAccount userAccount = accountDao.findById(username);
		double storagePrincipal = 0;
		for (UserStockOwned userStockOwned : userStockOwneds) {
			int num = userStockOwned.getStocknum();
			String code = userStockOwned.getId().getStockcode();
			double price = currentDataDao.queryByHql(code).getTrade()*num;
			storagePrincipal+=price;
		}
		userAccount.setStoragePrincipal(storagePrincipal);
		userAccount.setProfit(storagePrincipal+userAccount.getAvailablePrincipal()-userAccount.getHistoryPrincipal());
		userAccount.setTodayProfit(storagePrincipal+userAccount.getAvailablePrincipal()-userAccount.getYesterdayPrincipal());
		
		accountDao.merge(userAccount);

		return userAccount;
	}

	/**
	*该方法实现功能：获取用户持有股票的集合
	*@param：String username 用户的用户名
	*/
	@Override
	public List<UserStockOwned> getUserStockOwned(String username) {
		
		return stockOwnedDao.getUserStockOwned(username);
	}

	/**
	*该方法实现功能：获取用户交易记录的集合
	*@param：String username 用户的用户名
	*/
	@Override
	public List<UserTradeRecord> getUserTradeRecord(String username) {
		
		return tradeRecordDao.getUserTradeRecord(username);
	}
	
	/**
	*该方法实现功能：对界面提供购买某只股票功能的方法
	*@param：String username 用户的用户名
	*@param：String code 股票的代码
	*@param：int num 购买股票的数量
	*/
	@Override
	public UserTradeSingal buy(String username, String code, int num) {
		UserAccount userAccount = accountDao.findById(username);
		double price = currentDataDao.queryByHql(code).getTrade();
		double turnover = price*num;
		if (userAccount.getAvailablePrincipal()<turnover) {
			return UserTradeSingal.Insufficient;
		}
		UserStockOwned userStockOwned = stockOwnedDao.findById(new UserStockOwnedId(username, code));
		if (userStockOwned!=null) {
			userStockOwned.setStocknum(userStockOwned.getStocknum()+num);
			
		}
		else{
			userStockOwned = new UserStockOwned(new UserStockOwnedId(username, code),num);
			
		}
		userAccount.setAvailablePrincipal(userAccount.getAvailablePrincipal()-turnover);
		UserTradeRecord userTradeRecord = new UserTradeRecord(0, username, code, num, (byte) 0, price, new Date());
		
		try {
			stockOwnedDao.merge(userStockOwned);
			accountDao.merge(userAccount);
			tradeRecordDao.persist(userTradeRecord);
			 
		} catch (Exception e) {
			// TODO: handle exception
			return UserTradeSingal.error;
		}
		
		return UserTradeSingal.Success;
	}

	/**
	*该方法实现功能：对界面提供卖出某只股票功能的方法
	*@param：String username 用户的用户名
	*@param：String code 股票的代码
	*@param：int num 卖出股票的数量
	*/
	@Override
	public UserTradeSingal sell(String username, String code, int num) {
		UserAccount userAccount = accountDao.findById(username);
		double price = currentDataDao.queryByHql(code).getTrade();
		double turnover = price*num;
		UserStockOwned userStockOwned = stockOwnedDao.findById(new UserStockOwnedId(username, code));
		int stockNum = userStockOwned.getStocknum();
		if (stockNum<num) {
			return UserTradeSingal.Insufficient;
		}
		else if (stockNum==num) {
			stockOwnedDao.delete(userStockOwned);
		}else {
			userStockOwned.setStocknum(stockNum-num);
			stockOwnedDao.merge(userStockOwned);
		}
		
		userAccount.setAvailablePrincipal(userAccount.getAvailablePrincipal()+turnover);
		UserTradeRecord userTradeRecord = new UserTradeRecord(0, username, code, num, (byte) 1, price, new Date());
		
		try {
			accountDao.merge(userAccount);
			tradeRecordDao.persist(userTradeRecord);
			 
		} catch (Exception e) {
			// TODO: handle exception
			return UserTradeSingal.error;
		}
		
		return UserTradeSingal.Success;
	}

	

}
