package com.ruoyi.common.utils.spring;

import java.util.Random;

public class DevId {
    public static String getDevId(){
        Random ran = new Random();
        long a = ran.nextInt(99999999);
        if(String.valueOf(a).length() != 8){
            return  null;
        }
        return String.valueOf(a);
    }

    /**
     * 获取页面编号
     * @return
     */
    public static String getPageCode(){
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0;i<10;i++){
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
