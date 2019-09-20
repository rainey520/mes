package com.ruoyi.common.utils;


public class Tools {

    /**
     * return if str is empty
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
	
	/**
     * 添加CS校验位
     *
     * @param send_check 待校验指令
     * @return
     */
    public static String addCheck(String send_check) {
        int check_num = 0;
        String sub_result;
        for (int y = 0; y < send_check.length(); ) {
            sub_result = send_check.substring(y, y + 2);
            check_num = check_num + Integer.parseInt(sub_result, 16);
            y = y + 2;
        }

        String check = String.valueOf(Integer.toHexString(check_num % 256));
        if (check.length() == 1) {
            check = "0" + check;
        }
        return send_check + check.toUpperCase();
    }

    /**
     * 检查校验位
     *
     * @param read 带校验位的指令
     * @return true 通过 false 不通过
     */
    public static boolean checkRead(String read) {
        int totalNumRead = 0;
        String oneByte;
        for (int x = 0; x < read.length() - 2; ) {
            oneByte = read.substring(x, x + 2);
            totalNumRead = totalNumRead + Integer.parseInt(oneByte, 16);
            x = x + 2;
        }
        String check = String.valueOf(Integer.toHexString(totalNumRead % 256));
        if (check.length() == 1) {
            check = "0" + check;
        }
        return check.equalsIgnoreCase(read.substring(read.length() - 2));
    }
    
    public static void main(String[] agrs){
    	System.out.println(addCheck("553A00021234123412341234"));
    	System.out.println(addCheck("553A200112341234123412340000000100000002000000030000000400000005000000060000000700000008"));
    }
}
