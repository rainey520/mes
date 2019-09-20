package com.ruoyi.common.feign.initdata.domain;

import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.system.user.domain.User;

/**
 * 用户授权进行初始化数据
 */
public class InitData {
    private User user;
    private DevCompany company;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DevCompany getCompany() {
        return company;
    }

    public void setCompany(DevCompany company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "InitData{" +
                "user=" + user +
                ", company=" + company +
                '}';
    }
}
