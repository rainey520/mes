package com.ruoyi.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 数据转换工具
 */
public class DataTurn {
    private static final String TAG = DataTurn.class.getSimpleName();

    /*****************************************************************
     * 判断奇数或偶数，位运算，最后一位是1则为奇数，为0是偶数
     */
    static public int isOdd(int num) {
        return num & 0x1;
    }

    /*****************************************************************
     * Hex字符串转int
     */
    static public int HexToInt(String inHex) {
        return Integer.parseInt(inHex, 16);
    }

    /*****************************************************************
     * Hex字符串转long
     */
    static public long HexToLong(String inHex) {
        return Long.parseLong(inHex, 16);
    }

    /*****************************************************************
     * int转Hex字符串
     */
    static public String IntToHex(int in) {
        String hex = Integer.toHexString(in);
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        return hex.toUpperCase();
    }

    /*****************************************************************
     * int转Hex字符串
     * @param in 10进制
     * @param size 设置转换长度
     */
    static public String IntToHex(int in, int size) {
        String hex = IntToHex(in);
        return SetHexSize(hex, size);
    }

    /***************************************************************
     *  设置16进制字符串长度，前面补零
     * @param hex 16进制字符串
     * @param size 设置长度
     * @return 返回
     */
    static public String SetHexSize(String hex, int size) {
        int hexSize = hex.length();
        if (hexSize > size) {
            throw new IllegalArgumentException("size error，please set size > " + hexSize);
        }
        if (size % 2 != 0) {
            throw new IllegalArgumentException("size error，please set size to even!");
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size - hexSize; i++) {
            builder.append("0");
        }
        builder.append(hex);
        return builder.toString();
    }

    /*****************************************************************
     * Hex字符串转byte
     */
    static public byte HexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /*****************************************************************
     * 1字节转2个Hex字符
     */
    static public String Byte2Hex(Byte inByte) {
        return String.format("%02x", inByte).toUpperCase();
    }

    /*****************************************************************
     * 字节数组转hex字符串
     */
    static public String ByteArrToHex(byte[] inByteArr) {
        StringBuilder strBuilder = new StringBuilder();
        int j = inByteArr.length;
        for (int i = 0; i < j; i++) {
            strBuilder.append(Byte2Hex(inByteArr[i]));
        }
        return strBuilder.toString();
    }

    /*****************************************************************
     * Float转ByteArr
     */
    static public byte[] FloatToByteArr(float f) {
        //把float转换为byte[]
        int fbit = Float.floatToIntBits(f);
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }
        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }
        return dest;
    }

    /*****************************************************************
     * ByteArr转Float
     */
    static public float ByteArrToFloat(byte[] bytes) {
        int l;
        l = bytes[0];
        l &= 0xff;
        l |= ((long) bytes[1] << 8);
        l &= 0xffff;
        l |= ((long) bytes[2] << 16);
        l &= 0xffffff;
        l |= ((long) bytes[3] << 24);
        return Float.intBitsToFloat(l);
    }

    /*****************************************************************
     * HexStr转Float
     */
    static public float HexToFloat(String hex) {
        byte[] bytes = HexToByteArr(hex);
        return ByteArrToFloat(bytes);
    }

    /*****************************************************************
     * Float转HexStr
     */
    static public String FloatToHex(float f) {
        byte[] bytes = FloatToByteArr(f);
        return ByteArrToHex(bytes);
    }

    /*****************************************************************
     * Float转Hex字符串
     * @param f 10进制浮点
     * @param size 设置转换长度
     */
    static public String FloatToHex(float f, int size) {
        byte[] bytes = FloatToByteArr(f);
        String hex = ByteArrToHex(bytes);
        return SetHexSize(hex, size);
    }

    /*****************************************************************
     * 字节数组转hex字符串，可选长度
     */
    static public String ByteArrToHex(byte[] inByteArr, int offset, int byteCount) {
        StringBuilder strBuilder = new StringBuilder();
        int j = byteCount;
        for (int i = offset; i < j; i++) {
            strBuilder.append(Byte2Hex(inByteArr[i]));
        }
        return strBuilder.toString();
    }

    /*****************************************************************
     * hex字符串转字节数组
     */
    static public byte[] HexToByteArr(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (isOdd(hexlen) == 1) { //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else { //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = HexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /*****************************************************************
     * javaBean转Byte数组
     * @param obj javaBean
     */
    

    /*****************************************************************
     * 16进制字符串转字符串
     * @param hex 16进制字符串
     * @return 字符
     */
    static public String HexStrToString(String hex) {
        byte[] bytes = DataTurn.HexToByteArr(hex);
        return new String(bytes);
    }

    /*****************************************************************
     * Byte数组转字符串
     * @param bytes Byte数组
     * @return 字符
     */
    static public String ByteArrToString(byte[] bytes) {
        return new String(bytes);
    }

    /*****************************************************************
     * hex字符串转字符串（无需Unicode解码）
     */
    static public String HexToStr(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /*****************************************************************
     * 字符串转换Hex字符串(无需Unicode编码)
     */
    static public String StrToHex(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /******************************************************************
     * byte数组转int类型的对象
     * @param bytes bytes
     * @return int
     */
    static public int ByteArrToInt(byte[] bytes) {
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff);
    }

    /******************************************************************
     * int转byte数组
     * @param num int
     * @return byte
     */
    static public byte[] IntToByteArr(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((num >> 24) & 0xff);
        bytes[1] = (byte) ((num >> 16) & 0xff);
        bytes[2] = (byte) ((num >> 8) & 0xff);
        bytes[3] = (byte) (num & 0xff);
        return bytes;
    }
    
    public static void main(String[] agrs){
    	System.out.println(DataTurn.HexToInt("00000010"));
    	System.out.println(DataTurn.IntToHex(50,4));
    }
    
}
