package com.ruoyi.project.system.user.controller;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.production.filesource.domain.FileSourceInfo;
import com.ruoyi.project.production.filesource.service.IFileSourceInfoService;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    private IMenuService menuService;

    @Autowired
    private RuoYiConfig ruoYiConfig;

    @Autowired
    private IDevCompanyService devCompanyService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IFileSourceInfoService fileInfoService;

    @GetMapping("/s")
    public String s(ModelMap mmap,HttpServletRequest request){
        mmap.put("token",request.getParameter("token"));
        return "s";
    }

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap, HttpServletRequest request) {
        // 取身份信息
        User tokenUser = JwtUtil.getTokenUser(request);
        User user = userService.selectUserById(tokenUser.getUserId());
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        DevCompany company = devCompanyService.selectDevCompanyById(user.getCompanyId());
        user.setDevCompany(company);

        mmap.put("menus", menus);
        mmap.put("user",user);
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
            return "index1";
        }
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap,HttpServletRequest request) {
        // 取身份信息
        User tokenUser = JwtUtil.getTokenUser(request);
        User user = userService.selectUserById(tokenUser.getUserId());
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        DevCompany devCompany = devCompanyService.selectDevCompanyById(user.getCompanyId());
        user.setDevCompany(devCompany);
        if (devCompany != null) {
            // 查询该公司的轮播图信息
            List<FileSourceInfo> picList =  fileInfoService.selectFileByComPic(user.getCompanyId(),0);
            if (StringUtils.isNotEmpty(picList)) {
                mmap.put("picList",picList);
            } else {
                mmap.put("picList",null);
            }
        }
        mmap.put("user",user);
        if (user != null && user.getLoginTag() != null) {
            mmap.put("userTag",user.getLoginTag());
        } else {
            mmap.put("userTag",user.getLoginTag());
        }
        mmap.put("version", ruoYiConfig.getVersion());
        if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
            return "main1";
        }
        return "main";
    }

    /**
     * 验证用户的登录标记
     *
     * @return
     */
    @PostMapping("/checkUserLoginTag")
    @ResponseBody
    public AjaxResult checkUserLoginTag(HttpServletRequest request) {
        User u = JwtUtil.getUser();
        u = userService.selectUserById(u.getUserId());
        if (u.getLoginTag().equals(UserConstants.LOGIN_TAG_ADD)) { // 说明用户已经设置过了
            return AjaxResult.success("success", "1");
        } else {
            return AjaxResult.success("error", "0"); // 用户未设置
        }
    }

}
