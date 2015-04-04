package dbms.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dbms.service.AdminService;
import dbms.service.DiscountService;
import dbms.service.LoginService;

@Controller
public class MyController {

	@Autowired 
	AdminService ser;	

	@Autowired
	LoginService logService;

	@Autowired
	DiscountService discountService;

	@RequestMapping("/login")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);  
		return "login";
	}

	@RequestMapping("/loggedin")
	public String loggedIn(@RequestParam(value="username") String username, @RequestParam(value="password")String password){

		if(logService.login(username, password)){
			return "index";
		}
		return "login";
	}

	@RequestMapping("/displayGraph")
	public String displayGraph(){
		return "graph";
	}

	//for string and int
	@RequestMapping("/getData")
	public @ResponseBody List<List<Object>> getSampleData(){

		List<List<Object>> arrayList = new ArrayList<List<Object>>(); 
		List<Object> mushroom = new ArrayList<Object>();
		mushroom.add("Mushroom");
		mushroom.add(1);

		List<Object> onions = new ArrayList<Object>();
		onions.add("onions");
		onions.add(3);

		List<Object> olives = new ArrayList<Object>();
		olives.add("Olives");
		olives.add(4);

		List<Object> Zucchini = new ArrayList<Object>();
		Zucchini.add("Zucchini");
		Zucchini.add(4);


		List<Object> Pepperoni = new ArrayList<Object>();
		Pepperoni.add("Pepperoni");
		Pepperoni.add(4);

		arrayList.add(mushroom);
		arrayList.add(onions);
		arrayList.add(olives);
		arrayList.add(Zucchini);
		arrayList.add(Pepperoni);

		return arrayList;
	}


	@RequestMapping("/getPieChartData")
	public @ResponseBody List<List<Object>> getPieChartSampleData(){

		List<List<Object>> arrayList = new ArrayList<List<Object>>(); 

		List<Object> onions = new ArrayList<Object>();
		onions.add("Age");
		onions.add("Weight");

		List<Object> olives = new ArrayList<Object>();
		olives.add(8);
		olives.add(12);

		List<Object> Zucchini = new ArrayList<Object>();
		Zucchini.add(4);
		Zucchini.add(5.5);


		List<Object> Pepperoni = new ArrayList<Object>();
		Pepperoni.add(11);
		Pepperoni.add(14);

		List<Object> ageAndWeight = new ArrayList<Object>();
		ageAndWeight.add(4);
		ageAndWeight.add(5);

		arrayList.add(onions);
		arrayList.add(olives);
		arrayList.add(Zucchini);
		arrayList.add(Pepperoni);
		arrayList.add(ageAndWeight);

		return arrayList;
	}

	@RequestMapping("/getTableData")
	public @ResponseBody List<Object> getTableData(){
		List<Object> tableData = new ArrayList<Object>();
		tableData.add("1");
		tableData.add("2");
		return tableData;
	}

	@RequestMapping("/getDiscountedItem")
	public @ResponseBody List<List<String>> getAllDiscountItemsGreterThan(){
		return createRows(discountService.getAllDiscountItemsGreterThan(0));
	}

	@RequestMapping("/inventoryManager")
	public  String getInventoryManagerData(){
		return "inventoryMgmt";
	}

	public List<List<String>> createRows(List<String> string){
		List<List<String>> listOfString = new ArrayList<List<String>>();
		for(String s : string){
			List<String> l = new ArrayList<String>();
			l.add(s);
			listOfString.add(l);
		}
		return listOfString;
	}
}
