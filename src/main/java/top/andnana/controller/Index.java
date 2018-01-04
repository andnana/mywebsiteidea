package top.andnana.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import top.andnana.entity.Msg;
import top.andnana.entity.User;
import top.andnana.service.UserService;

@Controller
public class Index {
    @Autowired
    UserService userService;


    @RequestMapping({"/userlist"})
    public String HelloWorld(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, Model model) {
        PageHelper.startPage(pageNumber, 2);
        List<User> userList = userService.selectAll();
        PageInfo pageInfo = new PageInfo(userList, 5);

        model.addAttribute("message", "Hello World!!!!");

        model.addAttribute("pageInfo", pageInfo);

        System.out.println("dfsdfsadf");

        return "index";


    }


    @RequestMapping({"/index"})
    @ResponseBody
    public Msg HelloWorld(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber) {
        System.out.println("pageNumber");
        System.out.println(pageNumber);
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(pageNumber, 2);
            List<User> userList = userService.selectAll();
            pageInfo = new PageInfo(userList, 5);

            System.out.println("员工数据：");

            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i).getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("dfsdfsadf");
//		Msg msg = new Msg();
//		Msg.success().getMyInfo().put("myInfo", pageInfo);

        return Msg.success().add("myInfo", pageInfo);


    }

    @ResponseBody
    @RequestMapping(value = "/index/{username}", method = RequestMethod.GET)
    public User HelloWorld(@PathVariable("username") String username) {
        System.out.println(username + "username");
        User user = userService.findUserByName(username);
        System.out.println(user + "user");

        return user;

    }


}
