package com.ruoyi.project.production.workOrderChange.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 工单变更表 dev_work_order_change
 * 
 * @author zqm
 * @date 2019-05-16
 */
public class WorkOrderChange extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 工单id */
	private Integer orderId;
	/** 工单号 */
	private String orderCode;
	/** 产线id */
	private Integer lineId;
	/** 产线名称 */
	private String lineName;
	/** 第一责任人 */
	private String deviceLiable;
	/** 第二责任人 */
	private String deviceLiable2;
	/** 计划开始时间 */
	private Date productionStart;
	/** 计划结束时间 */
	private Date productionEnd;
	/** 生产数量 */
	private Integer productNumber;
	/** 创建者 */
	private String createPeople;
	/** 创建时间 */
	private Date createTime;

	private String remark;

	/** 工单变更状态 0、修改 1、合单 2、拆单  */
	private Integer cStatus;
	/** 用于标记 0、产线 1、车间  */
	private Integer sign;
	/** 工价 */
	private Float workPrice;
	/** 订单编号  */
	private String orderCodeInfo;

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
	public void setOrderId(Integer orderId) 
	{
		this.orderId = orderId;
	}

	public Integer getOrderId() 
	{
		return orderId;
	}
	public void setOrderCode(String orderCode) 
	{
		this.orderCode = orderCode;
	}

	public String getOrderCode() 
	{
		return orderCode;
	}
	public void setLineId(Integer lineId) 
	{
		this.lineId = lineId;
	}

	public Integer getLineId() 
	{
		return lineId;
	}
	public void setLineName(String lineName) 
	{
		this.lineName = lineName;
	}

	public String getLineName() 
	{
		return lineName;
	}
	public void setDeviceLiable(String deviceLiable) 
	{
		this.deviceLiable = deviceLiable;
	}

	public String getDeviceLiable() 
	{
		return deviceLiable;
	}
	public void setDeviceLiable2(String deviceLiable2) 
	{
		this.deviceLiable2 = deviceLiable2;
	}

	public String getDeviceLiable2() 
	{
		return deviceLiable2;
	}
	public void setProductionStart(Date productionStart) 
	{
		this.productionStart = productionStart;
	}

	public Date getProductionStart() 
	{
		return productionStart;
	}
	public void setProductionEnd(Date productionEnd) 
	{
		this.productionEnd = productionEnd;
	}

	public Date getProductionEnd() 
	{
		return productionEnd;
	}
	public void setProductNumber(Integer productNumber) 
	{
		this.productNumber = productNumber;
	}

	public Integer getProductNumber() 
	{
		return productNumber;
	}
	public void setCreatePeople(String createPeople) 
	{
		this.createPeople = createPeople;
	}

	public String getCreatePeople() 
	{
		return createPeople;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getcStatus() {
		return cStatus;
	}

	public void setcStatus(Integer cStatus) {
		this.cStatus = cStatus;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public Float getWorkPrice() {
		return workPrice;
	}

	public void setWorkPrice(Float workPrice) {
		this.workPrice = workPrice;
	}

	public String getOrderCodeInfo() {
		return orderCodeInfo;
	}

	public void setOrderCodeInfo(String orderCodeInfo) {
		this.orderCodeInfo = orderCodeInfo;
	}

	@Override
	public String toString() {
		return "WorkOrderChange{" +
				"id=" + id +
				", companyId=" + companyId +
				", orderId=" + orderId +
				", orderCode='" + orderCode + '\'' +
				", lineId=" + lineId +
				", lineName='" + lineName + '\'' +
				", deviceLiable='" + deviceLiable + '\'' +
				", deviceLiable2='" + deviceLiable2 + '\'' +
				", productionStart=" + productionStart +
				", productionEnd=" + productionEnd +
				", productNumber=" + productNumber +
				", createPeople='" + createPeople + '\'' +
				", createTime=" + createTime +
				", remark='" + remark + '\'' +
				", cStatus=" + cStatus +
				", sign=" + sign +
				'}';
	}
}
