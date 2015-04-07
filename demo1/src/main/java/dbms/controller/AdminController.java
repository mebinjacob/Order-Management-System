package dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.OrderMgmtUtils;
import dbms.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/productList")
	public @ResponseBody List<List<? extends Object>> viewProductList(){
		return OrderMgmtUtils.createRows(adminService.getAllProducts(1));
	}
	
	
	@RequestMapping("/addCategory")
	public String addCategory(){
		return "adminAddCategory";
	}	
	
	@RequestMapping("/saveCategory")
	public String saveCategory(@RequestParam("category") String category, @RequestParam("subCategory") String subCategory){
		adminService.addCategory(category, subCategory);
		return "adminAddCategory";
	}
	
	@RequestMapping("/addUser")
	public String addUser(){
		return "adminAddUser";
	}
	
	@RequestMapping("/saveUser")
	public String saveUser(@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName, @RequestParam("lastName") String lastName ,@RequestParam("contactNumber") Integer contactNumber,@RequestParam("password") String password,@RequestParam("role") String role, @RequestParam("street") String street, @RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("zipCode") Integer zipCode, @RequestParam("email") String email){
		adminService.addUser(firstName, middleName, lastName, contactNumber, password, street, city, state, zipCode, email);
		return "adminAddUser";
	}
	
	@RequestMapping("/addItem")
	public String addItem(){
		return "adminAddItem";
	}
	
	@RequestMapping("/saveItem")
	public String saveItem(@RequestParam("isActive") String isActive, @RequestParam("sellingPrice") Double sellingPrice, @RequestParam("itemName") String itemName ,@RequestParam("description") String description){
		adminService.addItem(isActive, sellingPrice, itemName, description);
		return "adminAddUser";
	}
	

}
