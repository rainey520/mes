package com.ruoyi.project.tem.data.controller;

import com.ruoyi.framework.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面清单
 */
@Controller
@RequestMapping("/tem/data")
public class TemDataController extends BaseController {

    private String prefix = "/tem/data";

    @GetMapping()
    public String tem(){
        return  prefix +"/data";
    }
}
