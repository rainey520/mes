package com.ruoyi.project.quality.mesBatch.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * MES批准追踪表 tab_mes_batch
 * 
 * @author sj
 * @date 2019-07-22
 */
public class MesBatch extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
    /** 工单id */
	private Integer workId;
	/** 公司id */
	private Integer companyId;
	/** 主码 */
	@Excel(name = "主码")
	private String mesCode;
	/** 工单号 */
	@Excel(name = "工单号")
	private String workCode;
	/** 订单号 */
	@Excel(name = "订单号")
	private String orderCode;
	/** 产品/半成品编码 */
	@Excel(name = "产品/半成品")
	private String pbCode;
	/** 规则id */
	private Integer ruleId;
	/** 规则名称 */
	private String ruleName;
	/** 规则物料 */
	private String ruleMateriel;
	/** 创建时间 */
	@Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date cTime;
	private Date uTime;
	/** MES标记扫描字段(1-12优先级别递增)、0代表生产未扫描，1，代表生产已扫描,默认值12位0 */
	private String mesSign;
	/** MES批次追踪明细 */
	private List<MesBatchDetail> mesBatchDetailList;

	/** 物料批次追踪条件 */
    private String batchCode;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getMesSign() {
        return mesSign;
    }

    public void setMesSign(String mesSign) {
        this.mesSign = mesSign;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getMesCode() {
        return mesCode;
    }

    public void setMesCode(String mesCode) {
        this.mesCode = mesCode;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPbCode() {
        return pbCode;
    }

    public void setPbCode(String pbCode) {
        this.pbCode = pbCode;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleMateriel() {
        return ruleMateriel;
    }

    public void setRuleMateriel(String ruleMateriel) {
        this.ruleMateriel = ruleMateriel;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public List<MesBatchDetail> getMesBatchDetailList() {
        return mesBatchDetailList;
    }

    public void setMesBatchDetailList(List<MesBatchDetail> mesBatchDetailList) {
        this.mesBatchDetailList = mesBatchDetailList;
    }

    @Override
    public String toString() {
        return "MesBatch{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", mesCode='" + mesCode + '\'' +
                ", workCode='" + workCode + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", pbCode='" + pbCode + '\'' +
                ", ruleId=" + ruleId +
                ", ruleName='" + ruleName + '\'' +
                ", ruleMateriel='" + ruleMateriel + '\'' +
                ", cTime=" + cTime +
                ", uTime=" + uTime +
                ", mesSign='" + mesSign + '\'' +
                ", mesBatchDetailList=" + mesBatchDetailList +
                ", batchCode='" + batchCode + '\'' +
                '}';
    }
}
