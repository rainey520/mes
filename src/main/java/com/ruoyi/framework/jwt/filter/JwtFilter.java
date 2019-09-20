package com.ruoyi.framework.jwt.filter;



import com.ruoyi.common.feign.FeignUtils;
import com.ruoyi.framework.jwt.JwtToken;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Autowired
    private IUserService userService;
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if(StringUtils.isEmpty(token)){
            Cookie[] cookies = req.getCookies();
            if(cookies == null)return false;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                }
            }
        }
        if(StringUtils.isEmpty(token)){
            return false;
        }
        User u = JwtUtil.getUserByToken(token);
        if(u == null){
            return false;
        }
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if(StringUtils.isEmpty(token)){
            Cookie[] cookies = req.getCookies();
            if(cookies == null)return false;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                }
            }
        }

        if(StringUtils.isEmpty(token)){
            return false;
        }
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
               return executeLogin(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                responseindex(request, response);
            }
        }
        responseindex(request, response);
        return false;
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void responseindex(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect(FeignUtils.MAIN_LOGIN_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
