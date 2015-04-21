package dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.OrderMgmtUtils;
import dbms.service.BrandManagerService;
import dbms.service.LoginService;

@Controller
@RequestMapping("/brandManager")
public class BrandManagerController {
	
	@Autowired
	BrandManagerService brandManagerService;
	
	@RequestMapping("/productList")
	public @ResponseBody List<List<? extends Object>> viewProductList(){
		return OrderMgmtUtils.createRows(brandManagerService.getAllProducts(1));
	}
	
	
	@RequestMapping("/viewReports")
	public String viewReport(){
		return "brandManagerviewReports";
	}
	
	@RequestMapping("/logout")
	public String logout()
	{
		LoginService.roleName = null;
		LoginService.userID = null;
		LoginService.userName = null;
		return "login";
	}
	
	@RequestMapping("/getStockReport")
	public @ResponseBody List<List<Object>> getStockReport(@RequestParam("brandName") String brandName){
		return brandManagerService.getStockReport(brandName);
	}
	
	@RequestMapping("/addCategory")
	public String addCategory(){
		return "brandManagerAddCategory";
	}
	
	@RequestMapping("/saveCategory")
	public String saveCategory(@RequestParam("category") String category, @RequestParam("subCategory") String subCategory){
		brandManagerService.addCategory(category, subCategory);
		return "brandManagerAddCategory";
	}
	
	
	@RequestMapping("/getInventoryMgmtPieChartData")
	public @ResponseBody List<List<? extends Object>> getPieChartData(){
		return OrderMgmtUtils.createRows(brandManagerService.getProductsByCategory(1));
		
	}
	
	
	@RequestMapping("/productListBasedOnReview")
	public @ResponseBody List<List<? extends Object>> viewProductListBasedOnReview(){
		return OrderMgmtUtils.createRows(brandManagerService.getAllProducts(1));
	}
	
	@RequestMapping("/getMonthlySalesReport")
	public @ResponseBody List<List<Object>> getMonthlySalesReport(@RequestParam String month, @RequestParam String year) {
		return brandManagerService.getMonthlySalesReport(month, year);
	}

	@RequestMapping("/getWeeklySalesReport")
	public @ResponseBody List<List<Object>> getWeeklySalesReport(@RequestParam String year, @RequestParam String month, @RequestParam String week) {
		return brandManagerService.getWeeklySalesReport(year, month, week);
	}

	@RequestMapping("/getRegionSaleReport")
	public @ResponseBody List<List<Object>> getRegionSalesReport(@RequestParam String brandName, @RequestParam String year, @RequestParam String month) {
		return brandManagerService.getRegionSaleReport(brandName, year, month);
	}
	

}
