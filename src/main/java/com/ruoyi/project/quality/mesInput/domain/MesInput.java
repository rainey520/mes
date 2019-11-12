package com.ruoyi.project.quality.mesInput.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * mes录入记录表 tab_mes_input
 * 
 * @author sj
 * @date 2019-11-12
 */
public class MesInput extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 录入主键 */
	private Integer id;
	/** 工单号 */
	private String workCode;
	/** mes批次信息 */
	private String mesInfo;
	/** 产品编码 */
	private String proCode;
	/** 工单时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date workTime;
	/** 工单数量 */
	private Integer workNum;
	/** 退货数量 */
	private Integer backNum;
	/** 百分比 */
	private String rate;

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setWorkCode(String workCode) 
	{
		this.workCode = workCode;
	}

	public String getWorkCode() 
	{
		return workCode;
	}
	public void setMesInfo(String mesInfo) 
	{
		this.mesInfo = mesInfo;
	}

	public String getMesInfo() 
	{
		return mesInfo;
	}
	public void setProCode(String proCode) 
	{
		this.proCode = proCode;
	}

	public String getProCode() 
	{
		return proCode;
	}
	public void setWorkTime(Date workTime) 
	{
		this.workTime = workTime;
	}

	public Date getWorkTime() 
	{
		return workTime;
	}
	public void setWorkNum(Integer workNum) 
	{
		this.workNum = workNum;
	}

	public Integer getWorkNum() 
	{
		return workNum;
	}
	public void setBackNum(Integer backNum) 
	{
		this.backNum = backNum;
	}

	public Integer getBackNum() 
	{
		return backNum;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workCode", getWorkCode())
            .append("mesInfo", getMesInfo())
            .append("proCode", getProCode())
            .append("workTime", getWorkTime())
            .append("workNum", getWorkNum())
            .append("backNum", getBackNum())
            .toString();
    }
}
