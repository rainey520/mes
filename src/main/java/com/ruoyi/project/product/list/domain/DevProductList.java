package com.ruoyi.project.product.list.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品管理表 dev_product_list
 * 
 * @author ruoyi
 * @date 2019-04-10
 */

public class DevProductList extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /**
     * 产品所属公司
     */
    private Integer companyId;
    /**
     * 公司名称
     */
    private String comName;
    /**
     * 产品编码
     */
    @Excel(name = "编码")
    private String productCode;
    /**
     * 产品型号
     */
    @Excel(name = "型号")
    private String productModel;
    /**
     * 产品名称
     */
    @Excel(name = "名称")
    private String productName;
    /**
     * 导入价格
     */
    @Excel(name = "价格", type = Excel.Type.IMPORT)
    private float priceImport;
    /**
     * 产品价格
     */
    @Excel(name = "价格", type = Excel.Type.EXPORT)
    private BigDecimal price;
    /**
     * 标准工时
     */
    @Excel(name = "标准工时[pcs/h]")
    private Integer standardHourYield;
    /**
     * 备注信息
     */
    @Excel(name = "备注信息")
    private String remark;
    /**  */
    private Date createTime;
    private int def_id;
    /**
     * 对应客户的产品编码信息
     */
    private String customerCode;
    /**
     * 创建者
     */
    @Excel(name = "创建者",type = Excel.Type.EXPORT)
    private String create_by;
    /**
     * 产品图片
     */
    private String productImg;
    /**
     * 图片数量
     */
    private Integer imgSize;

    /** ECN状态 0、默认为开启 1、已开启 */
    private Integer ecnStatus;
    /** ecn修改信息 */
    private String ecnText;

    private Integer ecnType;

    /**
     * 是否上传文件标记
     */
    private Integer fileFlag;
    /**
     * 库存良品数量
     */
    private Integer goodNumber;


    /** 标记 0、产品 1、半成品 */
    private Integer sign;
    /** 单位 */
    @Excel(name = "单位")
    private String unit;
    /** 规格id */
    private Integer ruleId;

    /*******************  app交互参数 *******************/
    private Integer uid; // app在线用户id
    private Integer mParentId; // 菜单父id
    private Integer menuList;
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

    public Integer getMenuList() {
        return menuList;
    }

    public void setMenuList(Integer menuList) {
        this.menuList = menuList;
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

    public Integer getEcnType() {
        return ecnType;
    }

    public void setEcnType(Integer ecnType) {
        this.ecnType = ecnType;
    }

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public Integer getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(Integer fileFlag) {
        this.fileFlag = fileFlag;
    }

    public float getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(float priceImport) {
        this.priceImport = priceImport;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getImgSize() {
        return imgSize;
    }

    public void setImgSize(Integer imgSize) {
        this.imgSize = imgSize;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setStandardHourYield(Integer standardHourYield) {
        this.standardHourYield = standardHourYield;
    }

    public Integer getStandardHourYield() {
        return standardHourYield;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public int getDef_id() {
        return def_id;
    }

    public void setDef_id(int def_id) {
        this.def_id = def_id;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Integer getEcnStatus() {
        return ecnStatus;
    }

    public void setEcnStatus(Integer ecnStatus) {
        this.ecnStatus = ecnStatus;
    }

    public String getEcnText() {
        return ecnText;
    }

    public void setEcnText(String ecnText) {
        this.ecnText = ecnText;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        return "DevProductList{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", comName='" + comName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productName='" + productName + '\'' +
                ", priceImport=" + priceImport +
                ", price=" + price +
                ", standardHourYield=" + standardHourYield +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", def_id=" + def_id +
                ", customerCode='" + customerCode + '\'' +
                ", create_by='" + create_by + '\'' +
                ", productImg='" + productImg + '\'' +
                ", imgSize=" + imgSize +
                ", ecnStatus=" + ecnStatus +
                ", ecnText='" + ecnText + '\'' +
                ", ecnType=" + ecnType +
                ", fileFlag=" + fileFlag +
                ", goodNumber=" + goodNumber +
                ", sign=" + sign +
                ", unit='" + unit + '\'' +
                ", ruleId=" + ruleId +
                '}';
    }
}
