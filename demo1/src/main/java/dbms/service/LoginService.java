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
	
	@Value("${count.sql}")
	String countSql;
	
	public boolean login(String username, String password){
		
//"SELECT COUNT(*) FROM IMGMT_USER where email= ? and passwd = ?"		
		 List<Integer> results = jdbcTemplate.query(
	                countSql, new Object[]{username, password},
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
