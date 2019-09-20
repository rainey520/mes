package com.ruoyi.project.production.workDayHour.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 工单各个IO口每天每小时数据表 dev_work_day_hour
 *
 * @author zqm
 * @date 2019-04-16
 */
public class WorkDayHour extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 工单IO每小时数据主键ID
     */
    private Integer hourId;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 产线主键ID
     */
    private Integer lineId;
    /**
     * 工单主键ID
     */
    private Integer workId;
    /**
     * 硬件主键ID
     */
    private Integer devId;
    /**
     * 硬件名称
     */
    private String devName;
    /**
     * IO口
     */
    private Integer ioId;
    /**
     * IO名称
     */
    private String ioName;
    /**
     * IO顺序
     */
    private Integer ioOrder;
    /**
     * 0~1点的数据
     */
    private Integer hour1;
    /**
     * 1~2点的数据
     */
    private Integer hour2;
    /**
     * 2~3点的数据
     */
    private Integer hour3;
    /**
     * 3~4点的数据
     */
    private Integer hour4;
    /**
     * 4~5点的数据
     */
    private Integer hour5;
    /**
     * 5~6点的数据
     */
    private Integer hour6;
    /**
     * 6~7点的数据
     */
    private Integer hour7;
    /**
     * 7~8点的数据
     */
    private Integer hour8;
    /**
     * 8~9点的数据
     */
    private Integer hour9;
    /**
     * 9~10点的数据
     */
    private Integer hour10;
    /**
     * 10~11点的数据
     */
    private Integer hour11;
    /**
     * 11~12点的数据
     */
    private Integer hour12;
    /**
     * 12~13点的数据
     */
    private Integer hour13;
    /**
     * 13~14点的数据
     */
    private Integer hour14;
    /**
     * 14~15点的数据
     */
    private Integer hour15;
    /**
     * 15~16点的数据
     */
    private Integer hour16;
    /**
     * 16~17点的数据
     */
    private Integer hour17;
    /**
     * 17~18点的数据
     */
    private Integer hour18;
    /**
     * 18~19点的数据
     */
    private Integer hour19;
    /**
     * 19~20点的数据
     */
    private Integer hour20;
    /**
     * 20~21点的数据
     */
    private Integer hour21;
    /**
     * 21~22点的数据
     */
    private Integer hour22;
    /**
     * 22~23点的数据
     */
    private Integer hour23;
    /**
     * 23~24点的数据
     */
    private Integer hour24;
    /**
     * 对应数据记录日期
     */
    private Date dataTime;
    /**
     * 创建时间
     */
    private Date createTime;

    public void setHourId(Integer hourId) {
        this.hourId = hourId;
    }

    public Integer getHourId() {
        return hourId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevName() {
        return devName;
    }

    public void setIoId(Integer ioId) {
        this.ioId = ioId;
    }

    public Integer getIoId() {
        return ioId;
    }

    public void setIoName(String ioName) {
        this.ioName = ioName;
    }

    public String getIoName() {
        return ioName;
    }

    public void setIoOrder(Integer ioOrder) {
        this.ioOrder = ioOrder;
    }

    public Integer getIoOrder() {
        return ioOrder;
    }

    public void setHour1(Integer hour1) {
        this.hour1 = hour1;
    }

    public Integer getHour1() {
        return hour1;
    }

    public void setHour2(Integer hour2) {
        this.hour2 = hour2;
    }

    public Integer getHour2() {
        return hour2;
    }

    public void setHour3(Integer hour3) {
        this.hour3 = hour3;
    }

    public Integer getHour3() {
        return hour3;
    }

    public void setHour4(Integer hour4) {
        this.hour4 = hour4;
    }

    public Integer getHour4() {
        return hour4;
    }

    public void setHour5(Integer hour5) {
        this.hour5 = hour5;
    }

    public Integer getHour5() {
        return hour5;
    }

    public void setHour6(Integer hour6) {
        this.hour6 = hour6;
    }

    public Integer getHour6() {
        return hour6;
    }

    public void setHour7(Integer hour7) {
        this.hour7 = hour7;
    }

    public Integer getHour7() {
        return hour7;
    }

    public void setHour8(Integer hour8) {
        this.hour8 = hour8;
    }

    public Integer getHour8() {
        return hour8;
    }

    public void setHour9(Integer hour9) {
        this.hour9 = hour9;
    }

    public Integer getHour9() {
        return hour9;
    }

    public void setHour10(Integer hour10) {
        this.hour10 = hour10;
    }

    public Integer getHour10() {
        return hour10;
    }

    public void setHour11(Integer hour11) {
        this.hour11 = hour11;
    }

    public Integer getHour11() {
        return hour11;
    }

    public void setHour12(Integer hour12) {
        this.hour12 = hour12;
    }

    public Integer getHour12() {
        return hour12;
    }

    public void setHour13(Integer hour13) {
        this.hour13 = hour13;
    }

    public Integer getHour13() {
        return hour13;
    }

    public void setHour14(Integer hour14) {
        this.hour14 = hour14;
    }

    public Integer getHour14() {
        return hour14;
    }

    public void setHour15(Integer hour15) {
        this.hour15 = hour15;
    }

    public Integer getHour15() {
        return hour15;
    }

    public void setHour16(Integer hour16) {
        this.hour16 = hour16;
    }

    public Integer getHour16() {
        return hour16;
    }

    public void setHour17(Integer hour17) {
        this.hour17 = hour17;
    }

    public Integer getHour17() {
        return hour17;
    }

    public void setHour18(Integer hour18) {
        this.hour18 = hour18;
    }

    public Integer getHour18() {
        return hour18;
    }

    public void setHour19(Integer hour19) {
        this.hour19 = hour19;
    }

    public Integer getHour19() {
        return hour19;
    }

    public void setHour20(Integer hour20) {
        this.hour20 = hour20;
    }

    public Integer getHour20() {
        return hour20;
    }

    public void setHour21(Integer hour21) {
        this.hour21 = hour21;
    }

    public Integer getHour21() {
        return hour21;
    }

    public void setHour22(Integer hour22) {
        this.hour22 = hour22;
    }

    public Integer getHour22() {
        return hour22;
    }

    public void setHour23(Integer hour23) {
        this.hour23 = hour23;
    }

    public Integer getHour23() {
        return hour23;
    }

    public void setHour24(Integer hour24) {
        this.hour24 = hour24;
    }

    public Integer getHour24() {
        return hour24;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("hourId", getHourId())
                .append("companyId", getCompanyId())
                .append("lineId", getLineId())
                .append("workId", getWorkId())
                .append("devId", getDevId())
                .append("devName", getDevName())
                .append("ioId", getIoId())
                .append("ioName", getIoName())
                .append("ioOrder", getIoOrder())
                .append("hour1", getHour1())
                .append("hour2", getHour2())
                .append("hour3", getHour3())
                .append("hour4", getHour4())
                .append("hour5", getHour5())
                .append("hour6", getHour6())
                .append("hour7", getHour7())
                .append("hour8", getHour8())
                .append("hour9", getHour9())
                .append("hour10", getHour10())
                .append("hour11", getHour11())
                .append("hour12", getHour12())
                .append("hour13", getHour13())
                .append("hour14", getHour14())
                .append("hour15", getHour15())
                .append("hour16", getHour16())
                .append("hour17", getHour17())
                .append("hour18", getHour18())
                .append("hour19", getHour19())
                .append("hour20", getHour20())
                .append("hour21", getHour21())
                .append("hour22", getHour22())
                .append("hour23", getHour23())
                .append("hour24", getHour24())
                .append("dataTime", getDataTime())
                .append("createTime", getCreateTime())
                .toString();
    }
}
