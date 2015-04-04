package dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.OrderMgmtUtils;
import dbms.service.InventoryManagerService;

@Controller
@RequestMapping("/inventoryManager")
public class InventoryManagerController {
	
	@Autowired
	InventoryManagerService inventoryManagerService;
	
	@RequestMapping("/productList")
	public @ResponseBody List<List<? extends Object>> viewProductList(){
		return OrderMgmtUtils.createRows(inventoryManagerService.getAllProducts(1));
	}
	
	
	@RequestMapping("/viewReports")
	public String viewReport(){
		return "viewReports";
	}
	
	
	@RequestMapping("/getInventoryMgmtPieChartData")
	public @ResponseBody List<List<? extends Object>> getPieChartData(){
		return OrderMgmtUtils.createRows(inventoryManagerService.getProductsByCategory(1));
		
	}

}
