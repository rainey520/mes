package com.ruoyi.common.utils;

import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 密码工具类
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.common.utils
 * @Author: Administrator
 * @Date: 2019/6/10 11:49
 * @Description //TODO
 * @Version: 1.0
 **/
public class PasswordUtil {
    /**
     * 编译密码
     * @param username 用户名
     * @param password 用户密码
     * @param salt 盐
     * @return 结果
     */
    public static String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex().toString();
    }

    /**
     * 对比用户名密码
     * @param user 用户
     * @param newPassword 输入密码
     * @return 结果
     */
    public static boolean matches(User user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }
}
