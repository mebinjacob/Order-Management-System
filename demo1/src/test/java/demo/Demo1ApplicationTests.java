package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dbms.Demo1Application;
import dbms.service.storeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Demo1Application.class)
@WebAppConfiguration
public class Demo1ApplicationTests {

	
	@Autowired
	storeService ss;
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test 
	public void storeTest(){
//		ss.monthlySalesReport(1234);	
	}
}
