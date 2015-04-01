package dbms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dbms.service.AdminService;

@Controller
public class MyController {
	
	@Autowired 
	AdminService ser;	
	 @RequestMapping("/login")
	    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
	      model.addAttribute("name", name);  
//		  ser.test();
	      return "login";
		  
	    }

}
