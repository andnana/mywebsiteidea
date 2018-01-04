package top.andnana.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.andnana.entity.Category;
import top.andnana.entity.Msg;
import top.andnana.service.CategoryService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public Msg HelloWorld(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber) {
        System.out.println("pageNumber");
        System.out.println(pageNumber);
        PageHelper.startPage(pageNumber, 10);
        List<Category> categoryList = categoryService.getCategory();
        PageInfo pageInfo = new PageInfo(categoryList, 5);

        System.out.println("员工数据：");

        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(categoryList.get(i).getId());
        }

        System.out.println("dfsdfsadf");
        return Msg.success().add("myInfo", pageInfo);
    }

    @RequestMapping("/checkCategoryName")
    @ResponseBody
    public Msg checkCategoryname(@RequestParam("categoryName") String categoryName) {
        System.out.println("categoryName");
        System.out.println(categoryName);
        String categoryNameReg = "(^[\\u2E80-\\u9FFFa-zA-Z0-9_-]{2,50}$)";
        boolean available = categoryName.matches(categoryNameReg);
        if (!available) {
            return Msg.failure().add("otherMsg", "分类名称在2-50位中英文字符（a-zA-Z0-9_-)");
        }

        Category category = categoryService.getCategoryByName(categoryName);
        return category != null ? Msg.failure().add("otherMsg", "分类名称重复") : Msg.success().add("otherMsg", "分类名称可用");
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveJob(@Valid Category category, BindingResult result) {
        System.out.println("category");
        System.out.println(category);
        try {
            if (result.hasErrors()) {
                List<FieldError> errors = result.getFieldErrors();
                Map<String, Object> errorsHashMap = new HashMap<>();

                for (FieldError fieldError : errors) {
                    errorsHashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return Msg.failure().add("errors", errorsHashMap);
            } else {
                categoryService.addCategory(category);
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.failure().add("errors", "exception");
    }

    @RequestMapping(value = "/category/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteByIds(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            String[] ids_String = ids.split("-");
            List<Integer> ids_Integer = new ArrayList<>();
            for (int i = 0; i < ids_String.length; i++) {
                ids_Integer.add(Integer.parseInt(ids_String[i]));
            }
            categoryService.deleteByExample(ids_Integer);
        } else {
            Integer id = Integer.parseInt(ids);
            categoryService.deleteByPrimaryKey(id);
        }

        return Msg.success();
    }

    @RequestMapping(value = "/categorybyid/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category categorybyid(@PathVariable("id") Integer id) {


        Category category = categoryService.selectByPrimaryKey(id);


        return category;
    }
}
