package com.ruoyi.project.device.api.form;

/**
 * 安卓硬件拉取工单信息配置
 */
public class ApiWorkForm {
    private Integer companyId;//公司id
    private String companyName;//公司名称
    private String companyLogo;//公司logo
    private Integer lineId;//产线id
    private String lineName;//产线名称
    private Integer workId;//工单id
    private String workCode;//工单编号
    private int workNumber;//工单数量
    private int actualNum; // 实际生产产量信息
    private Integer perNum; // 个人生产数量
    private Integer workorderStatus; // 工单生产状态
    private String productCode;//产品编码
    private String productName;//产品名称
    private Integer tag; // 车间或者流水线标记
    /** 车间责任人信息id和名称 **/
    private Integer userId;
    private String userName;
    private int op =0;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getPerNum() {
        return perNum;
    }

    public void setPerNum(Integer perNum) {
        this.perNum = perNum;
    }

    public Integer getWorkorderStatus() {
        return workorderStatus;
    }

    public void setWorkorderStatus(Integer workorderStatus) {
        this.workorderStatus = workorderStatus;
    }

    public int getActualNum() {
        return actualNum;
    }

    public void setActualNum(int actualNum) {
        this.actualNum = actualNum;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public int getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(int workNumber) {
        this.workNumber = workNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        if(op == 1 || op == 2) {
            this.op = op;
        }
    }
}
