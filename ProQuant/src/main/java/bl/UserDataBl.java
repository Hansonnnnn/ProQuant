package bl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PO.StockCurrentData;
import PO.user.UserAccount;
import PO.user.UserStockOwned;
import PO.user.UserStrategyRecord;
import PO.user.UserTradeRecord;
import VO.UserVO.AccountPageTotalVO;
import VO.UserVO.StrategyRecordVO;
import VO.UserVO.UserOptionalStocksListVO;
import bl.helper.User.UserPoToVo;
import blservice.userDataBlService.UserDataService;
import dataservice.StockDataService;
import dataservice.StockStrategyService;
import dataservice.UserStockPoolService;
import dataservice.UserTradeService;
import model.ResultMessage;

@Service("UserDataService")
public class UserDataBl implements UserDataService{

	@Autowired
	UserStockPoolService userStockPoolservice;
	@Autowired
	UserTradeService userTradeService;
	@Autowired
	StockDataService stockDataService;
	@Autowired
	StockStrategyService stockStrategyService;
	@Override
	public AccountPageTotalVO getUserAccountData(String userName) {
		UserAccount userAccount=userTradeService.getUserAccount(userName);
		List<UserStockOwned>  userStocks=userTradeService.getUserStockOwned(userName);
		List<UserTradeRecord> userTradeRecords=userTradeService.getUserTradeRecord(userName);
		
		return UserPoToVo.threePOToAccountPageTotalVO(userAccount, userStocks, userTradeRecords, stockDataService);
	}

	@Override
	public ArrayList<UserOptionalStocksListVO> getUserOptionalStocks(String userName) {
		List<String> stockLists=userStockPoolservice.getUserStockPool(userName);
		ArrayList<UserOptionalStocksListVO> userOptionalStocksListVOs=new ArrayList<>();
		for(int i=0;i<stockLists.size();i++){
			StockCurrentData stockCurrentData=stockDataService.getStockCurrentData(stockLists.get(i));
			UserOptionalStocksListVO vo=UserPoToVo.stockCurrentPOToUserOptionalStocksListVO(stockCurrentData);
		    userOptionalStocksListVOs.add(vo);
		}
		return userOptionalStocksListVOs;
	}

	@Override
	public ResultMessage deleteUserOptionalStocks(ArrayList<String> newOptionalStocksList,String userName) {
		for(int i=0;i<newOptionalStocksList.size();i++){
			if(!userStockPoolservice.removeFromPool(userName, newOptionalStocksList.get(i))){
				return ResultMessage.failed;
			}
		}
			return ResultMessage.success;
	}

	@Override
	public ArrayList<StrategyRecordVO> getStrategyRecordVO(String userName) {
		// TODO Auto-generated method stub
		List<UserStrategyRecord> userList=stockStrategyService.getRecords(userName);
		ArrayList<StrategyRecordVO> strategyList=new ArrayList<>();
		for(int i=0;i<userList.size();i++){
			StrategyRecordVO strategyRecordVO=UserPoToVo.UserStrategyPOToStrategyVO(userList.get(i));
			strategyList.add(strategyRecordVO);
		}
		return strategyList;
	}
	
	@Override
	public ResultMessage addUserOptionalStocks(ArrayList<String> stockCodes,String userName) {
		for(int i=0;i<stockCodes.size();i++){
			if(!userStockPoolservice.addToPool(userName, stockCodes.get(i))){
				return ResultMessage.failed;
			}
		}
		return ResultMessage.success;
	}

}
