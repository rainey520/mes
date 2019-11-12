package com.ruoyi.project.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Rainey
 * @Date: 2019/9/5 18:07
 * @Version: 1.0
 **/
public class Product implements Serializable {
    private static final long serialVersionUID = -5685594138009679846L;
    /** 产品id */
    private Integer proId;
    /** 产品编码 */
    private String productCode;
    /** 公司信息 */
    private Integer companyId;
    /** app端分页 当前记录起始索引 */
    private Integer pageNum;
    /** app端分页 每页显示记录数 */
    private Integer pageSize;


    /****************** 展会mes测试参数 *******************/
    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sTime;
    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date eTime;
    /** 总工单数量 */
    private Integer totalWorkNum;
    /** 总退货数量 */
    private Integer totalBackNum;
    /** 退换比 */
    private String rate;

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public Integer getTotalWorkNum() {
        return totalWorkNum;
    }

    public void setTotalWorkNum(Integer totalWorkNum) {
        this.totalWorkNum = totalWorkNum;
    }

    public Integer getTotalBackNum() {
        return totalBackNum;
    }

    public void setTotalBackNum(Integer totalBackNum) {
        this.totalBackNum = totalBackNum;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * app端设置请求分页数据
     */
    public void appStartPage()
    {
        if (StringUtils.isNotNull(getPageNum()) && StringUtils.isNotNull(getPageSize()))
        {
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "proId=" + proId +
                ", productCode='" + productCode + '\'' +
                ", companyId=" + companyId +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", sTime=" + sTime +
                ", eTime=" + eTime +
                ", totalWorkNum=" + totalWorkNum +
                ", totalBackNum=" + totalBackNum +
                ", rate='" + rate + '\'' +
                '}';
    }
}
