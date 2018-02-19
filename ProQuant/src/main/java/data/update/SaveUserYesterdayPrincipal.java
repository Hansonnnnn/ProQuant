package data.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.UserAccountDao;
import DAO.dao.UserDataDao;
import PO.user.UserAccount;
import dataservice.UserTradeService;


@Component()
public class SaveUserYesterdayPrincipal{

	@Autowired
	UserDataDao dataDao;
	@Autowired
	UserTradeService tradeService;
	@Autowired
	UserAccountDao accountDao;
	
	@Transactional
	public void save() {
	
		List<String> allUser = dataDao.getAllUserName();
		UserAccount userAccount;
		for (String string : allUser) {
			userAccount = tradeService.getUserAccount(string);
			userAccount.setYesterdayPrincipal(userAccount.getProfit()+userAccount.getHistoryPrincipal());
			accountDao.merge(userAccount);
		}
		
/*		Session session = sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		transaction.commit();
		session.close();
*/	}

}
