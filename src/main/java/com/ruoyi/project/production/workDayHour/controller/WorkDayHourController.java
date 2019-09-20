package com.ruoyi.project.production.workDayHour.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.production.workDayHour.domain.WorkDayHour;
import com.ruoyi.project.production.workDayHour.service.IWorkDayHourService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工单各个IO口每天每小时数据 信息操作处理
 *
 * @author zqm
 * @date 2019-04-16
 */
@Controller
@RequestMapping("/production/workDayHour")
public class WorkDayHourController extends BaseController {
    private String prefix = "production/workDayHour";

    @Autowired
    private IWorkDayHourService workDayHourService;

    @RequiresPermissions("production:workDayHour:view")
    @GetMapping()
    public String workDayHour() {
        return prefix + "/workDayHour";
    }

    /**
     * 查询工单各个IO口每天每小时数据列表
     */
    @RequiresPermissions("production:workDayHour:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WorkDayHour workDayHour) {
        startPage();
        List<WorkDayHour> list = workDayHourService.selectWorkDayHourList(workDayHour);
        return getDataTable(list);
    }


    /**
     * 导出工单各个IO口每天每小时数据列表
     */
    @RequiresPermissions("production:workDayHour:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WorkDayHour workDayHour) {
        List<WorkDayHour> list = workDayHourService.selectWorkDayHourList(workDayHour);
        ExcelUtil<WorkDayHour> util = new ExcelUtil<WorkDayHour>(WorkDayHour.class);
        return util.exportExcel(list, "workDayHour");
    }

    /**
     * 新增工单各个IO口每天每小时数据
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存工单各个IO口每天每小时数据
     */
    @RequiresPermissions("production:workDayHour:add")
    @Log(title = "工单各个IO口每天每小时数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WorkDayHour workDayHour) {
        return toAjax(workDayHourService.insertWorkDayHour(workDayHour));
    }

    /**
     * 修改工单各个IO口每天每小时数据
     */
    @GetMapping("/edit/{hourId}")
    public String edit(@PathVariable("hourId") Integer hourId, ModelMap mmap) {
        WorkDayHour workDayHour = workDayHourService.selectWorkDayHourById(hourId);
        mmap.put("workDayHour", workDayHour);
        return prefix + "/edit";
    }

    /**
     * 修改保存工单各个IO口每天每小时数据
     */
    @RequiresPermissions("production:workDayHour:edit")
    @Log(title = "工单各个IO口每天每小时数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WorkDayHour workDayHour) {
        return toAjax(workDayHourService.updateWorkDayHour(workDayHour));
    }

    /**
     * 删除工单各个IO口每天每小时数据
     */
    @RequiresPermissions("production:workDayHour:remove")
    @Log(title = "工单各个IO口每天每小时数据", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(workDayHourService.deleteWorkDayHourByIds(ids));
    }

}
