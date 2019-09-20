package com.ruoyi.project.production.nectWorkSingle.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 工单单工位关联表 tab_nect_work_single
 * 
 * @author sj
 * @date 2019-07-03
 */
public class NectWorkSingle extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 工单单工位关联表主键id */
	private Integer id;
	/** 工单id */
	private Integer workId;
	/** 单工位主键id */
	private Integer swId;
	/** 配置时间 */
	private Date cTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setWorkId(Integer workId) 
	{
		this.workId = workId;
	}

	public Integer getWorkId() 
	{
		return workId;
	}
	public void setSwId(Integer swId) 
	{
		this.swId = swId;
	}

	public Integer getSwId() 
	{
		return swId;
	}
	public void setCTime(Date cTime) 
	{
		this.cTime = cTime;
	}

	public Date getCTime() 
	{
		return cTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workId", getWorkId())
            .append("swId", getSwId())
            .append("cTime", getCTime())
            .toString();
    }
}
