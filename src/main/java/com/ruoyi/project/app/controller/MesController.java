package com.ruoyi.project.app.controller;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.production.devWorkOrder.service.IDevWorkOrderService;
import com.ruoyi.project.quality.mesBatch.domain.MesBatch;
import com.ruoyi.project.quality.mesBatch.service.IMesBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * app端MES交互
 *
 * @Author: Rainey
 * @Date: 2019/9/20 14:38
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/mes")
public class MesController {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MesController.class);

    @Autowired
    private IDevWorkOrderService devWorkOrderService;

    @Autowired
    private IMesBatchService mesBatchService;

    /**
     * 仓库配置MES数据拉取
     */
    @RequestMapping("/appConfig")
    public AjaxResult appConfig(@RequestBody MesBatch mesBatch) {
        if (mesBatch != null && mesBatch.getWorkId() != null) {
            Map<String,Object> map = new HashMap<>(16);
            map.put("mesCode", CodeUtils.getMesCode());
            map.put("mesData",devWorkOrderService.selectWorkOrderMesByWId(mesBatch.getWorkId()));
            return AjaxResult.success(map);
        }
        return AjaxResult.error();
    }

    /**
     * 仓库配置MES数据保存
     */
    @RequestMapping("/appConfigSave")
    public AjaxResult appConfigSave(@RequestBody MesBatch mesBatch) {
        try {
            int i = mesBatchService.insertMesBatch(mesBatch);
            return i > 0 ? AjaxResult.success() : AjaxResult.error();
        } catch (BusinessException e) {
            LOGGER.error("app端仓库配置MES出现异常：" + e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 生产配置MES数据拉取
     */
    @RequestMapping("/appMesProduce")
    public AjaxResult appMesProduce(@RequestBody MesBatch mesBatch){
        if (mesBatch != null && mesBatch.getWorkId() != null) {
            return AjaxResult.success(devWorkOrderService.selectWorkOrderMesByWId(mesBatch.getWorkId()));
        }
        return AjaxResult.error();
    }

    /**
     * 生产配置MES数据保存
     */
    @RequestMapping("/appMesProduceSave")
    public AjaxResult appMesProduceSave(@RequestBody MesBatch mesBatch) {
        try {
            int i = mesBatchService.updateMesBatch(mesBatch);
            return i > 0 ? AjaxResult.success() : AjaxResult.error();
        } catch (BusinessException e) {
            LOGGER.error("app端生产配置MES出现异常：" + e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }
}
