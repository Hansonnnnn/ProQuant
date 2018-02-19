package data.spider.update;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import PO.StockBasicIndex;
import PO.StockCurrentData;

@Component("CSUU")
public class StockUpdateUtils {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void updateByHibernate(ArrayList<StockCurrentData> result) {
		Session session= sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		for (StockCurrentData stockCurrentData : result) {
			stockCurrentData.setDate(new Date());
			session.merge(stockCurrentData);
		}
		transaction.commit();
		session.close();

	}
	@Transactional("JDBC")
	public void updateByJDBC(ArrayList<StockCurrentData> result) {
		String updateSql = "UPDATE `stock_current_data` set `changepercent`=?,"
				+ "`trade`=?,`open`=?,`high`=?,`low`=?,`settlement`=?,`volume`=?,"
				+ "`turnoverratio`=?,`amount`=?,`per`=?,`pb`=?,`mktcap`=?,`nmc`=?"
				+ " where code =?";
		/*SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(result.toArray());
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.batchUpdate(insertSql, batch);*/

		jdbcTemplate.batchUpdate(updateSql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				ps.setDouble(1, result.get(i).getChangepercent());
				ps.setDouble(2, result.get(i).getTrade());
				ps.setDouble(3, result.get(i).getOpen());
				ps.setDouble(4, result.get(i).getHigh());
				ps.setDouble(5, result.get(i).getLow());
				ps.setDouble(6, result.get(i).getSettlement());
				ps.setLong(7, result.get(i).getVolume());
				ps.setDouble(8, result.get(i).getTurnoverratio());
				ps.setLong(9, result.get(i).getAmount());
				ps.setDouble(10, result.get(i).getPer());
				ps.setDouble(11, result.get(i).getPb());
				ps.setDouble(12, result.get(i).getMktcap());
				ps.setDouble(13, result.get(i).getNmc());
				ps.setString(14, result.get(i).getCode());
				
			}
			
			@Override
			public int getBatchSize() {
				return result.size();
			}
		});
		
	}
	
	@Transactional("JDBC")
	public void updateStockBasicIndex(ArrayList<StockBasicIndex> result) {
		String updateSql = "UPDATE `proquant`.`stock_basic_index` SET "
				+ "`week_change_ratio` = ?,`month_change_ratio` = ?,`season_change_ratio` = ?,"
				+ "`halfayear_change_ratio` = ?,`year_change_ratio` = ?,`pe` = ?,`pb` = ?,"
				+ "`pcf` = ?,`ps` = ?,`per_share_earnings` = ?,`net_profit_margin_on_sales` =?,"
				+ "`roa` = ?,`debt_asset_ratio` = ?,`current_ratio` = ?,`main_business_income` = ?,"
				+ "`net_profit` = ?,`total_assets` = ?,`shareholders_equity` = ? WHERE `code` = ?";

		/*SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(result.toArray());
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.batchUpdate(insertSql, batch);*/

		jdbcTemplate.batchUpdate(updateSql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				ps.setDouble(1, result.get(i).getWeekChangeRatio());
				ps.setDouble(2, result.get(i).getMonthChangeRatio());
				ps.setDouble(3, result.get(i).getSeasonChangeRatio());
				ps.setDouble(4, result.get(i).getHalfayearChangeRatio());
				ps.setDouble(5, result.get(i).getYearChangeRatio());
				ps.setDouble(6, result.get(i).getPe());
				ps.setDouble(7, result.get(i).getPb());
				ps.setDouble(8, result.get(i).getPcf());
				ps.setDouble(9, result.get(i).getPs());
				ps.setDouble(10, result.get(i).getPerShareEarnings());
				ps.setDouble(11, result.get(i).getNetProfitMarginOnSales());
				ps.setDouble(12, result.get(i).getRoa());
				ps.setDouble(13, result.get(i).getDebtAssetRatio());
				ps.setDouble(14, result.get(i).getCurrentRatio());
				ps.setDouble(15, result.get(i).getMainBusinessIncome());
				ps.setDouble(16, result.get(i).getNetProfit());
				ps.setDouble(17, result.get(i).getTotalAssets());
				ps.setDouble(18, result.get(i).getShareholdersEquity());
				
				ps.setString(19, result.get(i).getCode());
				
			}
			
			@Override
			public int getBatchSize() {
				return result.size();
			}
		});
		
	}
}
