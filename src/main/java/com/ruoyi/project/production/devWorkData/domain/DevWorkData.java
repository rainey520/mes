package com.ruoyi.project.production.devWorkData.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 工单数据表 dev_work_data
 * 
 * @author zqm
 * @date 2019-04-15
 */
public class DevWorkData extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 工单数据主键ID */
	private Integer dataId;
	/** 公司主键ID */
	private Integer companyId;
	/** 产线主键ID */
	private Integer lineId;
	/** 工单主键ID */
	private Integer workId;
	/** 硬件主键ID */
	private Integer devId;
	/** 硬件名称 */
	private String devName;
	/** 工位id */
	private Integer ioId;
	/** IO名称 */
	private String ioName;
	/** IO顺序 */
	private Integer ioOrder;
	/** 标记是否需要记录初始值 */
	private Integer dataSign;
	/** 初始值 */
	private Integer initialData;
	/** 累计产量 */
	private Integer cumulativeNum;
	/** 手动调整产量数据 */
	private Integer manualNum;
	/** 创建时间 */
	private Date createTime;

	private Integer ioSign;//标记对应端口总数统计
	/** 车间或者流水线上报日志标记 0、流水线，1、车间 **/
	private Integer scType;

	public Integer getScType() {
		return scType;
	}

	public void setScType(Integer scType) {
		this.scType = scType;
	}

	public void setDataId(Integer dataId)
	{
		this.dataId = dataId;
	}

	public Integer getDataId() 
	{
		return dataId;
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
	public void setDevName(String devName) 
	{
		this.devName = devName;
	}

	public String getDevName() 
	{
		return devName;
	}
	public void setIoId(Integer ioId) 
	{
		this.ioId = ioId;
	}

	public Integer getIoId() 
	{
		return ioId;
	}
	public void setIoName(String ioName) 
	{
		this.ioName = ioName;
	}

	public String getIoName() 
	{
		return ioName;
	}
	public void setIoOrder(Integer ioOrder) 
	{
		this.ioOrder = ioOrder;
	}

	public Integer getIoOrder() 
	{
		return ioOrder;
	}
	public void setDataSign(Integer dataSign) 
	{
		this.dataSign = dataSign;
	}

	public Integer getDataSign() 
	{
		return dataSign;
	}
	public void setInitialData(Integer initialData) 
	{
		this.initialData = initialData;
	}

	public Integer getInitialData() 
	{
		return initialData;
	}
	public void setCumulativeNum(Integer cumulativeNum) 
	{
		this.cumulativeNum = cumulativeNum;
	}

	public Integer getCumulativeNum() 
	{
		return cumulativeNum;
	}
	public void setManualNum(Integer manualNum) 
	{
		this.manualNum = manualNum;
	}

	public Integer getManualNum() 
	{
		return manualNum;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}


	public Integer getIoSign() {
		return ioSign;
	}

	public void setIoSign(Integer ioSign) {
		this.ioSign = ioSign;
	}

	@Override
	public String toString() {
		return "DevWorkData{" +
				"dataId=" + dataId +
				", companyId=" + companyId +
				", lineId=" + lineId +
				", workId=" + workId +
				", devId=" + devId +
				", devName='" + devName + '\'' +
				", ioId=" + ioId +
				", ioName='" + ioName + '\'' +
				", ioOrder=" + ioOrder +
				", dataSign=" + dataSign +
				", initialData=" + initialData +
				", cumulativeNum=" + cumulativeNum +
				", manualNum=" + manualNum +
				", createTime=" + createTime +
				", ioSign=" + ioSign +
				", scType=" + scType +
				'}';
	}
}
