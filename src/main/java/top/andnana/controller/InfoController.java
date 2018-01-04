package top.andnana.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.andnana.entity.*;
import top.andnana.service.InfoService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.andnana.service.LabelInfoService;
import top.andnana.service.LabelService;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
@Controller
public class InfoController {
    @Autowired
    private InfoService infoService;
    @Autowired
    private LabelInfoService labelInfoService;

    @RequestMapping(value = "/infolist", method = RequestMethod.GET)
    @ResponseBody
    public Msg HelloWorld(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, @RequestParam(value = "cateid", defaultValue = "2") Integer cateid) {
        System.out.println("pageNumber");
        System.out.println(pageNumber);
        PageHelper.startPage(pageNumber, 8);
        List<Info> infoList = infoService.selectByCategory(cateid);
        PageInfo pageInfo = new PageInfo(infoList, 5);

        System.out.println("员工数据：");

        for (int i = 0; i < infoList.size(); i++) {
            System.out.println(infoList.get(i).getId());
        }

        System.out.println("dfsdfsadf");
        return Msg.success().add("myInfo", pageInfo);
    }

    @RequestMapping("/checkInfoTitle")
    @ResponseBody
    public Msg checkTitle(@RequestParam("title") String title) {
        String titleReg = "(^[\\u2E80-\\u9FFFa-zA-Z0-9_-]{2,50}$)";
        boolean available = title.matches(titleReg);
        if (!available) {
            return Msg.failure().add("otherMsg", "标题在2-50位中英文字符（a-zA-Z0-9_-)");
        }

        Info info = infoService.getInfoByTitle(title);
        return info != null ? Msg.failure().add("otherMsg", "标题重复") : Msg.success().add("otherMsg", "标题可用");
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveInfo(@Valid Info info, BindingResult result,
                        @RequestParam(value = "img", required = false) MultipartFile img
            , HttpServletRequest request, @RequestParam("labelIds") String[] labelIds) {

//      String fileName = getFileName(img.getOriginalFilename());
        System.out.println("info");
        System.out.println(info);
        System.out.println(info.getContent());
        System.out.println(info.getPicName());
        System.out.println(info.getCategory());
        System.out.println(info.getLabelList());
        System.out.println("labelIds");
        for (int i = 0; i < labelIds.length; i++) {
            System.out.println(labelIds[i]);

        }
        System.out.println("img");
        System.out.println(img);
        System.out.println("img.getOriginalFileName()");
        System.out.println(img.getOriginalFilename());
        try {
            if (result.hasErrors()) {
                List<FieldError> errors = result.getFieldErrors();
                Map<String, Object> errorsHashMap = new HashMap<>();

                for (FieldError fieldError : errors) {
                    errorsHashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return Msg.failure().add("errors", errorsHashMap);
            } else {
                try {
                    if (!img.isEmpty()) {
                        if (!img.getContentType().equals("image/jpeg")) {
                            return Msg.failure().add("otherMsg", "图片格式应为jpg");
                        }
                        System.out.println(request.getServletContext().getRealPath("") + "resources" + File.separator + "images" + File.separator + img.getOriginalFilename());
                        File file = new File(request.getServletContext().getRealPath("") + "resources" + File.separator + "images" + File.separator + img.getOriginalFilename());
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        img.transferTo(file);
                        Thumbnails.of(file)
                                .size(200, 200)
                                .toFile(file);


                        info.setPicName(img.getOriginalFilename());
                        info.setInfoDate(new Date());
                        infoService.addInfo(info);
                        List<LabelInfo> labelInfos = new ArrayList<>();
                        for (int i = 0; i < labelIds.length; i++) {
                            System.out.println(labelIds[i]);
                            LabelInfo labelInfo = new LabelInfo();
                            labelInfo.setInfoId(info.getId());
                            labelInfo.setLabelId(Integer.valueOf(labelIds[i]));
                            labelInfos.add(labelInfo);
                        }
                        labelInfoService.addLabelInfos(labelInfos);


                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.failure().add("errors", "exception");
    }

    @RequestMapping(value = "/info/{infoid}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getInfoByPrimaryKey(@PathVariable("infoid") Integer infoid) {
        Info info = infoService.selectByPrimaryKey(infoid);
        return Msg.success().add("info", info);
    }

    private String getFileName(String fileName) {

        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return System.currentTimeMillis() + extension;
    }

}
