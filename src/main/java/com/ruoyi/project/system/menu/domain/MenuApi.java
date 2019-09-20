package com.ruoyi.project.system.menu.domain;


/**
 * 菜单app交互封装类
 * @Author: Rainey
 * @Date: 2019/7/27 8:36
 * @Version: 1.0
 **/
public class MenuApi{
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 菜单父id
     */
    private Integer parentId;
    /**
     * 菜单id
     */
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 设备编号
     */
    private String devCode;
    /**
     * 设备类型
     */
    private String devType;

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MenuApi{" +
                "uid=" + uid +
                ", parentId=" + parentId +
                ", menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
