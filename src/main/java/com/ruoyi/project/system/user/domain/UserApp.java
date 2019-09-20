package com.ruoyi.project.system.user.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户app交互封装
 * @Author: Rainey
 * @Date: 2019/7/27 9:50
 * @Version: 1.0
 **/
public class UserApp extends BaseEntity {
    private Integer uid;
    private String userName;
    private String phone;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
