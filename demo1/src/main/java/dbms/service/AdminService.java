package dbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void test(){
        jdbcTemplate.execute("create table customers(" +
                " first_name varchar(255), last_name varchar(255))");

        String[] fullNames = new String[]{"John Woo", "Jeff Dean", "Josh Bloch", "Josh Long"};
        for (String fullname : fullNames) {
            String[] name = fullname.split(" ");
            System.out.printf("Inserting customer record for %s %s\n", name[0], name[1]);
            jdbcTemplate.update(
                    "INSERT INTO customers(first_name,last_name) values(?,?)",
                    name[0], name[1]);
        }

    
	}
}
