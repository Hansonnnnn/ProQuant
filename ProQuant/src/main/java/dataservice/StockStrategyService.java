package dataservice;

import java.util.List;

import PO.user.UserStrategyRecord;

public interface StockStrategyService {
   
  public void persist(UserStrategyRecord record);
  
  public List<UserStrategyRecord> getRecords(String username);
}
