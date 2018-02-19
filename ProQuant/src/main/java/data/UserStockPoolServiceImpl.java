package data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.UserStockPoolDao;
import PO.user.UserStockPool;
import dataservice.UserStockPoolService;
@Transactional
@Service("UserStockPoolService")
public class UserStockPoolServiceImpl implements UserStockPoolService {

	@Autowired
	private UserStockPoolDao stockPoolDao;
	@Override
	public List<String> getUserStockPool(String username) {
		
		return stockPoolDao.findByUsername(username);
	}
	@Override
	public boolean addToPool(String username, String stockcode) {
		if (stockPoolDao.hasExist(username, stockcode)) {
			return false;
		}
		
		UserStockPool userStockPool = new UserStockPool(username, stockcode);
		try {
			stockPoolDao.persist(userStockPool);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeFromPool(String username, String stockcode) {
		UserStockPool userStockPool = new UserStockPool(username, stockcode);
		return stockPoolDao.delete(userStockPool);
	}
	@Override
	public boolean modifyPool(List<String> list, String username) {
		
		
		 
		return false;
	}

}
