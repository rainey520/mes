package com.ruoyi.project.monitor.job.task;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.production.devWorkDayHour.service.IDevWorkDayHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask extends BaseController {


    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法");
    }

    /**
     * 每小时定时任务，计算工单数据日志记录
     */
    public void calcDataLog() {

    }
}
