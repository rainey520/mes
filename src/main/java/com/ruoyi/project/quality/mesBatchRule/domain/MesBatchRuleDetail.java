package com.ruoyi.project.quality.mesBatchRule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * MES配置规则明细表 tab_mes_batch_rule_detail
 * 
 * @author sj
 * @date 2019-08-09
 */
public class MesBatchRuleDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** MES规则明细id */
	private Integer id;
	/** MES规则主表id */
	private Integer ruleId;
	/** 配置规则明细类型(1、半成品，2、物料默认值) */
	private Integer dType;
	/** 半成品/物料的编码 */
	private String dCode;
	/** 用于判断半成品是否已经配置了MES规则(0、默认值未配置，1、已配置) */
	private Integer dTag;
	/** 规则排序(默认0无优先级别，1-6优先级别递减，1最高) */
	private Integer ruleOrder;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getdType() {
		return dType;
	}

	public void setdType(Integer dType) {
		this.dType = dType;
	}

	public String getdCode() {
		return dCode;
	}

	public void setdCode(String dCode) {
		this.dCode = dCode;
	}

	public Integer getdTag() {
		return dTag;
	}

	public void setdTag(Integer dTag) {
		this.dTag = dTag;
	}

	public Integer getRuleOrder() {
		return ruleOrder;
	}

	public void setRuleOrder(Integer ruleOrder) {
		this.ruleOrder = ruleOrder;
	}

	@Override
	public String toString() {
		return "MesBatchRuleDetail{" +
				"id=" + id +
				", ruleId=" + ruleId +
				", dType=" + dType +
				", dCode='" + dCode + '\'' +
				", dTag=" + dTag +
				", ruleOrder=" + ruleOrder +
				'}';
	}
}
