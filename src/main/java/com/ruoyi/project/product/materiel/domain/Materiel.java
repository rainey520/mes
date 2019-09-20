package com.ruoyi.project.product.materiel.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料表 tab_materiel
 *
 * @author zqm
 * @date 2019-04-30
 */
public class Materiel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 物料主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 物料编码
     */
    @Excel(name = "物料编码",type = Excel.Type.ALL)
    private String materielCode;
    /**
     * 物料名称
     */
    @Excel(name = "物料名称",type = Excel.Type.ALL)
    private String materielName;
    /**
     * 对应供应商对应物料的供应商编码
     */
    private String supplierCode;
    /**
     * 物料型号
     */
    @Excel(name = "物料型号",type = Excel.Type.ALL)
    private String materielModel;
    /**
     * 单价(含税)
     */
    @Excel(name = "单价(含税)",type = Excel.Type.EXPORT)
    private BigDecimal price;
    /**
     * 导入价格
     */
    @Excel(name = "单价(含税)",type = Excel.Type.IMPORT)
    private Float priceImport;
    /** 物料单位 */
    @Excel(name = "单位",type = Excel.Type.IMPORT)
    private String unit;
    /**
     * 物料图片(最多五张)
     */
    private String materielImg;
    /**
     * 图片张数
     */
    private Integer imgSize;
    /**
     * 备注信息
     */
    @Excel(name = "备注信息",type = Excel.Type.ALL)
    private String remark;
    /**
     * 创建者
     */
    private Integer createId;
    /**
     * 创建者名称
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 物料是否上传过文件标记
     * 0、未上传，1、上传过
     */
    private Integer fileFlag;
    /**
     * 不良品数量
     */
    private Integer badNumber;

    /*******************  app交互参数 *******************/
    private Integer uid; // app在线用户id
    private Integer mParentId; // 菜单父id
    private String devCode; //设备编号
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getBadNumber() {
        return badNumber;
    }

    public void setBadNumber(Integer badNumber) {
        this.badNumber = badNumber;
    }

    public Integer getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(Integer fileFlag) {
        this.fileFlag = fileFlag;
    }

    public Float getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(Float priceImport) {
        this.priceImport = priceImport;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielModel(String materielModel) {
        this.materielModel = materielModel;
    }

    public String getMaterielModel() {
        return materielModel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMaterielImg(String materielImg) {
        this.materielImg = materielImg;
    }

    public String getMaterielImg() {
        return materielImg;
    }

    public void setImgSize(Integer imgSize) {
        this.imgSize = imgSize;
    }

    public Integer getImgSize() {
        return imgSize;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "Materiel{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", materielCode='" + materielCode + '\'' +
                ", materielName='" + materielName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", materielModel='" + materielModel + '\'' +
                ", price=" + price +
                ", priceImport=" + priceImport +
                ", unit='" + unit + '\'' +
                ", materielImg='" + materielImg + '\'' +
                ", imgSize=" + imgSize +
                ", remark='" + remark + '\'' +
                ", createId=" + createId +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", fileFlag=" + fileFlag +
                ", badNumber=" + badNumber +
                ", uid=" + uid +
                ", mParentId=" + mParentId +
                ", devCode='" + devCode + '\'' +
                ", devType='" + devType + '\'' +
                '}';
    }
}
