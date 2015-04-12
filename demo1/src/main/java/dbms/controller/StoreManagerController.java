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

import dbms.service.LoginService;
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
	storeService store_service;
	
	@RequestMapping("/getStoreItem")
	public @ResponseBody List<List<String>> getStoreItems()
	{
		LoginService loginService = new LoginService();
		//return store_service.selectItem_FromStore(loginService.locationID);
		return store_service.selectItem_FromStore(2);
	}
	
	@RequestMapping("/getStoreInventoryRequests")
	public @ResponseBody List<List<String>> getStore_InventoryRequest()
	{
		LoginService loginService = new LoginService();
		//return store_service.selectInventoryRequest_Store(loginService.locationID);
		return store_service.selectInventoryRequest_Store(2);
	}
	
	@RequestMapping("/getStoreDiscountRequests")
	public @ResponseBody List<List<String>> getStore_DiscountAndOffers()
	{
		LoginService loginService = new LoginService();
		//return store_service.selectDiscountAndOffers(loginService.locationID);
		return store_service.selectDiscountAndOffers(3);
	}
	
	@RequestMapping("/addInventory")
	public @ResponseBody String addInventory(@RequestParam("empId") String empId, @RequestParam("itemId") String itemId, 
											@RequestParam("qty") int qty, @RequestParam("Comments") String comments)
	{
		store_service.addInventoryRequst(empId, itemId, qty, comments);
		return "storeManager";
	}
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
		monthAndSaleList.addAll(store_service.monthlySalesReport());
		return store_service.monthlySalesReport();
	}
	
	
	@RequestMapping("/getWeeklySalesReport")
	public @ResponseBody List<List<Object>> getWeeklySalesReport(@RequestParam String monthID){
		return store_service.weeklySalesReport(monthID);
	}
	
}
