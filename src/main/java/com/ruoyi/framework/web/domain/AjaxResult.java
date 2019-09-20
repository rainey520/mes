package com.ruoyi.framework.web.domain;

import java.util.HashMap;

/**
 * 操作消息提醒
 * 
 * @author ruoyi
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public AjaxResult()
    {
    }

    /**
     * 返回错误消息
     * 
     * @return 错误消息
     */
    public static AjaxResult error()
    {
        return error(1, "操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg)
    {
        return error(500, msg);
    }

    /**
     * 返回错误消息
     * 
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(int code, String msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     * 
     * @param msg 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 0);
        return json;
    }
    
    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功消息
     * 
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
    public static AjaxResult success(Object data)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", 0);
        json.put("data", data);
        return json;
    }

    public static AjaxResult success(String msg,Object data)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 0);
        json.put("data", data);
        return json;
    }

    public static AjaxResult login(String path,String token,int sign,String u){
        AjaxResult register = new AjaxResult();
        register.put("path", path);
        register.put("u", u);
        register.put("code", 0);
        register.put("token", token);
        register.put("sign", sign);
        return register;
    }

    /**
     * 用于API 接口交付
     * @param code 状态码
     * @param msg 提示信息
     * @param data 相关数据
     * @return
     */
    public static AjaxResult api(int code,String msg,Object data){
        AjaxResult result = new AjaxResult();
        result.put("code",code);
        result.put("msg",msg);
        result.put("data",data);
        return result;
    }
}
