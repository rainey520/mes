package com.ruoyi.project.app.domain;

public class Index {
    private int parentId;
    private String mesInfo;
    private String devCode;
    private String devType;

    public String getMesInfo() {
        return mesInfo;
    }

    public void setMesInfo(String mesInfo) {
        this.mesInfo = mesInfo;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
}
