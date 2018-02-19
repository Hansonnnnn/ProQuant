package bl.helper.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import PO.StockCurrentData;
import PO.user.UserAccount;
import PO.user.UserStockOwned;
import PO.user.UserStrategyRecord;
import PO.user.UserTradeRecord;
import VO.UserVO.AccountPageTotalVO;
import VO.UserVO.AccountVO;
import VO.UserVO.DealRecordsVO;
import VO.UserVO.OwnedStocksVO;
import VO.UserVO.StrategyRecordVO;
import VO.UserVO.UserOptionalStocksListVO;
import dataservice.StockDataService;
import model.StockPlate;
import model.StrategyType;

public  class UserPoToVo {
    public static UserOptionalStocksListVO stockCurrentPOToUserOptionalStocksListVO(StockCurrentData current){
    	UserOptionalStocksListVO vo=new UserOptionalStocksListVO(current.getCode(), current.getName(), current.getTrade()
    			, current.getChangepercent(), current.getVolume());
    	return vo;
    }
    
    public static AccountPageTotalVO threePOToAccountPageTotalVO(UserAccount userAccount,
    		List<UserStockOwned>  userStocks,List<UserTradeRecord> userTradeRecords,StockDataService service){

    	//账户金额等信息
        AccountVO accountVO=new AccountVO(userAccount.getUsername(),userAccount.getAvailablePrincipal()+userAccount.getStoragePrincipal()
        ,userAccount.getAvailablePrincipal(),userAccount.getProfit(),userAccount.getTodayProfit());
        //持仓记录
        ArrayList<OwnedStocksVO> ownedStocksVOs=getOwn(userStocks,service);
    	//成交记录
        ArrayList<DealRecordsVO> dealRecordsVOs=getDeal(userTradeRecords,service);
        AccountPageTotalVO accountPageTotalVO=new AccountPageTotalVO(accountVO, ownedStocksVOs, dealRecordsVOs);
    	return accountPageTotalVO;
    }
    
    private static  ArrayList<OwnedStocksVO> getOwn(List<UserStockOwned>  userStocks,
    		StockDataService service){
    	ArrayList<OwnedStocksVO> ownedStocksVOs=new ArrayList<>();
    	for(int i=0;i<userStocks.size();i++){
    		StockCurrentData po=service.getStockCurrentData(userStocks.get(i).getId().getStockcode());
    		OwnedStocksVO ownedStocksVO=new OwnedStocksVO();
    		ownedStocksVO.setOwnedNum(userStocks.get(i).getStocknum());
    		ownedStocksVO.setStockName(po.getName());
    		ownedStocksVO.setNewestPrice(po.getTrade());
    		ownedStocksVO.setCode(po.getCode());
    		ownedStocksVOs.add(ownedStocksVO);

    	}
        return ownedStocksVOs;
    }
    
    private static ArrayList<DealRecordsVO> getDeal(List<UserTradeRecord> userTradeRecords,StockDataService service){
    	ArrayList<DealRecordsVO> dealRecordsVOs=new ArrayList<>();
    	for(int i=0;i<userTradeRecords.size();i++){
    		DealRecordsVO dealRecordsVO=new DealRecordsVO();
    		
    		dealRecordsVO.setDealNum(userTradeRecords.get(i).getTradenum());
    		
    		SimpleDateFormat time = new SimpleDateFormat("YYYY-MM-dd");
    		String timeString = time.format(userTradeRecords.get(i).getDate());
    		dealRecordsVO.setDealDate(timeString);

    		String name=service.getName(userTradeRecords.get(i).getStockcode());
    		dealRecordsVO.setStockName(name);
    		
    		switch (userTradeRecords.get(i).getTradetype()){
    			case 0:dealRecordsVO.setDealType("买入");break;
    			case 1:dealRecordsVO.setDealType("卖出");break;
    		}
    		
    		dealRecordsVO.setAveragePrice(userTradeRecords.get(i).getTradeprice());
    		dealRecordsVOs.add(dealRecordsVO);
    	}
    	return dealRecordsVOs;
    }
    
    public static StrategyRecordVO UserStrategyPOToStrategyVO(UserStrategyRecord po){
    	StrategyRecordVO strategyRecordVO=new StrategyRecordVO();
    	strategyRecordVO.setStartDate(po.getStartDate().toLocaleString());
    	strategyRecordVO.setEndDate(po.getEndDate().toLocaleString());
    	strategyRecordVO.setMaxHoldStocksNum(po.getMaxHoldStocksNum().toString());
    	strategyRecordVO.setStrategAlgorithmName(StrategyType.getName(po.getStrategyAlgorithm()));
    	strategyRecordVO.setStockPlate(StockPlate.getName(po.getStockPlate()));
    	strategyRecordVO.setStrategyBaseRule(StockPlate.getName(po.getBaseRule()));
    	strategyRecordVO.setHoldDays(Integer.toString(po.getHoldingPeriod()));
    	strategyRecordVO.setPossessDays(Integer.toString(po.getFormativePeriod()));
    	return strategyRecordVO;
    	
    }
}
