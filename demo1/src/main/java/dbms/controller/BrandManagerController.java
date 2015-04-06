package dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.OrderMgmtUtils;
import dbms.service.BrandManagerService;

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
	

}
