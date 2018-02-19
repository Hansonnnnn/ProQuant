package dataservice;

import PO.user.UserData;

/**
 * @author 凡
 * 用户登录签到接口
 */
public interface UserLogService {

	/**
	 * @TODO：获得用户密码，头像
	 * @param username
	 * @return 如果用户名错误，则返回null
	 */
	public UserData getUserBasicData(String username);
	
	
	/**
	 * @TODO：判断当天该用户是否已签到
	 * @param username
	 * @return
	 */
	public boolean hasSigned(String username);
	
	/**
	 * @TODO：未签到用户签到
	 * @param username
	 * @return int 返回连续签到天数
	 */
	public int signIn(String username);
	
	/**
	 * @TODO：注册
	 * @param userData
	 * @return 失败即为用户名已被注册
	 */
	public boolean signUp(UserData userData);
	
	/**
	 * @TODO：登录
	 * @param username
	 * @param password
	 * @return 登陆成功或失败
	 */
	public boolean logIn(String username,String password);
	
	/**
	 * @TODO：修改用户信息(密码或头像)
	 * @param userData
	 * @return
	 */
	public boolean alterUserInfo(UserData userData);
	
}
