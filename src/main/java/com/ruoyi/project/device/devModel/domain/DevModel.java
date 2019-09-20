package com.ruoyi.project.device.devModel.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 硬件型号表 dev_model
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
public class DevModel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 型号编号 */
	@Excel(name = "型号编号")
	private Integer id;
	/** 型号 */
	@Excel(name = "型号")
	private String modelName;
	/**  */
	private Integer defId;
	/** 创建时间 */
	@Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
	private Date createTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setModelName(String modelName) 
	{
		this.modelName = modelName;
	}

	public String getModelName() 
	{
		return modelName;
	}
	public void setDefId(Integer defId) 
	{
		this.defId = defId;
	}

	public Integer getDefId() 
	{
		return defId;
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
            .append("modelName", getModelName())
            .append("defId", getDefId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
