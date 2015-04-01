package dbms.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean login(String username, String password){
		
		String sql = "SELECT COUNT(*) FROM IMGMT_USER where email='" + username + "' and passwd='" + password + "';";
		
		 List<Integer> results = jdbcTemplate.query(
	                "SELECT COUNT(*) FROM IMGMT_USER where email= ? and passwd = ?", new Object[]{username, password},
	                new RowMapper<Integer>() {
	                    @Override
	                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	                        return rs.getRow();
	                    }
	                });
		 
		 if(results.get(0) == 1)
			 return true;
		 
		return false;
	}
}
