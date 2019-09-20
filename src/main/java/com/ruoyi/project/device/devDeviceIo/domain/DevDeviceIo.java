package com.ruoyi.project.device.devDeviceIo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 设备IO表 dev_device_io
 * 
 * @author ruoyi
 * @date 2019-02-26
 */
public class DevDeviceIo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 设备id */
	private Integer devId;
	/** IO名称 */
	private String ioname;
	/** IO对应值的位置 */
	private Integer ioposition;
	/** IO用途描述 */
	private String note;
	/** 创建时间 */
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
	public void setIoname(String ioname) 
	{
		this.ioname = ioname;
	}

	public String getIoname() 
	{
		return ioname;
	}
	public void setIoposition(Integer ioposition) 
	{
		this.ioposition = ioposition;
	}

	public Integer getIoposition() 
	{
		return ioposition;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
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
            .append("ioname", getIoname())
            .append("ioposition", getIoposition())
            .append("note", getNote())
            .append("createTime", getCreateTime())
            .toString();
    }
}
