package com.ruoyi.project.quality.afterService.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 售后服务表 tab_after_service
 * 
 * @author sj
 * @date 2019-08-20
 */
public class AfterService extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 售后服务主键 */
	private Integer id;
	/** 录入批次信息 */
	@Excel(name = "批次信息", type = Excel.Type.EXPORT)
	private String inputBatchInfo;
	/** 搜索条件，多个逗号隔开 */
	private String searchItems;
	/** 录入时间 */
	@Excel(name = "录入时间", type = Excel.Type.EXPORT, dateFormat = "yyyy-MM-dd HH:ss")
	private Date inputTime;
	/** 录入对象id */
	private Integer inputUserId;
	/** 录入对象名称 */
	@Excel(name = "录入者名称", type = Excel.Type.EXPORT)
	private String inputUserName;
	/** 批次号(预留字段) */
	private String batchCode;
	/** 所属公司ID */
	private Integer companyId;
	/** 数量 */
	private Integer count;

	/**
	 * 权限拉取
	 */
	private Integer menuId;

	/**
	 * 检索有效期，开始结束时间
	 */
	private String searchBeginTime;
	private String searchEndTime;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSearchBeginTime() {
		return searchBeginTime;
	}

	public void setSearchBeginTime(String searchBeginTime) {
		this.searchBeginTime = searchBeginTime;
	}

	public String getSearchEndTime() {
		return searchEndTime;
	}

	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getInputUserName() {
		return inputUserName;
	}

	public void setInputUserName(String inputUserName) {
		this.inputUserName = inputUserName;
	}

	public String getSearchItems() {
		return searchItems;
	}

	public void setSearchItems(String searchItems) {
		this.searchItems = searchItems;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setInputBatchInfo(String inputBatchInfo) 
	{
		this.inputBatchInfo = inputBatchInfo;
	}

	public String getInputBatchInfo() 
	{
		return inputBatchInfo;
	}
	public void setInputTime(Date inputTime) 
	{
		this.inputTime = inputTime;
	}

	public Date getInputTime() 
	{
		return inputTime;
	}
	public void setInputUserId(Integer inputUserId) 
	{
		this.inputUserId = inputUserId;
	}

	public Integer getInputUserId() 
	{
		return inputUserId;
	}
	public void setBatchCode(String batchCode) 
	{
		this.batchCode = batchCode;
	}

	public String getBatchCode() 
	{
		return batchCode;
	}

	@Override
	public String toString() {
		return "AfterService{" +
				"id=" + id +
				", inputBatchInfo='" + inputBatchInfo + '\'' +
				", searchItems='" + searchItems + '\'' +
				", inputTime=" + inputTime +
				", inputUserId=" + inputUserId +
				", inputUserName='" + inputUserName + '\'' +
				", batchCode='" + batchCode + '\'' +
				", companyId=" + companyId +
				", count=" + count +
				", menuId=" + menuId +
				", searchBeginTime='" + searchBeginTime + '\'' +
				", searchEndTime='" + searchEndTime + '\'' +
				'}';
	}
}
