package com.ruoyi.project.tem.add.controller;

import com.ruoyi.framework.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 新建页面
 */
@Controller
@RequestMapping("/tem/add")
public class TemAddController extends BaseController {

    private String prefix = "/tem/add";

    @GetMapping()
    public String temAdd(){
        return prefix+"/addTem";
    }
}
