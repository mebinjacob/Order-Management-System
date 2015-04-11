/**
 * 
 */
package dbms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dbms.service.storeService;

/**
 * @author Mebin Jacob
 *
 */
@Controller
@RequestMapping("/storeManager")
public class StoreManagerController {

	@Value("${monthlySalesReport.sql}")
	String monthlySalesReport;
	
	@Autowired
	storeService sS;
	
	
	@RequestMapping("/getStoresList")
	public @ResponseBody List<String> getStoresList(){
		return null;
	}
	
	@RequestMapping("/getSalesReportData")
	public @ResponseBody List<List<Object>> getMonthlySalesReportData(){
		List<List<Object>> monthAndSaleList = new ArrayList<List<Object>>();
		List<Object> mthAndSale = new ArrayList<Object>();
		mthAndSale.add("Month");
		mthAndSale.add("Sale");
		monthAndSaleList.add(mthAndSale);
		monthAndSaleList.addAll(sS.monthlySalesReport());
		return sS.monthlySalesReport();
	}
	
	
	@RequestMapping("/getWeeklySalesReport")
	public @ResponseBody List<List<Object>> getWeeklySalesReport(@RequestParam String monthID){
		return sS.weeklySalesReport(monthID);
	}
	
}
