package com.ruoyi.common.feign.devList;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.device.devList.domain.DevList;
import com.ruoyi.project.device.devList.service.IDevListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.common.feign.devList
 * @Author: Administrator
 * @Date: 2019/6/12 10:46
 * @Description //TODO
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/cus/dev")
public class DevListApiController {

    @Autowired
    private IDevListService devListService;

    /**
     * 主服务器修改硬件
     */
    @RequestMapping("/edit")
    public AjaxResult edit(@RequestBody DevList devList, HttpServletRequest request){
        try {
            return AjaxResult.success("success",devListService.apiEdit(devList));
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }
}
