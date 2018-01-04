package top.andnana.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.andnana.entity.Category;
import top.andnana.entity.Label;
import top.andnana.entity.Msg;
import top.andnana.service.LabelService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
@Controller
public class LabelController {
    @Autowired
    private LabelService labelService;
    @RequestMapping(value = "/label", method = RequestMethod.GET)
    @ResponseBody
    public Msg getLabel(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber) {
        System.out.println("pageNumber");
        System.out.println(pageNumber);
        PageHelper.startPage(pageNumber, 10);
        List<Label> labelList = labelService.getLabel();
        PageInfo pageInfo = new PageInfo(labelList, 5);

        System.out.println("员工数据：");

        for (int i = 0; i < labelList.size(); i++) {
            System.out.println(labelList.get(i).getId());
        }

        System.out.println("dfsdfsadf");
        return Msg.success().add("myInfo", pageInfo);
    }
    @RequestMapping("/checkLabelName")
    @ResponseBody
    public Msg checkLabelname(@RequestParam("labelName") String labelName) {
        System.out.println("labelName");
        System.out.println(labelName);
        String labelNameReg = "(^[\\u2E80-\\u9FFFa-zA-Z0-9_-]{2,50}$)";
        boolean available = labelName.matches(labelNameReg);
        if (!available) {
            return Msg.failure().add("otherMsg", "标签名称在2-50位中英文字符（a-zA-Z0-9_-)");
        }

        Label label = labelService.getLabelByName(labelName);
        return label != null ? Msg.failure().add("otherMsg", "标签名称重复") : Msg.success().add("otherMsg", "标签名称可用");
    }

    @RequestMapping(value = "/label", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveLabel(@Valid Label label, BindingResult result) {
        System.out.println("label");
        System.out.println(label);
        try {
            if (result.hasErrors()) {
                List<FieldError> errors = result.getFieldErrors();
                Map<String, Object> errorsHashMap = new HashMap<>();

                for (FieldError fieldError : errors) {
                    errorsHashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return Msg.failure().add("errors", errorsHashMap);
            } else {
                labelService.addLabel(label);
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.failure().add("errors", "exception");
    }

    @RequestMapping(value = "/label/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteByIds(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            String[] ids_String = ids.split("-");
            List<Integer> ids_Integer = new ArrayList<>();
            for (int i = 0; i < ids_String.length; i++) {
                ids_Integer.add(Integer.parseInt(ids_String[i]));
            }
            labelService.deleteByExample(ids_Integer);
        } else {
            Integer id = Integer.parseInt(ids);
            labelService.deleteByPrimaryKey(id);
        }

        return Msg.success();
    }
    @RequestMapping(value = "/labels", method = RequestMethod.GET)
    @ResponseBody
    public Msg selectAll() {
        List<Label> labelList = labelService.selectAll();

        System.out.println("labelList");
        System.out.println(labelList);
        return Msg.success().add("labelList", labelList);
    }

}
