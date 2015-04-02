package dbms.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dbms.dao.Test;
import dbms.service.AdminService;
import dbms.service.LoginService;

@Controller
public class MyController {

	@Autowired 
	AdminService ser;	
	
	@Autowired
	LoginService logService;

	@RequestMapping("/login")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);  
		//		  ser.test();
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
	
	//for strint and int
	@RequestMapping("/getData")
	public @ResponseBody List<List<Object>> getSampleData(){
		Test t = new Test();
		t.setPoint(1);
		List<Test> tList = new ArrayList<Test>();
		Test t1 = new Test();
		t.setName("Mushroom");
		t.setPoint(1);
		tList.add(t1);
		
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
		
		return arrayList;//"[ ['Mushrooms', 3], ['Onions', 1], ['Olives', 1], ['Zucchini', 1], ['Pepperoni', 2]]";
	}
}
