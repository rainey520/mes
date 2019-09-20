package com.ruoyi.common.constant;

/**
 * 工单常量信息
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.common.constant
 * @Author: Administrator
 * @Date: 2019/4/13 16:27
 * @Description //TODO
 * @Version: 1.0
 **/
public class WorkConstants {
    /**
     * 设置工单生产状态未进行为0<br>
     * 0:为默认值，新工单未开始,<br>
     * 1:处于进行状态的工单,<br>
     * 2:已经完成的工单
     */
    public static final Integer WORK_STATUS_NOSTART = 0;
    /**
     * 设置工单生产状态进行中为1<br>
     * 0:为默认值，新工单未开始,<br>
     * 1:处于进行状态的工单,<br>
     * 2:已经完成的工单
     */
    public static final Integer WORK_STATUS_STARTING = 1;
    /**
     * 设置工单生产状态已经完成为2<br>
     * 0:为默认值，新工单未开始,<br>
     * 1:处于进行状态的工单,<br>
     * 2:已经完成的工单
     */
    public static final Integer WORK_STATUS_END = 2;
    /**
     * 设置工单操作状态为0<br>
     * 0:为默认值，工单未进行未开始，页面显示开始按钮,<br>
     * 1:工单正在进行中，页面显示暂停按钮,<br>
     * 2:工单正在进行中，不过属于暂停状态，页面显示开始按钮 <br>
     * 3:工单已经结束
     */
    public static final Integer OPERATION_STATUS_NOSTART = 0;
    /**
     * 设置工单操作状态为1<br>
     * 0:为默认值，工单未进行未开始，页面显示开始按钮,<br>
     * 1:工单正在进行中，页面显示暂停按钮,<br>
     * 2:工单正在进行中，不过属于暂停状态，页面显示开始按钮 <br>
     * 3:工单已经结束
     */
    public static final Integer OPERATION_STATUS_STARTING = 1;
    /**
     * 设置工单操作状态为2<br>
     * 0:为默认值，工单未进行未开始，页面显示开始按钮,<br>
     * 1:工单正在进行中，页面显示暂停按钮,<br>
     * 2:工单正在进行中，不过属于暂停状态，页面显示开始按钮 <br>
     * 3:工单已经结束
     */
    public static final Integer OPERATION_STATUS_PAUSE = 2;
    /**
     * 设置工单操作状态为3<br>
     * 0:为默认值，工单未进行未开始，页面显示开始按钮,<br>
     * 1:工单正在进行中，页面显示暂停按钮,<br>
     * 2:工单正在进行中，不过属于暂停状态，页面显示开始按钮 <br>
     * 3:工单已经结束
     */
    public static final Integer OPERATION_STATUS_FINISH = 3;

    /**
     * 工单是否需要初始化数据 <br>
     * 0、否 1、是，当第一次点击开始按钮时需要对工单数据和每天没小时数据进行初始化话，
     */
    public static final Integer WORK_INIT_DATA_NO = 0;

    /**
     * 工单是否需要初始化数据 <br>
     * 0、否 1、是，当第一次点击开始按钮时需要对工单数据和每天没小时数据进行初始化话，
     */
    public static final Integer WORK_INIT_DATA_YES = 1;

    /**
     * 工单标记是否确认数据 <br>
     * 0、否 1、是，当值为1时，则不能修改数据，也不能删除数据
     */
    public static final Integer WORK_SIGN_NO = 0;
    /**
     * 工单标记是否确认数据 <br>
     * 0、否 1、是，当值为1时，则不能修改数据，也不能删除数据
     */
    public static final Integer WORK_SIGN_YES = 1;

    /**
     * 工单异常处理状态为0 <br>
     * 0、待处理 1、正在处理 2、处理完成
     */
    public static final Integer WORKEXCE_STATUT_NOHANDLE = 0;
    /**
     * 工单异常处理状态为1 <br>
     * 0、待处理 1、正在处理 2、处理完成
     */
    public static final Integer WORKEXCE_STATUT_HANDLE = 1;
    /**
     * 工单异常处理状态为2 <br>
     * 0、待处理 1、正在处理 2、处理完成
     */
    public static final Integer WORKEXCE_STATUT_FINISH = 2;

    /**
     * 工单号 是否唯一的返回结果
     */
    public final static String WORKERORDER_NUMBER_UNIQUE = "0";
    public final static String WORKERORDER_NUMBER_NOT_UNIQUE = "1";

    /**
     * 单工位车间名称是否唯一的返回结果
     */
    public final static String WORKHOUSE_NAME_UNIQUE = "0";
    public final static String WORKHOUSE_NAME_NOT_UNIQUE = "1";

    /** 用于标记是 0、产线  1、车间 */
    public final static int SING_LINE =0;
    public final static int SING_SINGLE =1;

    /**
     * 流水线名称是否唯一的返回结果
     */
    public final static String LINE_NAME_UNIQUE = "0";
    public final static String LINE_NAME_NOT_UNIQUE = "1";

    /** 开始暂停计数标记  0、需要计数，1、不需要计数 */
    public final static Integer PB_SIGN_YES =0;
    public final static Integer PB_SIGN_NO =1;

    /**
     * 工单异常类型 是否唯一的返回结果
     */
    public final static String EXC_TYPE_NAME_UNIQUE = "0";
    public final static String EXC_TYPE_NAME_NOT_UNIQUE = "1";

    /**
     * 工单是否有ECN标记
     * 0、无ECN，1、有ECN
     */
    public final static Integer ECN_STATUS_NO = 0;
    public final static Integer ECN_STATUS_YES = 1;

}
