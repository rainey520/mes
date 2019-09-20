package com.ruoyi.project.device.devIo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 硬件IO口配置表 dev_io
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
public class DevIo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer devId;
	/**  */
	private Integer ioOrder;
	/**  */
	private String ioName;
	/**  */
	private Integer lineId;
	/**  */
	private Integer isSign;
	/**  */
	private String remark;
	/**  */
	private Date createTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDevId(Integer devId) 
	{
		this.devId = devId;
	}

	public Integer getDevId() 
	{
		return devId;
	}
	public void setIoOrder(Integer ioOrder) 
	{
		this.ioOrder = ioOrder;
	}

	public Integer getIoOrder() 
	{
		return ioOrder;
	}
	public void setIoName(String ioName) 
	{
		this.ioName = ioName;
	}

	public String getIoName() 
	{
		return ioName;
	}
	public void setLineId(Integer lineId) 
	{
		this.lineId = lineId;
	}

	public Integer getLineId() 
	{
		return lineId;
	}
	public void setIsSign(Integer isSign) 
	{
		this.isSign = isSign;
	}

	public Integer getIsSign() 
	{
		return isSign;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("devId", getDevId())
            .append("ioOrder", getIoOrder())
            .append("ioName", getIoName())
            .append("lineId", getLineId())
            .append("isSign", getIsSign())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
