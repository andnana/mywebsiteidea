package top.andnana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.andnana.entity.Msg;
import top.andnana.entity.User;
import top.andnana.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/19 0019.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateUser(@Valid User user) {

        userService.updateByPrimaryKeySelective(user);

        return Msg.success();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            Map<String, Object> errorsHashMap = new HashMap<>();

            for (FieldError fieldError : errors) {
                errorsHashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.failure().add("errors", errorsHashMap);
        } else {
            userService.saveUser(user);
            return Msg.success();
        }

    }

    @RequestMapping("/checkUsername")
    @ResponseBody
    public Msg checkUsername(@RequestParam("username") String username) {
        String usernameReg = "(^[a-zA-Z0-9_-]{3,16})|(^[\\u2E80-\\u9FFF]{2,5}$)";
        boolean available = username.matches(usernameReg);
        if (!available) {
            return Msg.failure().add("otherMsg", "用户名在3-16位英文字符，或2-5位中文。（a-zA-Z0-9_-)");
        }

        boolean hasUserBoolean = userService.checkUsername(username);
        return hasUserBoolean ? Msg.failure().add("otherMsg", "用户名重复") : Msg.success().add("otherMsg", "用户名可用");
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getUser(@PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        return Msg.success().add("user", user);
    }

    //    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public Msg deleteUserById(@PathVariable("id")Integer id){
//        userService.deleteByPrimaryKey(id);
//        return Msg.success();
//    }
    @RequestMapping(value = "/user/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteUserById(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            String[] ids_String = ids.split("-");
            List<Integer> ids_Integer = new ArrayList<>();
            for (int i = 0; i < ids_String.length; i++) {
                ids_Integer.add(Integer.parseInt(ids_String[i]));
            }
            userService.deleteByExample(ids_Integer);
        } else {
            Integer id = Integer.parseInt(ids);
            userService.deleteByPrimaryKey(id);
        }

        return Msg.success();
    }
}
