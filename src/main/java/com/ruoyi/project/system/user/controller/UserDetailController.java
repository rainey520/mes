package com.ruoyi.project.system.user.controller;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户个人设置控制层
 */
@Controller
@RequestMapping("/userDetail")
public class UserDetailController extends BaseController {

    @Autowired
    private RuoYiConfig ruoYiConfig;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDevCompanyService devCompanyService;


    private String prefix = "system/userDetail";

    @GetMapping("/userDetail")
    public String userDetail(ModelMap mmap, HttpServletRequest request){
        // 取身份信息
        User user = JwtUtil.getTokenUser(request);
        user = userService.selectUserById(user.getUserId());
        // 根据用户id查询出公司信息
        DevCompany devCompany = devCompanyService.selectDevCompanyById(user.getCompanyId());
        mmap.put("user", userService.selectUserById(user.getUserId()));
        mmap.put("company", devCompany);
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
            return prefix +"/userDetailEn";
        }
        return prefix +"/userDetail";
    }

}

