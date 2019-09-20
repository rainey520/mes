package com.ruoyi.project.device.devDeviceCounts.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 数据上报日志表 dev_data_log
 * 
 * @author zqm
 * @date 2019-04-12
 */
public class DevDataLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 查询id */
	private Integer lineId;
	/** 工单id */
	private Integer workId;
	/** 硬件id */
	private Integer devId;
	/** 工位id */
	private Integer ioId;
	/**  */
	private Integer ioOrder;
	/** 上报总数 */
	private Integer dataTotal;
	/**  */
	private Date createDate;
	/**  */
	private Date createTime;
	//每次上传前后数据差
	private Integer delData;
	/** 车间或者流水线上报日志标记 0、流水线，1、车间 **/
	private Integer scType;

	public Integer getScType() {
		return scType;
	}

	public void setScType(Integer scType) {
		this.scType = scType;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setLineId(Integer lineId) 
	{
		this.lineId = lineId;
	}

	public Integer getLineId() 
	{
		return lineId;
	}
	public void setWorkId(Integer workId) 
	{
		this.workId = workId;
	}

	public Integer getWorkId() 
	{
		return workId;
	}
	public void setDevId(Integer devId) 
	{
		this.devId = devId;
	}

	public Integer getDevId() 
	{
		return devId;
	}
	public void setIoId(Integer ioId) 
	{
		this.ioId = ioId;
	}

	public Integer getIoId() 
	{
		return ioId;
	}
	public void setIoOrder(Integer ioOrder) 
	{
		this.ioOrder = ioOrder;
	}

	public Integer getIoOrder() 
	{
		return ioOrder;
	}
	public void setDataTotal(Integer dataTotal) 
	{
		this.dataTotal = dataTotal;
	}

	public Integer getDataTotal() 
	{
		return dataTotal;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public Integer getDelData() {
		return delData;
	}

	public void setDelData(Integer delData) {
		this.delData = delData;
	}

	@Override
	public String toString() {
		return "DevDataLog{" +
				"id=" + id +
				", companyId=" + companyId +
				", lineId=" + lineId +
				", workId=" + workId +
				", devId=" + devId +
				", ioId=" + ioId +
				", ioOrder=" + ioOrder +
				", dataTotal=" + dataTotal +
				", createDate=" + createDate +
				", createTime=" + createTime +
				", delData=" + delData +
				", scType=" + scType +
				'}';
	}
}
