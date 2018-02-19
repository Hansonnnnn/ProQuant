package dataservice;

import java.util.List;

public interface UserStockPoolService {

	/**
	 * @TODO：获得用户自选股列表
	 * @param username
	 * @return stockcode的list
	 */
	public List<String> getUserStockPool(String username);
	
	/**
	 * @TODO：
	 * @param username
	 * @param stockcode
	 * @return  false为添加失败，当前股票已存在股票池中
	 */
	public boolean addToPool(String username,String stockcode);
	
	public boolean removeFromPool(String username,String stockcode);
	/**
	 * 直接把所有的股票替换成现在list中的股票名
	 * @param list
	 * @return
	 */
	public boolean modifyPool(List<String> list,String username);
}
