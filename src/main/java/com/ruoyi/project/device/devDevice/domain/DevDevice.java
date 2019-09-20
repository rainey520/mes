package com.ruoyi.project.device.devDevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 设备表 dev_device
 * 
 * @author ruoyi
 * @date 2019-02-26
 */
public class DevDevice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** iemi编码 */
	private String iemi;
	/** 上报数据间隔时间秒 */
	private String uploadtime;
	/** 设备名称 */
	private String name;
	/** 设备描述 */
	private String note;
	/** 唯一编码 */
	private String code;
	/** 首次上报 */
	private Date firstTime;
	/** 最后一次上报 */
	private Date lastTime;
	/** 设备状态 */
	private Integer state;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
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
	public void setUploadtime(String uploadtime) 
	{
		this.uploadtime = uploadtime;
	}

	public String getUploadtime() 
	{
		return uploadtime;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setFirstTime(Date firstTime) 
	{
		this.firstTime = firstTime;
	}

	public Date getFirstTime() 
	{
		return firstTime;
	}
	public void setLastTime(Date lastTime) 
	{
		this.lastTime = lastTime;
	}

	public Date getLastTime() 
	{
		return lastTime;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("iemi", getIemi())
            .append("uploadtime", getUploadtime())
            .append("name", getName())
            .append("note", getNote())
            .append("code", getCode())
            .append("firstTime", getFirstTime())
            .append("lastTime", getLastTime())
            .append("state", getState())
            .toString();
    }
}
