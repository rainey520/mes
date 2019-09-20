package com.ruoyi.project.device.api.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.device.api.form.WorkDataForm;

import java.util.Map;

/**
 * 交互服务层
 */
public interface IInitDataManageService {
    /**
     * 根据硬件编码查询对应的工单信息
     * @param code 硬件编码
     * @return
     */
    Map<String,Object> workOrder(String code);

    /**
     * 各个硬件数据上报
     * @param data 上报数据
     * @return
     */
    Map<String,Object> workData(WorkDataForm data);

    /**
     * 异常上报
     * @param code 硬件编码
     * @return
     */
    Map<String,Object> workEx(String code);

    /**
     * 根据机器设备上扫描上传工单，对工单进行绑定开始操作
     * @param code 设备绑定的硬件编号
     * @param orderCode 工单号
     * @return
     */
    AjaxResult startWorkOrder(String code, String orderCode);
}
