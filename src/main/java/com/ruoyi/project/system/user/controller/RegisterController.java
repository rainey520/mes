package com.ruoyi.project.system.user.controller;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户注册
 *
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.project.system.user.controller
 * @Author: Administrator
 * @Date: 2019/4/3 16:29
 * @Description //TODO
 * @Version: 1.0
 **/
@Controller
public class RegisterController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转注册页面
     *
     * @return
     */
    @GetMapping("/register")
    public String register(Integer langV) {
        if (UserConstants.LANGUAGE_EN.equals(langV)) {
            return "registerEn";
        }
        return "register";
    }

    @ResponseBody
    @RequestMapping("/registerAdd")
    public AjaxResult registerAdd(User user){
        try {
            userService.register(user);
            return success();
        }catch (Exception e){
            return error(e.getMessage());
        }

    }

}
