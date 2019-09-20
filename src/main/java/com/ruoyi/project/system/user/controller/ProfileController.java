package com.ruoyi.project.system.user.controller;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.PasswordUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user/profile")
public class ProfileController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private String prefix = "system/user/profile";

    @Autowired
    private IUserService userService;


    @Autowired
    private DictService dict;


    @Value("${file.iso}")
    private String imgUrl;

    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap, HttpServletRequest request) {
        User user = JwtUtil.getTokenUser(request);
        user.setSex(dict.getLabel("sys_user_sex", user.getSex()));
        mmap.put("user", user);
        mmap.put("roleGroup", userService.selectUserRoleGroup(user.getUserId()));
        mmap.put("postGroup", userService.selectUserPostGroup(user.getUserId()));
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password, HttpServletRequest request) {
        User user = JwtUtil.getUser();
        if (PasswordUtil.matches(user, password)) {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap, HttpServletRequest request) {
        User user = JwtUtil.getTokenUser(request);
        mmap.put("user", userService.selectUserById(user.getUserId()));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword, HttpServletRequest request) {
        User user = JwtUtil.getTokenUser(request);
        if (StringUtils.isNotEmpty(newPassword) && PasswordUtil.matches(user, oldPassword)) {
            user.setPassword(newPassword);
            if (userService.resetUserPwd(user) > 0) {
                setSysUser(userService.selectUserById(user.getUserId()));
                return success();
            }
            return error();
        } else {
            return error("修改密码失败，旧密码错误");
        }
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap, HttpServletRequest request) {
        User user = JwtUtil.getTokenUser(request);
        mmap.put("user", userService.selectUserById(user.getUserId()));
        return prefix + "/edit";
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap, HttpServletRequest request) {
        User user = JwtUtil.getTokenUser(request);
        mmap.put("user", userService.selectUserById(user.getUserId()));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(User user, HttpServletRequest request) {
        try {
            User tokenUser = JwtUtil.getTokenUser(request);
            User currentUser = userService.selectUserById(tokenUser.getUserId());
            currentUser.setUserName(user.getUserName());
            currentUser.setEmail(user.getEmail());
            currentUser.setSex(user.getSex());
            // 设置用户标记为0 已经完成初始化设置
            currentUser.setLoginTag(UserConstants.LOGIN_TAG_ADD);
            if (userService.updateUserInfo(currentUser, request) > 0) {
                setSysUser(userService.selectUserById(currentUser.getUserId()));
                return success();
            }

        } catch (Exception e) {

        }
        return error();
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file, HttpServletRequest request) {
        User currentUser = JwtUtil.getTokenUser(request);
        try {
            if (!file.isEmpty()) {
                //判断SOP文件夹是否存在
                String sopPath = RuoYiConfig.getProfile() + "mes" + currentUser.getCompanyId();
                File f = new File(sopPath);
                if (!f.exists()) {
                    //不存在创建对应文件夹
                    f.mkdir();
                }
                String path = sopPath + "/";
                String avatar = FileUploadUtils.upload(path, file);
                currentUser.setLoginTag(UserConstants.LOGIN_TAG_ADD);
                currentUser.setAvatar(imgUrl + "mes" + currentUser.getCompanyId() + "/" + avatar);
                String imgurl = imgUrl + "mes" + currentUser.getCompanyId() + "/" + avatar;
                if (userService.updateUserInfo(currentUser, request) > 0) {
                    setSysUser(userService.selectUserById(currentUser.getUserId()));
                    return AjaxResult.success("success", imgurl);
                }
                return error();
            }
            return error();
        } catch (Exception e) {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }

    /**
     * 更新用户的登录标记为0
     *
     * @param user
     * @return
     */
    @PostMapping("/changeLoginTag")
    @ResponseBody
    public AjaxResult changeLoginTag(User user, HttpServletRequest request) {
        try {
            if (userService.changeLoginTag(user, request) > 0) {
                setSysUser(userService.selectUserById(JwtUtil.getTokenUser(request).getUserId()));
                return AjaxResult.success("success", user);
            }
            return error();
        } catch (Exception e) {
            return error("初始化失败");
        }
    }
}
