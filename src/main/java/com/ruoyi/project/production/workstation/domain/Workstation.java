package com.ruoyi.project.production.workstation.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 工位配置表 tab_workstation
 * 
 * @author sj
 * @date 2019-06-13
 */
public class Workstation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 产线id */
	private Integer lineId;
	/** 工位名称 */
	private String wName;
	/** 指导书页数id */
	private Integer sopId;
	/** 计数器id */
	private Integer devId;
	/** 计数器硬件名称 */
	private String devName;
	/** 计数器编码 */
	private String devCode;
	/** 看板硬件id */
	private Integer cId;
	/** 看板硬件编码 */
	private String cCode;
	/** ECM 扫描枪硬件id */
	private Integer eId;
	/** EMC 扫码枪编码 */
	private String eCode;
	/** 计数器数据判断依据0、不是 1、是 */
	private Integer sign;
	/** 创建时间 */
	private Date cTime;
	/** 标记是否需要删除 */
	private Integer defId;
	/** 工位对应的激活码信息 */
	private String activeCode;
	private Integer resSign;//ASOP 更新标识

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
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

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getwName() {
		return wName;
	}

	public void setwName(String wName) {
		this.wName = wName;
	}

	public Integer getSopId() {
		return sopId;
	}

	public void setSopId(Integer sopId) {
		this.sopId = sopId;
	}

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public String geteCode() {
		return eCode;
	}

	public void seteCode(String eCode) {
		this.eCode = eCode;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Integer getDefId() {
		return defId;
	}

	public void setDefId(Integer defId) {
		this.defId = defId;
	}

	public Integer getResSign() {
		return resSign;
	}

	public void setResSign(Integer resSign) {
		this.resSign = resSign;
	}

	@Override
	public String toString() {
		return "Workstation{" +
				"id=" + id +
				", companyId=" + companyId +
				", lineId=" + lineId +
				", wName='" + wName + '\'' +
				", sopId=" + sopId +
				", devId=" + devId +
				", devCode='" + devCode + '\'' +
				", cId=" + cId +
				", cCode='" + cCode + '\'' +
				", eId=" + eId +
				", eCode='" + eCode + '\'' +
				", sign=" + sign +
				", cTime=" + cTime +
				", defId=" + defId +
				'}';
	}
}
