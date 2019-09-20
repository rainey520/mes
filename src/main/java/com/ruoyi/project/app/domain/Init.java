package com.ruoyi.project.app.domain;

import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.system.menu.domain.Menu;

import java.util.List;

public class Init {
    private List<DevWorkOrder> workList;
    private List<Menu> menuList;
    private DevCompany company;
    private List<String> comPicList;
    //用户头像
    private String userPhoto;

    public List<String> getComPicList() {
        return comPicList;
    }

    public void setComPicList(List<String> comPicList) {
        this.comPicList = comPicList;
    }

    public List<DevWorkOrder> getWorkList() {
        return workList;
    }

    public void setWorkList(List<DevWorkOrder> workList) {
        this.workList = workList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public DevCompany getCompany() {
        return company;
    }

    public void setCompany(DevCompany company) {
        this.company = company;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return "Init{" +
                "workList=" + workList +
                ", menuList=" + menuList +
                ", company=" + company +
                '}';
    }
}
