package com.ruoyi.common.constant;

/**
 * mrp常量信息
 *
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.common.constant
 * @Author: Administrator
 * @Date: 2019/6/25 14:34
 * @Description //TODO
 * @Version: 1.0
 **/
public class MrpConstants {
    /**
     * 订单是否生成mrp记录 未生成 0 </br>
     * 0、未生成 ；1、已生成
     */
    public final static Integer ORDER_NOTO_MRP = 0;
    /**
     * 订单是否生成mrp记录 已生成 1 </br>
     * 0、未生成 ；1、已生成
     */
    public final static Integer ORDER_TO_MRP = 1;

    /**
     * MRP采购状态描述 需采购 0 </br>
     * 0、需采购 1、采购中、2、采购完成 3、不采购
     */
    public final static Integer MRP_MAT_NEEDCG = 0;
    /**
     * MRP采购状态描述 采购中 1 </br>
     * 0、需采购 1、采购中、2、采购完成 3、不采购
     */
    public final static Integer MRP_MAT_CGING = 1;
    /**
     * MRP采购状态描述 采购完成 2 </br>
     * 0、需采购 1、采购中、2、采购完成 3、不采购
     */
    public final static Integer MRP_MAT_CGFINISH = 2;
    /**
     * MRP采购状态描述 不采购 3 </br>
     * 0、需采购 1、采购中、2、采购完成 3、不采购
     */
    public final static Integer MRP_MAT_NOTCG = 3;

    /**
     * ERP库存操作类型
     * 0、物料入库，1、物料退货，2、产品出库，3、客户退货
     */
    public final static Integer MAT_INTOSTOCK = 0;
    public final static Integer MAT_OUTSTOCK = 1;
    public final static Integer PRO_OUTSTOCK = 2;
    public final static Integer PRO_INTOSTOCK = 3;

    /**
     * 订单产品明细的物料状态
     * 0、物料充足，1、物料不足
     */
    public final static Integer MAT_STATUS_ENOUGH = 0;
    public final static Integer MAT_STATUS_NOT_ENOUGH = 1;
}
