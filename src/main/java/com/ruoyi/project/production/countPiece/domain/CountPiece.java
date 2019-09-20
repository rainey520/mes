package com.ruoyi.project.production.countPiece.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 计件管理数据表 tab_count_piece
 * 
 * @author sj
 * @date 2019-07-04
 */
public class CountPiece extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 计件管理主键id */
	private Integer cpId;
	/** 公司id */
	private Integer companyId;
	/** 计件记录归属人id */
	private Integer cpUserId;
	/** 计件记录归属人姓名 */
	@Excel(name = "姓名",type = Excel.Type.EXPORT)
	private String cpUserName;
	/** 计件日期 */
	@Excel(name = "计件日期",dateFormat = "yyyy-MM-dd",type = Excel.Type.EXPORT)
	private Date cpDate;
	/** 工单id */
	private Integer workId;
	/** 工单号 */
	private String workNumber;
	/** 工价 */
	private Float workPrice;
	/** 计件数量 */
	private Integer cpNumber;
	/** 不良品 */
	private Integer cpBadNumber;
	/** 统计计件数量 */
    @Excel(name = "计件总数",type = Excel.Type.EXPORT)
	private Integer sumCpNumber;
    /** 统计不良品 */
    @Excel(name = "总不良品数",type = Excel.Type.EXPORT)
    private Integer sumCpBadNumber;
	/** 总价((计件数量-不良品数量)*工价) */
	private Float totalPrice;
    @Excel(name = "总价",type = Excel.Type.EXPORT)
	/** 统计总价((计件数量-不良品数量)*工价) */
	private Float sumTotalPrice;
	/** 备注 */
	private String cpRemark;
	/** 最后修改时间 */
	private Date cpLastUpdate;
	/** 最后修改人id */
	private Integer cpLastId;
	/** 最后修改人姓名 */
	private String cpLastName;

	public String getCpLastName() {
		return cpLastName;
	}

	public void setCpLastName(String cpLastName) {
		this.cpLastName = cpLastName;
	}

	public String getCpUserName() {
		return cpUserName;
	}

	public void setCpUserName(String cpUserName) {
		this.cpUserName = cpUserName;
	}

	public Integer getSumCpNumber() {
		return sumCpNumber;
	}

	public void setSumCpNumber(Integer sumCpNumber) {
		this.sumCpNumber = sumCpNumber;
	}

	public Float getSumTotalPrice() {
		return sumTotalPrice;
	}

	public void setSumTotalPrice(Float sumTotalPrice) {
		this.sumTotalPrice = sumTotalPrice;
	}

	public Integer getSumCpBadNumber() {
		return sumCpBadNumber;
	}

	public void setSumCpBadNumber(Integer sumCpBadNumber) {
		this.sumCpBadNumber = sumCpBadNumber;
	}

	public void setCpId(Integer cpId)
	{
		this.cpId = cpId;
	}

	public Integer getCpId() 
	{
		return cpId;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setCpUserId(Integer cpUserId) 
	{
		this.cpUserId = cpUserId;
	}

	public Integer getCpUserId() 
	{
		return cpUserId;
	}
	public void setCpDate(Date cpDate) 
	{
		this.cpDate = cpDate;
	}

	public Date getCpDate() 
	{
		return cpDate;
	}
	public void setWorkId(Integer workId) 
	{
		this.workId = workId;
	}

	public Integer getWorkId() 
	{
		return workId;
	}
	public void setWorkNumber(String workNumber) 
	{
		this.workNumber = workNumber;
	}

	public String getWorkNumber() 
	{
		return workNumber;
	}
	public void setWorkPrice(Float workPrice) 
	{
		this.workPrice = workPrice;
	}

	public Float getWorkPrice() 
	{
		return workPrice;
	}
	public void setCpNumber(Integer cpNumber) 
	{
		this.cpNumber = cpNumber;
	}

	public Integer getCpNumber() 
	{
		return cpNumber;
	}
	public void setTotalPrice(Float totalPrice) 
	{
		this.totalPrice = totalPrice;
	}

	public Float getTotalPrice() 
	{
		return totalPrice;
	}
	public void setCpBadNumber(Integer cpBadNumber) 
	{
		this.cpBadNumber = cpBadNumber;
	}

	public Integer getCpBadNumber() 
	{
		return cpBadNumber;
	}
	public void setCpRemark(String cpRemark) 
	{
		this.cpRemark = cpRemark;
	}

	public String getCpRemark() 
	{
		return cpRemark;
	}
	public void setCpLastUpdate(Date cpLastUpdate) 
	{
		this.cpLastUpdate = cpLastUpdate;
	}

	public Date getCpLastUpdate() 
	{
		return cpLastUpdate;
	}
	public void setCpLastId(Integer cpLastId) 
	{
		this.cpLastId = cpLastId;
	}

	public Integer getCpLastId() 
	{
		return cpLastId;
	}

	@Override
	public String toString() {
		return "CountPiece{" +
				"cpId=" + cpId +
				", companyId=" + companyId +
				", cpUserId=" + cpUserId +
				", cpDate=" + cpDate +
				", workId=" + workId +
				", workNumber='" + workNumber + '\'' +
				", workPrice=" + workPrice +
				", cpNumber=" + cpNumber +
				", sumCpNumber=" + sumCpNumber +
				", totalPrice=" + totalPrice +
				", sumTotalPrice=" + sumTotalPrice +
				", cpBadNumber=" + cpBadNumber +
				", sumCpBadNumber=" + sumCpBadNumber +
				", cpRemark='" + cpRemark + '\'' +
				", cpLastUpdate=" + cpLastUpdate +
				", cpLastId=" + cpLastId +
				'}';
	}
}
