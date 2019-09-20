package com.ruoyi.project.quality.mesBatch.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * MES 追溯数据封装
 * @Author: Rainey
 * @Date: 2019/8/12 11:53
 * @Version: 1.0
 **/
public class MesData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /********* 查询条件 ************/
    /** 批次号 */
    private String batchCode;

    /******************** 工单主表一级追溯信息相关 ***********************/
    /** 工单号 */
    private String workCodePro;
    /** 产线名称 */
    private String lineNamePro;
    /** 产品编码 */
    private String productCodePro;
    /** 产品名称 */
    private String productNamePro;
    /** 生产数量 */
    private Integer workNumber;
    /** 开始时间 */
    private Date startTimePro;
    /** 结束时间 */
    private Date endTimePro;
    /** 生产类型(0、默认值生产的是成品，1、生产半成品) */
    private Integer makeTypePro;
    /** 工单追溯主码列表 */
    private List<MesBatch> mesBatchList;

    /********************* MES追溯相关 ***********************/
    /** mes追溯标记 0、正向追溯，1、反向追溯 */
    private Integer mesSign;
    /** mes一级追溯批次号 */
    private String proMesCode;
    /** MES产品追溯明细，明细可能包含半成品和物料信息 */
    private List<MesBatchDetail> mesProList;

    /********************* MES反向追溯相关 ***********************/
    private List<MesData> mesDataList;


    public List<MesBatch> getMesBatchList() {
        return mesBatchList;
    }

    public void setMesBatchList(List<MesBatch> mesBatchList) {
        this.mesBatchList = mesBatchList;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public List<MesData> getMesDataList() {
        return mesDataList;
    }

    public void setMesDataList(List<MesData> mesDataList) {
        this.mesDataList = mesDataList;
    }

    public Integer getMakeTypePro() {
        return makeTypePro;
    }

    public void setMakeTypePro(Integer makeTypePro) {
        this.makeTypePro = makeTypePro;
    }

    public String getWorkCodePro() {
        return workCodePro;
    }

    public void setWorkCodePro(String workCodePro) {
        this.workCodePro = workCodePro;
    }

    public String getLineNamePro() {
        return lineNamePro;
    }

    public void setLineNamePro(String lineNamePro) {
        this.lineNamePro = lineNamePro;
    }

    public String getProductCodePro() {
        return productCodePro;
    }

    public void setProductCodePro(String productCodePro) {
        this.productCodePro = productCodePro;
    }

    public String getProductNamePro() {
        return productNamePro;
    }

    public void setProductNamePro(String productNamePro) {
        this.productNamePro = productNamePro;
    }

    public Date getStartTimePro() {
        return startTimePro;
    }

    public void setStartTimePro(Date startTimePro) {
        this.startTimePro = startTimePro;
    }

    public Date getEndTimePro() {
        return endTimePro;
    }

    public void setEndTimePro(Date endTimePro) {
        this.endTimePro = endTimePro;
    }

    public String getProMesCode() {
        return proMesCode;
    }

    public void setProMesCode(String proMesCode) {
        this.proMesCode = proMesCode;
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public Integer getMesSign() {
        return mesSign;
    }

    public void setMesSign(Integer mesSign) {
        this.mesSign = mesSign;
    }

    public List<MesBatchDetail> getMesProList() {
        return mesProList;
    }

    public void setMesProList(List<MesBatchDetail> mesProList) {
        this.mesProList = mesProList;
    }

    @Override
    public String toString() {
        return "MesData{" +
                "workCodePro='" + workCodePro + '\'' +
                ", lineNamePro='" + lineNamePro + '\'' +
                ", productCodePro='" + productCodePro + '\'' +
                ", productNamePro='" + productNamePro + '\'' +
                ", workNumber=" + workNumber +
                ", startTimePro=" + startTimePro +
                ", endTimePro=" + endTimePro +
                ", makeTypePro=" + makeTypePro +
                ", mesSign=" + mesSign +
                ", proMesCode='" + proMesCode + '\'' +
                ", mesProList=" + mesProList +
                ", mesDataList=" + mesDataList +
                '}';
    }
}
