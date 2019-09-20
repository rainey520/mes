package com.ruoyi.project.product.importConfig.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 导入配置表 tab_import_config
 * 
 * @author sj
 * @date 2019-07-03
 */
public class ImportConfig extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 产品编码、半产品编码 物料编码  工单号*/
	private Integer con1;
	/** 产品名称/半产品名称  物料名称  订单号*/
	private Integer con2;
	/** 产品型号/半成品型号  物料型号  产线*/
	private Integer con3;
	/** 单价  生产数量*/
	private Integer price;
	/** 标准工时  编码*/
	private Integer con4;
	/** 备注信息  工价*/
	private Integer con5;
	/** 配置类型 0、产品配置 1、半成品配置 2、物料  3、工单OCR 图片解析*/
	private Integer cType;
	/** 操作时间 */
	private Date cTime;
	/** 单位 */
	private Integer unit;
	/**  */
	private Integer rowIndex;

	private int cSign;

	private String appId;

	private String apiKey;

	private String secretKey;

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

	public Integer getCon1() {
		return con1;
	}

	public void setCon1(Integer con1) {
		this.con1 = con1;
	}

	public Integer getCon2() {
		return con2;
	}

	public void setCon2(Integer con2) {
		this.con2 = con2;
	}

	public Integer getCon3() {
		return con3;
	}

	public void setCon3(Integer con3) {
		this.con3 = con3;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCon4() {
		return con4;
	}

	public void setCon4(Integer con4) {
		this.con4 = con4;
	}

	public Integer getCon5() {
		return con5;
	}

	public void setCon5(Integer con5) {
		this.con5 = con5;
	}

	public Integer getcType() {
		return cType;
	}

	public void setcType(Integer cType) {
		this.cType = cType;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getcSign() {
		return cSign;
	}

	public void setcSign(int cSign) {
		this.cSign = cSign;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public String toString() {
		return "ImportConfig{" +
				"id=" + id +
				", companyId=" + companyId +
				", con1=" + con1 +
				", con2=" + con2 +
				", con3=" + con3 +
				", price=" + price +
				", con4=" + con4 +
				", con5=" + con5 +
				", cType=" + cType +
				", cTime=" + cTime +
				", unit=" + unit +
				", rowIndex=" + rowIndex +
				'}';
	}
}
