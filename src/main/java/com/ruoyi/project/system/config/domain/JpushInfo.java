package com.ruoyi.project.system.config.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * @Author: Rainey
 * @Date: 2019/10/15 15:02
 * @Version: 1.0
 **/
public class JpushInfo extends BaseEntity {

    private static final long serialVersionUID = 3313785711625738233L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 公司看板账号
     */
    private String companyNumber;
    /**
     * 首次登陆时间
     */
    private String firstTime;
    /**
     * 上次访问时间
     */
    private Date lastTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "JpushInfo{" +
                "id=" + id +
                ", companyNumber='" + companyNumber + '\'' +
                ", firstTime='" + firstTime + '\'' +
                ", lastTime='" + lastTime + '\'' +
                '}';
    }
}
