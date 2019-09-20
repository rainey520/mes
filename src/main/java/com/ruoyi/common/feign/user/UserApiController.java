package com.ruoyi.common.feign.user;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 用户交付接口
 */
@RestController
@RequestMapping("/u")
public class UserApiController {
    @Autowired
    private IUserService userService;
    /**
     * 初始化用户数据
     * @param user 用户数据
     * @return
     */
    @RequestMapping("/init")
    public AjaxResult initUserInfo(@RequestBody  User user){
        try {
            user.setCreateTime(new Date());
            userService.register(user);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }
}
