package com.ruoyi.project.system.ser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 服务器管理表 tab_ser
 * 
 * @author ruoyi
 * @date 2019-06-03
 */
public class Ser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 服务器名称 */
	private String sname;
	/** 服务器IP */
	private String sip;
	/** 服务器路径 */
	private String spath;
	/** 用户数量 */
	private Integer suserNum;
	/** 备注信息 */
	private String remark;
	/** 创建时间 */
	private Date createTime;
	/** 最大用户数量 */
	private Integer maxNum;
	/** API验证密码 */
	private String spwd;
	/** 服务器密码 */
	private String pwd;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setSname(String sname) 
	{
		this.sname = sname;
	}

	public String getSname() 
	{
		return sname;
	}
	public void setSip(String sip) 
	{
		this.sip = sip;
	}

	public String getSip() 
	{
		return sip;
	}
	public void setSpath(String spath) 
	{
		this.spath = spath;
	}

	public String getSpath() 
	{
		return spath;
	}
	public void setSuserNum(Integer suserNum) 
	{
		this.suserNum = suserNum;
	}

	public Integer getSuserNum() 
	{
		return suserNum;
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
	public void setMaxNum(Integer maxNum) 
	{
		this.maxNum = maxNum;
	}

	public Integer getMaxNum() 
	{
		return maxNum;
	}
	public void setSpwd(String spwd) 
	{
		this.spwd = spwd;
	}

	public String getSpwd() 
	{
		return spwd;
	}
	public void setPwd(String pwd) 
	{
		this.pwd = pwd;
	}

	public String getPwd() 
	{
		return pwd;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sname", getSname())
            .append("sip", getSip())
            .append("spath", getSpath())
            .append("suserNum", getSuserNum())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("maxNum", getMaxNum())
            .append("spwd", getSpwd())
            .append("pwd", getPwd())
            .toString();
    }
}
