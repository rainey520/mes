package com.ruoyi.project.app.domain;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.StringUtils;

import java.io.Serializable;

/**
 * @Author: Rainey
 * @Date: 2019/9/5 18:07
 * @Version: 1.0
 **/
public class Product implements Serializable {
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
                '}';
    }
}
