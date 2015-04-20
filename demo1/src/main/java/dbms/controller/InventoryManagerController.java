package dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.OrderMgmtUtils;
import dbms.dao.regionSale;
import dbms.service.InventoryManagerService;

@Controller
@RequestMapping("/inventoryManager")
public class InventoryManagerController {

	@Autowired
	InventoryManagerService inventoryManagerService;

	@RequestMapping("/productList")
	public @ResponseBody List<List<? extends Object>> viewProductList() {
		return OrderMgmtUtils.createRows(inventoryManagerService
				.getAllProducts(1));
	}

	@RequestMapping("/viewordersAndPayment")
	public String viewOrdersAndPayment() {
		return "ivmgmtPaymentAndOrder";
	}

	@RequestMapping("/saveOrder")
	public String Orders(@RequestParam("itemid") String itemid,
			@RequestParam("quantity") String quantity,
			@RequestParam("comment") String comment,
			@RequestParam("price") String price) {
		
		inventoryManagerService.saveOrder(itemid, quantity, comment, price);
		return "ivmgmtPaymentAndOrder";
	}

	@RequestMapping("/viewReports")
	public String viewReport() {
		return "invmgmtviewReports";
	}

	@RequestMapping("/getInventoryMgmtPieChartData")
	public @ResponseBody List<List<? extends Object>> getPieChartData() {
		return OrderMgmtUtils.createRows(inventoryManagerService
				.getProductsByCategory(1));

	}

	@RequestMapping("/productListBasedOnReview")
	public @ResponseBody List<List<? extends Object>> viewProductListBasedOnReview() {
		return OrderMgmtUtils.createRows(inventoryManagerService
				.getAllProducts(1));
	}

	@RequestMapping("/getMonthlySalesReport")
	public @ResponseBody List<List<Object>> getMonthlySalesReport(@RequestParam String month, @RequestParam String year) {
		return inventoryManagerService.getMonthlySalesReport(month, year);
	}

	@RequestMapping("/getWeeklySalesReport")
	public @ResponseBody List<List<Object>> getWeeklySalesReport(@RequestParam String year, @RequestParam String month, @RequestParam String week) {
		return inventoryManagerService.getWeeklySalesReport(year, month, week);
	}
	
	@RequestMapping("/getRegionSalesReport")
	public @ResponseBody List<regionSale> getRegionSalesReport(@RequestParam String year, @RequestParam String month) {
		return inventoryManagerService.getRegionWiseProducts(1);
	}
	
	
	/*@RequestMapping("/ordersAndPayment")
	public @ResponseBody List<List<? extends Object>> ordersAndPayment() {
		return OrderMgmtUtils.createRows(inventoryManagerService
				.getOrdersAndPayment(1));
	}*/

}
