package com.ruoyi.project.device.devUserCompany.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户公司表 dev_user_company
 * 
 * @author ruoyi
 * @date 2019-02-02
 */
public class DevUserCompany extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 公司主键ID */
	private Integer companyId;
	/** 用户主键ID */
	private Integer userId;

	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("companyId", getCompanyId())
            .append("userId", getUserId())
            .toString();
    }
}
