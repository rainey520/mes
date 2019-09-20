package com.ruoyi.project.quality.mesBatchRule.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.materiel.domain.Materiel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * MES批准追踪规则表 tab_mes_batch_rule
 * 
 * @author sj
 * @date 2019-07-22
 */
public class MesBatchRule extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 规则名称 */
	private String ruleName;
	/** 类型区分 */
	private Integer pType;
	/** 物料编码 */
	private String materiels;
	/** 已配置的物料信息 */
	private String[] materielList;
	/** 未配置的物料信息 */
	private List<Materiel> notMaterielList;
	/** 未配置的半成品信息 */
	private List<DevProductList> notPartlList;
	/** 创建时间 */
	private Date cTime;
	/** 修改时间 */
	private Date uTime;
	/**
	 * mes配置明细
	 */
	private List<MesBatchRuleDetail> mesBatchRuleDetails;
	/** 已配置的总数 */
	private Integer totalNum;

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<DevProductList> getNotPartlList() {
		return notPartlList;
	}

	public void setNotPartlList(List<DevProductList> notPartlList) {
		this.notPartlList = notPartlList;
	}

	public List<MesBatchRuleDetail> getMesBatchRuleDetails() {
		return mesBatchRuleDetails;
	}

	public void setMesBatchRuleDetails(List<MesBatchRuleDetail> mesBatchRuleDetails) {
		this.mesBatchRuleDetails = mesBatchRuleDetails;
	}

	public String[] getMaterielList() {
		return materielList;
	}

	public void setMaterielList(String[] materielList) {
		this.materielList = materielList;
	}

	public List<Materiel> getNotMaterielList() {
		return notMaterielList;
	}

	public void setNotMaterielList(List<Materiel> notMaterielList) {
		this.notMaterielList = notMaterielList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getpType() {
		return pType;
	}

	public void setpType(Integer pType) {
		this.pType = pType;
	}

	public String getMateriels() {
		return materiels;
	}

	public void setMateriels(String materiels) {
		this.materiels = materiels;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

    @Override
    public String toString() {
        return "MesBatchRule{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", ruleName='" + ruleName + '\'' +
                ", pType=" + pType +
                ", materiels='" + materiels + '\'' +
                ", materielList=" + Arrays.toString(materielList) +
                ", notMaterielList=" + notMaterielList +
                ", cTime=" + cTime +
                ", uTime=" + uTime +
                ", mesBatchRuleDetails=" + mesBatchRuleDetails +
                '}';
    }
}
