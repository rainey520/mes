package com.ruoyi.project.device.devDeviceCounts.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * IO上报数据表 dev_device_counts
 * 
 * @author ruoyi
 * @date 2019-03-09
 */
public class DevDeviceCounts extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Long id;
	/** 设备ID */
	private String iemi;
	/** io口 */
	private String ioname;
	/** 当前总数 */
	private Integer counts;
	/** 上报时间 */
	private Date createTime;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setIemi(String iemi) 
	{
		this.iemi = iemi;
	}

	public String getIemi() 
	{
		return iemi;
	}
	public void setIoname(String ioname) 
	{
		this.ioname = ioname;
	}

	public String getIoname() 
	{
		return ioname;
	}
	public void setCounts(Integer counts) 
	{
		this.counts = counts;
	}

	public Integer getCounts() 
	{
		return counts;
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
            .append("iemi", getIemi())
            .append("ioname", getIoname())
            .append("counts", getCounts())
            .append("createTime", getCreateTime())
            .toString();
    }
}
