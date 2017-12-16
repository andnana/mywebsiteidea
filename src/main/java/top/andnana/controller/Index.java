package top.andnana.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import top.andnana.entity.User;
import top.andnana.service.UserService;

@Controller
public class Index {
	@Autowired
	UserService userService;
 
//	    public String HelloWorld(Model model){
//	 public String HelloWorld(){
     @RequestMapping({"/", "/index"}) 
	 public String HelloWorld(@RequestParam(value="pageNumber", defaultValue = "1") Integer pageNumber, Model model){
		 PageHelper.startPage(pageNumber, 2);
		   List<User> userList = userService.selectAll();
		 PageInfo pageInfo = new PageInfo(userList, 5);

	        model.addAttribute("message","Hello World!!!!"); 
//		   model.addAttribute("userList", userList);
		 model.addAttribute("pageInfo", pageInfo);
//		   System.out.println(userList);
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
