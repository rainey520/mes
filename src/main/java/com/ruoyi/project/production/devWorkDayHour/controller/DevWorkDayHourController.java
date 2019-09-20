package com.ruoyi.project.production.devWorkDayHour.controller;

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
import com.ruoyi.project.production.devWorkDayHour.domain.DevWorkDayHour;
import com.ruoyi.project.production.devWorkDayHour.service.IDevWorkDayHourService;
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
@RequestMapping("/production/devWorkDayHour")
public class DevWorkDayHourController extends BaseController
{
    private String prefix = "production/devWorkDayHour";
	
	@Autowired
	private IDevWorkDayHourService devWorkDayHourService;
	
	@RequiresPermissions("production:devWorkDayHour:view")
	@GetMapping()
	public String devWorkDayHour()
	{
	    return prefix + "/devWorkDayHour";
	}
	
	/**
	 * 查询工单各个IO口每天每小时数据列表
	 */
	@RequiresPermissions("production:devWorkDayHour:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevWorkDayHour devWorkDayHour)
	{
		startPage();
        List<DevWorkDayHour> list = devWorkDayHourService.selectDevWorkDayHourList(devWorkDayHour);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工单各个IO口每天每小时数据列表
	 */
	@RequiresPermissions("production:devWorkDayHour:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevWorkDayHour devWorkDayHour)
    {
    	List<DevWorkDayHour> list = devWorkDayHourService.selectDevWorkDayHourList(devWorkDayHour);
        ExcelUtil<DevWorkDayHour> util = new ExcelUtil<DevWorkDayHour>(DevWorkDayHour.class);
        return util.exportExcel(list, "devWorkDayHour");
    }
	
	/**
	 * 新增工单各个IO口每天每小时数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工单各个IO口每天每小时数据
	 */
	@RequiresPermissions("production:devWorkDayHour:add")
	@Log(title = "工单各个IO口每天每小时数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevWorkDayHour devWorkDayHour)
	{		
		return toAjax(devWorkDayHourService.insertDevWorkDayHour(devWorkDayHour));
	}

	/**
	 * 修改工单各个IO口每天每小时数据
	 */
	@GetMapping("/edit/{hourId}")
	public String edit(@PathVariable("hourId") Integer hourId, ModelMap mmap)
	{
		DevWorkDayHour devWorkDayHour = devWorkDayHourService.selectDevWorkDayHourById(hourId);
		mmap.put("devWorkDayHour", devWorkDayHour);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工单各个IO口每天每小时数据
	 */
	@RequiresPermissions("production:devWorkDayHour:edit")
	@Log(title = "工单各个IO口每天每小时数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DevWorkDayHour devWorkDayHour)
	{		
		return toAjax(devWorkDayHourService.updateDevWorkDayHour(devWorkDayHour));
	}
	
	/**
	 * 删除工单各个IO口每天每小时数据
	 */
	@RequiresPermissions("production:devWorkDayHour:remove")
	@Log(title = "工单各个IO口每天每小时数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devWorkDayHourService.deleteDevWorkDayHourByIds(ids));
	}
	
}
