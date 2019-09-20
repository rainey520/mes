package com.ruoyi.project.system.user.api;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.user.domain.UserApp;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户app交互接口
 * @Author: Rainey
 * @Date: 2019/7/27 9:42
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/api/u")
public class UserAppController extends BaseController {

    @Autowired
    private IUserService userService;

    @Value("${file.iso}")
    private String fileUrl;

    @PostMapping("/list")
    @ResponseBody
    public AjaxResult appSelectUserList(@RequestBody UserApp userApp){
        try {
            if (userApp != null) {
                userApp.appStartPage();
                return AjaxResult.success("请求成功",userService.appSelectUserList(userApp));
            }
            return AjaxResult.error();
        } catch (Exception e) {
            return AjaxResult.error("请求失败");
        }
    }

}
