package dbms.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${discount.sql}")
	String discountSql;


	public List<String> getAllDiscountItemsGreterThan(Integer i){

		List<String> results = jdbcTemplate.query(
				discountSql, new Object[]{i},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						/*List<String> itemList = new ArrayList<String>();
						while(rs.next()){
							itemList.add((String)rs.getObject(1));
						}*/
						
						return (String)rs.getObject(1);
					}
				});
		return results;
		
	}

}
