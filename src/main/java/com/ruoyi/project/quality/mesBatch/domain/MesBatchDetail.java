package com.ruoyi.project.quality.mesBatch.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * MES批准追踪详情表 tab_mes_batch_detail
 * 
 * @author sj
 * @date 2019-07-22
 */
public class MesBatchDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 追踪id */
	private Integer bId;
	/** 物料编码 */
	private String materielCode;
	/** 配置批次号 */
	private String batchCode;
	/** 生产批次号 */
	private String proBatchCode;
	/** 创建时间 */
	private Date cTime;
	/** 关联半成品是否配置(默认值0未配置，1已配置) */
	private Integer dTag;
	/** MES关联类型(默认值2物料，1半成品) */
	private Integer dType;
	/** 批次数量 */
	private Integer mesNumber;
	/** 物料用量总数 */
	private Integer totalNum;
	/** 批次先后排序(1-6优先级别递减，1最高) */
	private Integer ruleOrder;

	/******************* MES二级追溯明细信息 ********************/
	/******************** 工单主表二级追溯信息相关 ***********************/
	/** 工单号 */
	private String workCodePart;
	/** 产线名称 */
	private String lineNamePart;
	/** 产品编码 */
	private String productCodePart;
	/** 产品名称 */
	private String productNamePart;
	/** 生产数量 */
	private Integer workNumberPart;
	/** 开始时间 */
	private Date startTimePart;
	/** 结束时间 */
	private Date endTimePart;
	/** MES半成品二级追溯明细，明细为物料相关信息 */
	private List<MesBatchDetail> mesPartList;

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getProBatchCode() {
		return proBatchCode;
	}

	public void setProBatchCode(String proBatchCode) {
		this.proBatchCode = proBatchCode;
	}

	public Integer getMesNumber() {
		return mesNumber;
	}

	public void setMesNumber(Integer mesNumber) {
		this.mesNumber = mesNumber;
	}

	public Integer getRuleOrder() {
		return ruleOrder;
	}

	public void setRuleOrder(Integer ruleOrder) {
		this.ruleOrder = ruleOrder;
	}

	public Integer getWorkNumberPart() {
		return workNumberPart;
	}

	public void setWorkNumberPart(Integer workNumberPart) {
		this.workNumberPart = workNumberPart;
	}

	public Integer getdTag() {
		return dTag;
	}

	public void setdTag(Integer dTag) {
		this.dTag = dTag;
	}

	public Integer getdType() {
		return dType;
	}

	public void setdType(Integer dType) {
		this.dType = dType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getbId() {
		return bId;
	}

	public void setbId(Integer bId) {
		this.bId = bId;
	}

	public String getMaterielCode() {
		return materielCode;
	}

	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getWorkCodePart() {
		return workCodePart;
	}

	public void setWorkCodePart(String workCodePart) {
		this.workCodePart = workCodePart;
	}

	public String getLineNamePart() {
		return lineNamePart;
	}

	public void setLineNamePart(String lineNamePart) {
		this.lineNamePart = lineNamePart;
	}

	public String getProductCodePart() {
		return productCodePart;
	}

	public void setProductCodePart(String productCodePart) {
		this.productCodePart = productCodePart;
	}

	public String getProductNamePart() {
		return productNamePart;
	}

	public void setProductNamePart(String productNamePart) {
		this.productNamePart = productNamePart;
	}

	public Date getStartTimePart() {
		return startTimePart;
	}

	public void setStartTimePart(Date startTimePart) {
		this.startTimePart = startTimePart;
	}

	public Date getEndTimePart() {
		return endTimePart;
	}

	public void setEndTimePart(Date endTimePart) {
		this.endTimePart = endTimePart;
	}

	public List<MesBatchDetail> getMesPartList() {
		return mesPartList;
	}

	public void setMesPartList(List<MesBatchDetail> mesPartList) {
		this.mesPartList = mesPartList;
	}

	@Override
	public String toString() {
		return "MesBatchDetail{" +
				"id=" + id +
				", bId=" + bId +
				", materielCode='" + materielCode + '\'' +
				", batchCode='" + batchCode + '\'' +
				", proBatchCode='" + proBatchCode + '\'' +
				", cTime=" + cTime +
				", dTag=" + dTag +
				", dType=" + dType +
				", mesNumber=" + mesNumber +
				", ruleOrder=" + ruleOrder +
				", workCodePart='" + workCodePart + '\'' +
				", lineNamePart='" + lineNamePart + '\'' +
				", productCodePart='" + productCodePart + '\'' +
				", productNamePart='" + productNamePart + '\'' +
				", workNumberPart=" + workNumberPart +
				", startTimePart=" + startTimePart +
				", endTimePart=" + endTimePart +
				", mesPartList=" + mesPartList +
				'}';
	}
}
