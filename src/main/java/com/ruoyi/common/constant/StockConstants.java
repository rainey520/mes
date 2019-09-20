package com.ruoyi.common.constant;

/**
 * 库存所有的常量信息
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.common.constant
 * @Author: Administrator
 * @Date: 2019/4/30 10:43
 * @Description //TODO
 * @Version: 1.0
 **/
public class StockConstants {

    /**
     * 属于成品 0  <br>
     *     0、成品 <br>
     *     1、物料 <br>
     *     2、半成品，半成品由多个物料组成 <br>
     */
    public final static Integer DETAILS_TYPE_PRODUCT = 0;
    /**
     * 属于物料 1  <br>
     *     0、成品 <br>
     *     1、物料 <br>
     *     2、半成品，半成品由多个物料组成 <br>
     */
    public final static Integer DETAILS_TYPE_MATERIEL = 1;
    /**
     * 属于半成品 2  <br>
     *     0、成品 <br>
     *     1、物料 <br>
     *     2、半成品，半成品由多个物料组成 <br>
     */
    public final static Integer DETAILS_TYPE_PART = 2;


    /**
     * 产品、材料、半成品 出入库操作类型  0  <br>
     *     0、不良品出库 <br>
     *     1、不良品入库 <br>
     *     2、报废品出库 <br>
     *     3、报废品入库 <br>
     *     4、良品出库 <br>
     *     5、良品入库 <br>
     */
    public final static Integer BAD_OUTSTOCK = 0;
    /**
     * 产品、材料、半成品 出入库操作类型 = 1  <br>
     *     0、不良品出库 <br>
     *     1、不良品入库 <br>
     *     2、报废品出库 <br>
     *     3、报废品入库 <br>
     *     4、良品出库 <br>
     *     5、良品入库 <br>
     */
    public final static Integer BAD_INTOSTOCK = 1;
    /**
     * 产品、材料、半成品 出入库操作类型 = 2  <br>
     *     0、不良品出库 <br>
     *     1、不良品入库 <br>
     *     2、报废品清库 <br>
     *     3、报废品入库 <br>
     *     4、良品出库 <br>
     *     5、良品入库 <br>
     */
    public final static Integer SCRAP_OUTSTOCK = 2;
    /**
     * 产品、材料、半成品 出入库操作类型 = 3  <br>
     *     0、不良品出库 <br>
     *     1、不良品入库 <br>
     *     2、报废品出库 <br>
     *     3、报废品入库 <br>
     *     4、良品出库 <br>
     *     5、良品入库 <br>
     */
    public final static Integer SCRAP_INTOSTOCK = 3;
    /**
     * 产品、材料、半成品 出入库操作类型 = 4  <br>
     *     0、不良品出库 <br>
     *     1、不良品入库 <br>
     *     2、报废品出库 <br>
     *     3、报废品入库 <br>
     *     4、良品出库 <br>
     *     5、良品入库 <br>
     */
    public final static Integer GOOD_OUTSTOCK = 4;
    /**
     * 产品、材料、半成品 出入库操作类型 = 5  <br>
     *     0、不良品出库 <br>
     *     1、不良品入库 <br>
     *     2、报废品出库 <br>
     *     3、报废品入库 <br>
     *     4、良品出库 <br>
     *     5、良品入库 <br>
     */
    public final static Integer GOOD_INTOSTOCK = 5;
    /**
     * 临时仓库库存转入良品仓库
     */
    public final static Integer TEMPORARY_TO_GOOD = 0;

    /**
     * 产品、材料、半成品 操作状态 = 0 <br>
     *     0、不良品变成良品 <br>
     *     1、不良品变成报废品 <br>
     *     2、良品变成不良品 <br>
     *     3、良品变成报废品 <br>
     *     4、报废品清库 <br>
     */
    public final static Integer BAD_TO_GOOD = 0;
    /**
     * 产品、材料、半成品 操作状态 = 1 <br>
     *     0、不良品变成良品 <br>
     *     1、不良品变成报废品 <br>
     *     2、良品变成不良品 <br>
     *     3、良品变成报废品 <br>
     *     4、报废品清库 <br>
     */
    public final static Integer BAD_TO_SCRAP = 1;
    /**
     * 产品、材料、半成品 操作状态 = 2 <br>
     *     0、不良品变成良品 <br>
     *     1、不良品变成报废品 <br>
     *     2、良品变成不良品 <br>
     *     3、良品变成报废品 <br>
     *     4、报废品清库 <br>
     */
    public final static Integer GOOD_TO_BAD = 2;
    /**
     * 产品、材料、半成品 操作状态 = 3 <br>
     *     0、不良品变成良品 <br>
     *     1、不良品变成报废品 <br>
     *     2、良品变成不良品 <br>
     *     3、良品变成报废品 <br>
     *     4、报废品清库 <br>
     */
    public final static Integer GOOD_TO_SCRAP = 3;
    /**
     * 产品、材料、半成品 操作状态 = 4 <br>
     *     0、不良品变成良品 <br>
     *     1、不良品变成报废品 <br>
     *     2、良品变成不良品 <br>
     *     3、良品变成报废品 <br>
     *     4、报废品清库 <br>
     */
    public final static Integer CLEAN_SCRAP = 4;



    /**
     * 物料是否开启IQC检样 = 0 <br>
     *     0、默认值，不开启检样，直接入良品仓 <br>
     *     1、开启检样，直接入临时仓 <br>
     */
    public final static Integer IQC_NO = 0;
    /**
     * 物料是否开启IQC检样 = 1 <br>
     *     0、默认值，不开启检样，直接入良品仓 <br>
     *     1、开启检样，直接入临时仓 <br>
     */
    public final static Integer IQC_YES = 1;


    /**
     * 结款状态 未结款 0 <br>
     *     0、未结款 <br>
     *     1、已结款 <br>
     */
    public final static String PAYMENT_STATUS_NO = "0";
    /**
     * 结款状态 已结款 1 <br>
     *     0、未结款 <br>
     *     1、已结款 <br>
     */
    public final static String PAYMENT_STATUS_YES = "1";

    /**
     * 作废状态 未作废 0 <br>
     *     0、未作废 默认值 <br>
     *     1、已作废 <br>
     */
    public final static String DEL_FLAG_NO = "0";
    /**
     * 作废状态 已作废 1 <br>
     *     0、未作废 默认值 <br>
     *     1、已作废 <br>
     */
    public final static String DEL_FLAG_YES = "1";

    /**
     * 订单状态 待审核 1 <br>
     *     1、待审核 默认值 <br>
     *     2、待交付 <br>
     *     3、已交付 <br>
     *     4、已取消 <br>
     */
    public final static Integer ORDER_STATUS_ONE = 1;
    /**
     * 订单状态 待交付 2 <br>
     *     1、待审核 默认值 <br>
     *     2、待交付 <br>
     *     3、已交付 <br>
     *     4、已取消 <br>
     */
    public final static Integer ORDER_STATUS_TWO = 2;
    /**
     * 订单状态 已交付 3 <br>
     *     1、待审核 默认值 <br>
     *     2、待交付 <br>
     *     3、已交付 <br>
     *     4、已取消 <br>
     */
    public final static Integer ORDER_STATUS_THREE = 3;
    /**
     * 订单状态 已取消 4 <br>
     *     1、待审核 默认值 <br>
     *     2、待交付 <br>
     *     3、已交付 <br>
     *     4、已取消 <br>
     */
    public final static Integer ORDER_STATUS_FOUR = 4;

}
