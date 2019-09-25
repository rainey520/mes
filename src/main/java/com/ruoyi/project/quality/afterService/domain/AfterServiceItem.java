package com.ruoyi.project.quality.afterService.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;


/**
 * 搜索售后服务包装类
 * @Author: Rainey
 * @Date: 2019/8/22 10:00
 * @Version: 1.0
 **/
public class AfterServiceItem extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /** 搜索条件 */
    private String searchItem;
    /** 总的记录数 */
    private Integer totalNum;
    /** 开始时间 */
    private Date sTime;
    /** 结束时间 */
    private Date eTime;
    /** 所有录入者姓名 */
    private String userNames;


    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    @Override
    public String toString() {
        return "AfterServiceItem{" +
                "searchItem='" + searchItem + '\'' +
                ", totalNum=" + totalNum +
                ", sTime=" + sTime +
                ", eTime=" + eTime +
                ", userNames='" + userNames + '\'' +
                '}';
    }
}
