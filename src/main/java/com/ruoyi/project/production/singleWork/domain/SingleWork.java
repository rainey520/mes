package com.ruoyi.project.production.singleWork.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 单工位数据表 tab_single_work
 * 
 * @author sj
 * @date 2019-07-03
 */
public class SingleWork extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 单工位主键id */
	private Integer id;
	/** 公司主键id */
	private Integer companyId;
	/** 车间名称 */
	private String workshopName;
	/** 设备主键id */
	private Integer imId;
	/** 设备名称 */
	private String imCode;
	/** 标记(0、车间 1、设备) */
	private Integer sign;
	/** 责任人1(如果是车间则是责任人，是设备则是员工) */
	private Integer liableOne;
	/** 责任人1名称 */
	private String liableOneName;
	/** 责任人2 */
	private Integer liableTwo;
	private String liableTwoName;
	/** 计数器硬件信息硬件id(默认是0、未配置) */
	private Integer devId;
	private String devCode;
	/** 看板硬件信息(默认是0、未配置)*/
	private Integer watchId;
	private String watchCode;
	/** 扫码枪硬件信息(默认是0、未配置)*/
	private Integer eId;
	private String eCode;
	/** 创建时间 */
	private Date cTime;
	/** 上一级id(父id) */
	private Integer parentId;
	/**
	 * 是否配置SOP标志(0、未配置，1、已配置)
	 */
	private Integer sopSign;

	/*******************  app交互参数 *******************/
	private Integer uid; // app在线用户id
	private Integer mParentId; // 菜单父id
	private String deviceCode; //设备编号
	private String devType; //设备类型

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getmParentId() {
		return mParentId;
	}

	public void setmParentId(Integer mParentId) {
		this.mParentId = mParentId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public Integer getSopSign() {
		return sopSign;
	}

	public void setSopSign(Integer sopSign) {
		this.sopSign = sopSign;
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

	public String getWorkshopName() {
		return workshopName;
	}

	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}

	public Integer getImId() {
		return imId;
	}

	public void setImId(Integer imId) {
		this.imId = imId;
	}

	public String getImCode() {
		return imCode;
	}

	public void setImCode(String imCode) {
		this.imCode = imCode;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public Integer getLiableOne() {
		return liableOne;
	}

	public void setLiableOne(Integer liableOne) {
		this.liableOne = liableOne;
	}

	public String getLiableOneName() {
		return liableOneName;
	}

	public void setLiableOneName(String liableOneName) {
		this.liableOneName = liableOneName;
	}

	public Integer getLiableTwo() {
		return liableTwo;
	}

	public void setLiableTwo(Integer liableTwo) {
		this.liableTwo = liableTwo;
	}

	public String getLiableTwoName() {
		return liableTwoName;
	}

	public void setLiableTwoName(String liableTwoName) {
		this.liableTwoName = liableTwoName;
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

	public Integer getWatchId() {
		return watchId;
	}

	public void setWatchId(Integer watchId) {
		this.watchId = watchId;
	}

	public String getWatchCode() {
		return watchCode;
	}

	public void setWatchCode(String watchCode) {
		this.watchCode = watchCode;
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

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "SingleWork{" +
				"id=" + id +
				", companyId=" + companyId +
				", workshopName='" + workshopName + '\'' +
				", imId=" + imId +
				", imCode='" + imCode + '\'' +
				", sign=" + sign +
				", liableOne=" + liableOne +
				", liableOneName='" + liableOneName + '\'' +
				", liableTwo=" + liableTwo +
				", liableTwoName='" + liableTwoName + '\'' +
				", devId=" + devId +
				", devCode='" + devCode + '\'' +
				", watchId=" + watchId +
				", watchCode='" + watchCode + '\'' +
				", eId=" + eId +
				", eCode='" + eCode + '\'' +
				", cTime=" + cTime +
				", parentId=" + parentId +
				", sopSign=" + sopSign +
				", uid=" + uid +
				", mParentId=" + mParentId +
				", deviceCode='" + deviceCode + '\'' +
				", devType='" + devType + '\'' +
				'}';
	}
}
