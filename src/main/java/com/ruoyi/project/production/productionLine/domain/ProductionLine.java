package com.ruoyi.project.production.productionLine.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.production.workstation.domain.Workstation;
import com.ruoyi.project.system.user.domain.User;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 生产线表 production_line
 * 
 * @author ruoyi
 * @date 2019-04-11
 */
public class ProductionLine extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 生产线名称 */
	@Excel(name = "生产线名称")
	private String lineName;
	/**  */
	private Integer deviceLiable;
	/** 责任人 */
	@Excel(name = "责任人1")
	private String deviceLiableName;
	/** 第二责任人 */
	private Integer deviceLiableTow;
	@Excel(name = "责任人2")
	private String deviceLiableTowName;
	@Excel(name = "手动")
	private Integer manual;//是否是手动 1、是 0、不是
	/** 备注信息 */
	@Excel(name = "备注信息")
	private String remark;
	/**  */
	private Integer defId;
	/**  */
	private Integer companyId;
	/** 所属公司 */
	@Excel(name = "所属公司")
	private String comName;
	/** 创建时间 */
	@Excel(name = "创建时间",width = 30,dateFormat = "yyyy-MM-dd HH:ss:mm")
	private Date createTime;
	/**  */
	private Integer create_by;
	/** 创建者 */
	@Excel(name = "创建者")
	private String createName;
	/** 产线IO口配置 */
	private Integer[] devIo;
	/** 自定义数据 */
	private String paramConfig;

	private List<String> paramArray;//自定义数据数组

	/** 作业指导书
	 * */
	private Integer sopId;
	/**产品编码配置
	 * */
	private String productCodes;

	private Integer edUser; // 工程部责任人
	private User edUserInfo;
	private Integer ipqcUser; // 品质管理人
	private User ipqcUserInfo; // 品质管理人
	private Integer meUser; // 机械工程师
	private User meUserInfo; // 机械工程师
	private Integer teUser; // 测试员工
	private User teUserInfo; // 测试员工

	/**
	 * app端交互参数
	 */
	private Integer uid; // app端在线用户的id
	private Integer mParentId; // app端菜单父id
	private String devCode; //app设备编号
	private String devType; //app设备类型

	private Integer  status;//状态

	private List<Workstation> workstations;//对应产线所以工位

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

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

    public Integer getEdUser() {
		return edUser;
	}

	public void setEdUser(Integer edUser) {
		this.edUser = edUser;
	}

	public Integer getIpqcUser() {
		return ipqcUser;
	}

	public void setIpqcUser(Integer ipqcUser) {
		this.ipqcUser = ipqcUser;
	}

	public Integer getMeUser() {
		return meUser;
	}

	public void setMeUser(Integer meUser) {
		this.meUser = meUser;
	}

	public Integer getTeUser() {
		return teUser;
	}

	public void setTeUser(Integer teUser) {
		this.teUser = teUser;
	}

	public Integer getManual() {
		return manual;
	}

	public void setManual(Integer manual) {
		this.manual = manual;
	}

	private int isSign;//是否标记警戒线 0 没有 大于0则是相关id编号

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setLineName(String lineName) 
	{
		this.lineName = lineName;
	}

	public String getLineName() 
	{
		return lineName;
	}
	public void setDeviceLiable(Integer deviceLiable) 
	{
		this.deviceLiable = deviceLiable;
	}

	public Integer getDeviceLiable() 
	{
		return deviceLiable;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setDefId(Integer defId) 
	{
		this.defId = defId;
	}

	public Integer getDefId() 
	{
		return defId;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public Integer getCreate_by() {
		return create_by;
	}

	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}

	public String getDeviceLiableName() {
		return deviceLiableName;
	}

	public void setDeviceLiableName(String deviceLiableName) {
		this.deviceLiableName = deviceLiableName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Integer[] getDevIo() {
		return devIo;
	}

	public void setDevIo(Integer[] devIo) {
		this.devIo = devIo;
	}

	public int getIsSign() {
		return isSign;
	}

	public void setIsSign(int isSign) {
		this.isSign = isSign;
	}


	public Integer getDeviceLiableTow() {
		return deviceLiableTow;
	}

	public void setDeviceLiableTow(Integer deviceLiableTow) {
		this.deviceLiableTow = deviceLiableTow;
	}

	public String getDeviceLiableTowName() {
		return deviceLiableTowName;
	}

	public void setDeviceLiableTowName(String deviceLiableTowName) {
		this.deviceLiableTowName = deviceLiableTowName;
	}

	public String getParamConfig() {
		return paramConfig;
	}

	public void setParamConfig(String paramConfig) {
		this.paramConfig = paramConfig;
	}

	public List<String> getParamArray() {
		return paramArray;
	}

	public void setParamArray(List<String> paramArray) {
		this.paramArray = paramArray;
	}

	public Integer getSopId() {
		return sopId;
	}

	public void setSopId(Integer sopId) {
		this.sopId = sopId;
	}

	public String getProductCodes() {
		return productCodes;
	}

	public void setProductCodes(String productCodes) {
		this.productCodes = productCodes;
	}

	public User getEdUserInfo() {
		return edUserInfo;
	}

	public void setEdUserInfo(User edUserInfo) {
		this.edUserInfo = edUserInfo;
	}

	public User getIpqcUserInfo() {
		return ipqcUserInfo;
	}

	public void setIpqcUserInfo(User ipqcUserInfo) {
		this.ipqcUserInfo = ipqcUserInfo;
	}

	public User getMeUserInfo() {
		return meUserInfo;
	}

	public void setMeUserInfo(User meUserInfo) {
		this.meUserInfo = meUserInfo;
	}

	public User getTeUserInfo() {
		return teUserInfo;
	}

	public void setTeUserInfo(User teUserInfo) {
		this.teUserInfo = teUserInfo;
	}

	public List<Workstation> getWorkstations() {
		return workstations;
	}

	public void setWorkstations(List<Workstation> workstations) {
		this.workstations = workstations;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductionLine{" +
				"id=" + id +
				", lineName='" + lineName + '\'' +
				", deviceLiable=" + deviceLiable +
				", deviceLiableName='" + deviceLiableName + '\'' +
				", deviceLiableTow=" + deviceLiableTow +
				", deviceLiableTowName='" + deviceLiableTowName + '\'' +
				", manual=" + manual +
				", remark='" + remark + '\'' +
				", defId=" + defId +
				", companyId=" + companyId +
				", comName='" + comName + '\'' +
				", createTime=" + createTime +
				", create_by=" + create_by +
				", createName='" + createName + '\'' +
				", devIo=" + Arrays.toString(devIo) +
				", paramConfig='" + paramConfig + '\'' +
				", paramArray=" + paramArray +
				", sopId=" + sopId +
				", productCodes='" + productCodes + '\'' +
				", isSign=" + isSign +
				'}';
	}
}
