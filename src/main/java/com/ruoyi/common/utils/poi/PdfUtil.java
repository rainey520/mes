package com.ruoyi.common.utils.poi;

import com.itextpdf.text.BaseColor;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class PdfUtil {
    public static final BaseColor headerColor = new BaseColor(133, 206, 224);
    /**
     * 导出PDF 文件
     * @param temPath //模板路径
     * @param pdfName 导出文件名称
     * @param response 响应
     * @param map 数据
     */
    public  void  exportPdf(String temPath, String pdfName, ResourceLoader resourceLoader,
                                  HttpServletResponse response, HttpServletRequest request, Map<String, Object> map){
        try {

            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                //IE浏览器处理
                pdfName = java.net.URLEncoder.encode(pdfName, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                pdfName = new String(pdfName.getBytes("UTF-8"), "ISO-8859-1");
            }
            InputStream in = this.getClass().getResourceAsStream(temPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(in);
            JasperPrint print = JasperFillManager.fillReport(jasperReport,map,new JREmptyDataSource());
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\""+pdfName+".pdf\""));
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(print,out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String pdfName(){
        return String.valueOf(System.currentTimeMillis()+(int)((Math.random()*9+1)*10000));
    }

    /**
     * 判断对应的数据是否为null ，为null 返回 0 ，否则返回对应的值
     * @param val
     * @return
     */
    public static String nullValue(Object val){
        return val == null?"0":val.toString();
    }

    /**
     * 判断float 类型数是否为null ，为null 返回 0.0 ，否则返回对应的值
     * @param val
     * @return
     */
    public static String floatNullValue(Object val){
        return val == null?"0.0":val.toString();
    }

    public static String stringNullValue(Object val){
        if(val == null || "".equals(val)){
            return "0";
        }
        return val.toString();
    }

    /**
     * 判断 float 类型数据是否为空
     * @param val
     * @return
     */
    public static float floatNull(Float val){
        return val == null?0.0F:val;
    }

    /**
     * 判断 Integer 类型数据是否为空
     * @param val
     * @return
     */
    public static int IntegerNull(Integer val){
        return val == null?0:val;
    }
}
