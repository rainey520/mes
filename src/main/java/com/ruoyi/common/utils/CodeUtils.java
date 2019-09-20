package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生产各个单据的编号工具类型
 */
public class CodeUtils {

    /**
     * 获取工单号 GD+公司id属性
     * @return
     */
    public static String getWorkOrderCode(){
        return "GD" +getCode();
    }

    /**
     * 获取采购单单号
     * @return
     */
    public static String getPurchaseCode(){
        return "CG"+getCode()+getRandom();
    }

    /**
     * 自动生成库存内部管理单
     * @return
     */
    public static String getHandleStockCode(){
        return "KCCL"+getCode()+getRandom();
    }

    /**
     * 自动生成产品出库单号
     * @return
     */
    public static String getProOutStockCode(){
        return "CPCK"+getCode()+getRandom();
    }

    /**
     * 自动生成客户产品退货单
     * @return
     */
    public static String getProIntoStockCode(){
        return "KHTH"+getCode()+getRandom();
    }

    /**
     * 自动生成物料入库单号
     * @return
     */
    public static String getMatIntoStockCode(){
        return "WLRK"+getCode()+getRandom();
    }

    /**
     * 自动生成物料退货单号
     * @return
     */
    public static String getMatOutStockCode(){
        return "WLTH"+getCode()+getRandom();
    }

    /**
     * 自动生成生产入库单
     * @return
     */
    public static String getLineIntoStockCode(){
        return "SCRK"+getCode()+getRandom();
    }

    /**
     * 自动生成生产发料单
     * @return
     */
    public static String getLineOutStockCode(){
        return "SCFL"+getCode()+getRandom();
    }

    /**
     * 获取bom 单号
     * @return
     */
    public static String getBomCode(){
        return "BOM"+getCode()+getRandom();
    }
    /**
     * 获取单号后一段部分
     * @return
     */
    public static String getCode(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return  format.format(new Date());
    }

    /**
     * 获取四位随机数
     * @return
     */
    public static String getRandom(){
        return String.valueOf((int)((Math.random()*9+1)*100));
    }

    /**
     * 获取四位随机英文字母
     * @return 结果
     */
    public static String getRandomChar(){
        String randomChar = "";
        for (int i = 0; i < 6; i++) {
            // 得到随机字母
            char c = (char) ((Math.random() * 26) + 97);
            randomChar += (c + "");
        }
        return String.valueOf(randomChar);
    }

    /**
     * 获取MRP编号
     * @return 结果
     */
    public static String getMrpCode(){
        return "MRP"+getCode()+getRandom();
    }

    /**
     * 生产MES主码
     * @return 结果
     */
    public static String getMesCode(){
        return "MES" + getCode();
    }
}
