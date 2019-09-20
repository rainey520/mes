package com.ruoyi.project.system.user.controller;

import com.ruoyi.framework.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/info")
public class InfoController extends BaseController {

    private String prefix = "system/user";



    @RequiresPermissions("system:info:view")
    @GetMapping()
    public String info(){
        return prefix +"/info";
    }
}
