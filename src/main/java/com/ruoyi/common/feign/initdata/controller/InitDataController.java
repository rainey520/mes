package com.ruoyi.common.feign.initdata.controller;

import com.ruoyi.common.feign.initdata.domain.InitData;
import com.ruoyi.common.feign.initdata.service.IInitDataService;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 初始用户公司信息
 */
@RestController
public class InitDataController {
    @Autowired
    private IInitDataService iInitDataService;
    /**
     * 初始化公司用户数据
     * @param initData 封装接收数据
     * @return
     */
    @RequestMapping("/init/data")
    public AjaxResult initData(@RequestBody InitData initData){
        try {
            iInitDataService.initData(initData);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
