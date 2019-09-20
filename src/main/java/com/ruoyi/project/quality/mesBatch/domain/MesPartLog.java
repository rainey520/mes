package com.ruoyi.project.quality.mesBatch.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

/**
 * 半成品mes批次追踪记录表 tab_mes_part_log
 * 
 * @author sj
 * @date 2019-08-10
 */
public class MesPartLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 半成品mes批次追踪记录主键id */
	private Integer id;
	/** 对应工单编号 */
	private String workCode;
	/** 对应的主码编号 */
	private String mesCode;
	/** 半成品编码 */
	private String partCode;
	/** 关联编码 */
	private String materielCode;
	/** 批次信息 */
	private String batchCode;
	/** 创建时间 */
	private Date cTime;

	private List<MesPartLog> mesPartLogList;

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getMesCode() {
		return mesCode;
	}

	public void setMesCode(String mesCode) {
		this.mesCode = mesCode;
	}

	public String getMaterielCode() {
		return materielCode;
	}

	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public List<MesPartLog> getMesPartLogList() {
		return mesPartLogList;
	}

	public void setMesPartLogList(List<MesPartLog> mesPartLogList) {
		this.mesPartLogList = mesPartLogList;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", MesPartLog.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("workCode='" + workCode + "'")
				.add("mesCode='" + mesCode + "'")
				.add("materielCode='" + materielCode + "'")
				.add("batchCode='" + batchCode + "'")
				.add("cTime=" + cTime)
				.add("mesPartLogList=" + mesPartLogList)
				.toString();
	}
}
