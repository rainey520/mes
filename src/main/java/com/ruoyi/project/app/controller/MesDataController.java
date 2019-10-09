package com.ruoyi.project.app.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.quality.mesBatch.domain.MesBatch;
import com.ruoyi.project.quality.mesBatch.service.IMesBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * mes数据app端交互
 *
 * @Author: Rainey
 * @Date: 2019/9/24 8:57
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/mesData")
public class MesDataController {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MesDataController.class);

    @Autowired
    private IMesBatchService mesBatchService;

    /**
     * 查看mes数据列表
     */
    @RequestMapping("/list")
    public AjaxResult list(@RequestBody MesBatch mesBatch) {
        try {
            if (mesBatch != null) {
                mesBatch.appStartPage();
                return AjaxResult.success(mesBatchService.appSelectMesBatchList(mesBatch));
            }
        } catch (Exception e) {
            LOGGER.error("app端查看mes数据出现异常：" + e.getMessage());
        }
        return AjaxResult.error();
    }

    /**
     * app校验mes主码是否完整
     */
    @RequestMapping("/appCheckMesCode")
    public AjaxResult appCheckMesCode(@RequestBody MesBatch mesBatch) {
        if (mesBatch != null && StringUtils.isNotEmpty(mesBatch.getMesCode())) {
            int i = mesBatchService.checkMesCodeComplete(mesBatch.getMesCode());
            return i > 0 ? AjaxResult.success(mesBatch.getMesCode()) : AjaxResult.error();
        }
        return AjaxResult.error();
    }

    /**
     * 通过mes主码查询明细信息
     */
    @RequestMapping("/selectMesData")
    public Map<String, Object> appSelectMesData(@RequestBody MesBatch mesBatch){
        return mesBatchService.appSelectMesData(mesBatch);
    }
}
