package com.ruoyi.project.page.pageInfo.domain;

public class SopApi {
    private String lName;//产线名称
    private String wName;//工位名称
    private String wCode;//工单编号
    private int wStatus;//工单状态
    private int wNumber;//工单数量
    private String pCode;//产品编码
    private String pName;//产品名称
    private int isoId;//文件id
    private String isoPath;//文件地址
    private String path;//文件地址
    private String company;//公司名称

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getwCode() {
        return wCode;
    }

    public void setwCode(String wCode) {
        this.wCode = wCode;
    }

    public int getwStatus() {
        return wStatus;
    }

    public void setwStatus(int wStatus) {
        this.wStatus = wStatus;
    }

    public int getwNumber() {
        return wNumber;
    }

    public void setwNumber(int wNumber) {
        this.wNumber = wNumber;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getIsoId() {
        return isoId;
    }

    public void setIsoId(int isoId) {
        this.isoId = isoId;
    }

    public String getIsoPath() {
        return isoPath;
    }

    public void setIsoPath(String isoPath) {
        this.isoPath = isoPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
