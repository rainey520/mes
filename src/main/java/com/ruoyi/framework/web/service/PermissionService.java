package com.ruoyi.framework.web.service;

import com.ruoyi.common.utils.ServletUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

/**
 * RuoYi首创 js调用 thymeleaf 实现按钮权限可见性
 * 
 * @author ruoyi
 */
@Service("permission")
public class PermissionService
{
    public String hasPermi(String permission)
    {
        return isPermittedOperator(permission) ? "" : "hidden";
    }

    private boolean isPermittedOperator(String permission)
    {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

}
