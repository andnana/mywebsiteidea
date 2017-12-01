package top.andnana;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
	   @RequestMapping({"/","/index"})
	    public String HelloWorld(Model model){  
	        model.addAttribute("message","Hello World!!!");  
	        System.out.println("dfsdfsadf");
	        return "index";  
	    }  
}
