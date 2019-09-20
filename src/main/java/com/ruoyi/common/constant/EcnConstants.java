package com.ruoyi.common.constant;

/**
 * ECN常量信息
 * @Author: Rainey
 * @Date: 2019/7/26 9:51
 * @Version: 1.0
 **/
public class EcnConstants {

    /**
     * ECN保存类型
     * 1、产品ECN，2、工单ECN，3、半成品ECN，
     */
    public static final Integer SAVE_TYPE_PRO=1;
    public static final Integer SAVE_TYPE_WORK=2;
    public static final Integer SAVE_TYPE_PARTS=3;


    /**
     * ECN状态<br/>
     * 0、默认值待提交，1、提交待审核，2、审核待执行，3、执行中，4、执行完毕不显示，5、取消作废的ECN
     */
    public static final Integer STATUS_DTJ=0;
    public static final Integer STATUS_DSH=1;
    public static final Integer STATUS_NOTZX_=2;
    public static final Integer STATUS_ZXING=3;
    public static final Integer STATUS_FINISH=4;
    public static final Integer STATUS_CANCLE=5;
}
