package data;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import DAO.dao.UserAccountDao;
import DAO.dao.UserDataDao;
import PO.user.UserAccount;
import PO.user.UserData;
import dataservice.UserLogService;
import utility.DateHelper;
@Service("UserLogService")
@Transactional
public class UserLogServiceImpl implements UserLogService {

	@Autowired
	private UserDataDao userDataDao;
	@Autowired
	private UserAccountDao userAccountDao;
	//md5盐值字符串
	private static final String slat = "fdsgewr6t87349n#^R(*WFJ"; 
	@Override
	public UserData getUserBasicData(String username) {
		UserData userData = userDataDao.findById(username);
		
		return userData;
	}

	@Override
	public boolean hasSigned(String username) {
		UserAccount userAccount = userAccountDao.findById(username);
		Date date = userAccount.getSigndate();
		
		return DateHelper.isToday(date);
	}

	@Override
	public int signIn(String username) {
		UserAccount userAccount = userAccountDao.findById(username);
		Date today = new Date();
		Date date = userAccount.getSigndate();
		int num = userAccount.getConsecutiveDaysNum();
		if (DateHelper.isYesterday(date)) {
			num++;
			if (num%7==0) {
				userAccount.setAvailablePrincipal(userAccount.getAvailablePrincipal()+5000);
				userAccount.setHistoryPrincipal(userAccount.getHistoryPrincipal()+5000);
			}else {
				userAccount.setAvailablePrincipal(userAccount.getAvailablePrincipal()+1000);
				userAccount.setHistoryPrincipal(userAccount.getHistoryPrincipal()+1000);
			}
		}else {
			num = 1;
			userAccount.setAvailablePrincipal(userAccount.getAvailablePrincipal()+1000);
			userAccount.setHistoryPrincipal(userAccount.getHistoryPrincipal()+1000);
	
		}
		userAccount.setConsecutiveDaysNum(num);
		userAccount.setSigndate(today);
		userAccountDao.merge(userAccount);
		return num;
	}

	private String getMD5(String str){
		String base = str+"/"+slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		
		return md5;		
	}

	@Override
	public boolean signUp(UserData userData) {
		List<String> allUser = userDataDao.getAllUserName();
		if (allUser.indexOf(userData.getUsername())>=0) {
			return false;
		}
		
		
		userData.setPassword(getMD5(userData.getPassword()));
		try {
			userDataDao.persist(userData);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean logIn(String username, String password) {
		if (getUserBasicData(username).getPassword().equals(getMD5(password))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean alterUserInfo(UserData userData) {
		try {
			userDataDao.merge(userData);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
