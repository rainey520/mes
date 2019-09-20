package com.ruoyi.project.device.devList.domain;

import java.io.Serializable;

/**
 * 硬件api接口包装类
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.project.device.devList.domain
 * @Author: Administrator
 * @Date: 2019/6/12 10:04
 * @Description //TODO
 * @Version: 1.0
 **/
public class DevListResult implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int deviceStatus;
    private int configStatus;
    private String deviceName;
    private Integer companyId;
    private String remark;
    private String deviceId;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public int getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(int configStatus) {
        this.configStatus = configStatus;
    }

    @Override
    public String toString() {
        return "DevListResult{" +
                "id=" + id +
                ", deviceStatus=" + deviceStatus +
                ", configStatus=" + configStatus +
                ", deviceName='" + deviceName + '\'' +
                ", companyId=" + companyId +
                ", remark='" + remark + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
