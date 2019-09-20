package com.ruoyi.common.constant;

/**
 * MES 常量信息
 * @Author: Rainey
 * @Date: 2019/7/22 15:29
 * @Version: 1.0
 **/
public class MesConstants {

    /**
     * MES追踪规格类型 0、产品，1、半成品,2、物料
     */
    public final static int MES_TYPE_PRO = 0;
    public final static int MES_TYPE_PARTS = 1;
    public final static int MES_TYPE_MATERIEL = 2;


    /**
     * MES追踪规格规则名称唯一返回结果
     */
    public final static String MES_RULENAME_UNIQUE = "0";
    public final static String MES_RULENAME_NOT_UNIQUE = "1";

    /**
     * MES是否配置了规则标记 0、未配置，1、已配置
     */
    public final static Integer MES_TAG_NO = 0;
    public final static Integer MES_TAG_YES = 1;

    /**
     * MES追溯正反向标记 0、正向追溯，1、反向追溯
     */
    public final static Integer MES_SIGN_GO = 0;
    public final static Integer MES_SIGN_BACK= 1;

    /**
     * MES生产是否扫描标记，0代表未扫描，1代表已扫描,2代表未配置
     */
    public final static String MES_PRO_SCAN_NO = "0";
    public final static String MES_PRO_SCAN_YES= "1";
    public final static String MES_PRO_SCAN_NOCONF= "2";
    /**
     * MES扫码标记默认值
     */
    public final static String MES_PRO_SCAN_INIT = "000000000000";

}
