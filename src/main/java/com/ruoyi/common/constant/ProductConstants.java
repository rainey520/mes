package com.ruoyi.common.constant;

/**
 * 产品常量信息
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.common.constant
 * @Author: Administrator
 * @Date: 2019/4/27 16:56
 * @Description //TODO
 * @Version: 1.0
 **/
public class ProductConstants {
    /**
     * 产品编码 是否唯一的返回结果 0
     * 0、唯一 ；1、存在记录不唯一
     */
    public final static String PRODUCT_CODE_UNIQUE = "0";
    /**
     * 产品编码 是否唯一的返回结果 1
     * 0、唯一 ；1、存在记录不唯一
     */
    public final static String PRODUCT_CODE_NOT_UNIQUE = "1";

    /**
     * 产品sign标记
     * 0、成品，1、半成品
     */
    public final static Integer TYPE_PRO = 0;
    public final static Integer TYPE_PARTS = 1;

    /**
     * 产品是否有ECN标记
     * 0、无ECN，1、有ECN
     */
    public final static Integer ECN_STATUS_NO = 0;
    public final static Integer ECN_STATUS_YES = 1;
}
