package com.ruoyi.project.device.devCompany.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 公司表 dev_company
 *
 * @author ruoyi
 * @date 2019-04-08
 */
public class DevCompany extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 公司主键ID */
	@Excel(name = "公司编号")
	private Integer companyId;
	/** 公司名称 */
	@Excel(name = "公司名称")
	private String comName;
	/** 公司地址 */
	@Excel(name = "公司地址")
	private String comAddress;
	/** 创建时间 */
//	@Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
//	private Date createTime;
	/** 公司类型 */
	private Integer comType;
	/** 公司logo */
	@Excel(name = "公司logo")
	private String comLogo;
	/** 公司轮播图片 */
	private String comPicture;
	/** 公司所属行业 */
	private Integer industry;

	private String totalIso;//iso 文件总文件夹

	private int sign;//公司等级 0、普通用户  1 VIP 用户

	private long fileSize;//文件大小 b 为单位

	/** 看板登录账号密码 */
	private String loginNumber;
	private Integer loginPassword;

	public String getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(String loginNumber) {
		this.loginNumber = loginNumber;
	}

	public Integer getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(Integer loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public void setCompanyId(Integer companyId)
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId()
	{
		return companyId;
	}
	public void setComName(String comName)
	{
		this.comName = comName;
	}

	public String getComName()
	{
		return comName;
	}
//	public void setCreateTime(Date createTime)
//	{
//		this.createTime = createTime;
//	}
//
//	public Date getCreateTime()
//	{
//		return createTime;
//	}
	public void setComAddress(String comAddress)
	{
		this.comAddress = comAddress;
	}

	public String getComAddress()
	{
		return comAddress;
	}

	public Integer getComType() {
		return comType;
	}

	public void setComType(Integer comType) {
		this.comType = comType;
	}

	public void setComLogo(String comLogo)
	{
		this.comLogo = comLogo;
	}

	public String getComLogo()
	{
		return comLogo;
	}
	public void setComPicture(String comPicture)
	{
		this.comPicture = comPicture;
	}

	public String getComPicture()
	{
		return comPicture;
	}

	public String getTotalIso() {
		return totalIso;
	}

	public void setTotalIso(String totalIso) {
		this.totalIso = totalIso;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "DevCompany{" +
				"companyId=" + companyId +
				", comName='" + comName + '\'' +
				", comAddress='" + comAddress + '\'' +
				", comType=" + comType +
				", comLogo='" + comLogo + '\'' +
				", comPicture='" + comPicture + '\'' +
				", industry=" + industry +
				'}';
	}
}
