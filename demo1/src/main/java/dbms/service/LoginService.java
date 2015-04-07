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
public class LoginService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${login.sql}")
	String loginSql;

	public String login(String username, String password){

		//"SELECT COUNT(*) FROM IMGMT_USER where email= ? and passwd = ?"		
		List<String> results = jdbcTemplate.query(
				loginSql, new Object[]{username, password},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return  rs.getObject(1).toString();
					}
				});
		if(results.size() > 0)
			return results.get(0) ;
		else return null;

	}
}
