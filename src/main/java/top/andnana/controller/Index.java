package top.andnana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.andnana.entity.User;
import top.andnana.service.UserService;

@Controller
public class Index {
	@Autowired
	UserService userService;
 
//	    public String HelloWorld(Model model){
//	 public String HelloWorld(){
     @RequestMapping({"/", "/index"}) 
	 public String HelloWorld(Model model){
		   List<User> userList = userService.selectAll();
	        model.addAttribute("message","Hello World!!!!"); 
		   model.addAttribute("userList", userList);
		   System.out.println(userList);
	        System.out.println("dfsdfsadf");
		   
	        return "index";
		   
//		   return userList;
	    }  
	 @ResponseBody
	 @RequestMapping(value="/index/{username}", method=RequestMethod.GET)
	 public User HelloWorld(@PathVariable("username") String username){
		 System.out.println(username + "username");
		 User user = userService.findUserByName(username);
		 System.out.println(user+"user");
		 return user;
	 }
}
