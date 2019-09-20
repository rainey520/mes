package com.ruoyi.project.device.devDeviceCounts.domain;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务包装类
 * 查询IO口上传数据记录dev_data_log表返回数据
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.project.device.devDeviceCounts.domain
 * @Author: Administrator
 * @Date: 2019/4/23 18:42
 * @Description //TODO
 * @Version: 1.0
 **/
public class DataLogTask implements Serializable {
    private static final long serialVersionUID = 6663397464237667197L;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 总计数
     */
    private Integer sumData;

    /**
     * 小时
     */
    private Integer timeHour;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getSumData() {
        return sumData;
    }

    public void setSumData(Integer sumData) {
        this.sumData = sumData;
    }

    public Integer getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(Integer timeHour) {
        this.timeHour = timeHour;
    }
}
