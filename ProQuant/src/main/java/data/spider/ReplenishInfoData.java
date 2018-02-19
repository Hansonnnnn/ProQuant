package data.spider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ReplenishInfoData {

	private JdbcTemplate jdbcTemplate;
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		jdbcTemplate = context.getBean(JdbcTemplate.class);
	}
	
	public void getList() {
		System.out.println("start");
		String sql = "SELECT code,name FROM stock_current_data where code not in(select code from info_data)";
		List<NameAndCode> list = jdbcTemplate.query(sql, new RowMapperObject());
		insert(list);
		System.out.println("finish");
	}
	
	
	public void insert(List<NameAndCode> list){
		String sql="INSERT INTO `proquant`.`info_data`(`code`,`name`) VALUES(?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, list.get(i).getCode());
				ps.setString(2, list.get(i).getName());
			}
			
			@Override
			public int getBatchSize() {
				
				return list.size();
			}
		});
		
	}
	public static void main(String[] args) {
		new ReplenishInfoData().getList();
	}
}

class NameAndCode{
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}

class RowMapperObject implements RowMapper<NameAndCode>{

	@Override
	public NameAndCode mapRow(ResultSet rs, int i) throws SQLException {
		NameAndCode nameAndCode = new NameAndCode();
		nameAndCode.setCode(rs.getString("code"));
		nameAndCode.setName(rs.getString("name"));
		
		return nameAndCode;
	}
	
}