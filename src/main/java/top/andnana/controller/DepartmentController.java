package top.andnana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.andnana.entity.Department;
import top.andnana.entity.Msg;
import top.andnana.service.DepartmentService;

import java.util.List;

/**
 * Created by Administrator on 2017/12/19 0019.
 */
@Controller
public class DepartmentController {
   @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> deptsList = departmentService.getDepts();

        return Msg.success().add("depts", deptsList);
    }


}
