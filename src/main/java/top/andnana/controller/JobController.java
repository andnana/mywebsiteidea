package top.andnana.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.andnana.entity.Job;
import top.andnana.entity.Msg;
import top.andnana.entity.User;
import top.andnana.service.JobService;
import top.andnana.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
@Controller
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/job", method = RequestMethod.GET)
    @ResponseBody
    public Msg HelloWorld(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber) {
        System.out.println("pageNumber");
        System.out.println(pageNumber);
        PageHelper.startPage(pageNumber, 2);
        List<Job> jobList = jobService.getJobs();
        PageInfo pageInfo = new PageInfo(jobList, 5);

        System.out.println("员工数据：");

        for (int i = 0; i < jobList.size(); i++) {
            System.out.println(jobList.get(i).getId());
        }

        System.out.println("dfsdfsadf");
        return Msg.success().add("myInfo", pageInfo);
    }

    @RequestMapping("/checkJobname")
    @ResponseBody
    public Msg checkUsername(@RequestParam("jobname") String jobname) {
        String jobnameReg = "(^[\\u2E80-\\u9FFFa-zA-Z0-9_-]{2,50}$)";
        boolean available = jobname.matches(jobnameReg);
        if (!available) {
            return Msg.failure().add("otherMsg", "工作名称在2-50位中英文字符（a-zA-Z0-9_-)");
        }

        Job job = jobService.getJobByName(jobname);
        return job != null ? Msg.failure().add("otherMsg", "工作名称重复") : Msg.success().add("otherMsg", "工作名称可用");
    }

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveJob(@Valid Job job, BindingResult result) {
        System.out.println("job");
        System.out.println(job);
        try {
            if (result.hasErrors()) {
                List<FieldError> errors = result.getFieldErrors();
                Map<String, Object> errorsHashMap = new HashMap<>();

                for (FieldError fieldError : errors) {
                    errorsHashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return Msg.failure().add("errors", errorsHashMap);
            } else {
                jobService.addJob(job);
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.failure().add("errors", "exception");
    }

    @RequestMapping(value = "/job/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteJobById(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            String[] ids_String = ids.split("-");
            List<Integer> ids_Integer = new ArrayList<>();
            for (int i = 0; i < ids_String.length; i++) {
                ids_Integer.add(Integer.parseInt(ids_String[i]));
            }
            jobService.deleteByExample(ids_Integer);
        } else {
            Integer id = Integer.parseInt(ids);
            jobService.deleteByPrimaryKey(id);
        }

        return Msg.success();
    }
}
