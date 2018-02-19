package data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.UserStrategyRecordDao;
import PO.user.UserStrategyRecord;
import dataservice.StockStrategyService;
@Transactional
@Service("StockStrategyService")
public class StockStrategyServiceImpl implements StockStrategyService {

	@Autowired
	UserStrategyRecordDao recordDao;
	@Override
	public void persist(UserStrategyRecord record) {
		recordDao.persist(record);
	}

	@Override
	public List<UserStrategyRecord> getRecords(String username) {
		
		return recordDao.findByUsername(username);
	}
	
	
}
