package com.ruoyi.project.device.devDeviceCounts.controller;

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
import com.ruoyi.project.device.devDeviceCounts.domain.DevDeviceCounts;
import com.ruoyi.project.device.devDeviceCounts.service.IDevDeviceCountsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * IO上报数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-03-09
 */
@Controller
@RequestMapping("/device/devDeviceCounts")
public class DevDeviceCountsController extends BaseController
{
    private String prefix = "device/devDeviceCounts";
	
	@Autowired
	private IDevDeviceCountsService devDeviceCountsService;
	
	@RequiresPermissions("device:devDeviceCounts:view")
	@GetMapping()
	public String devDeviceCounts()
	{
	    return prefix + "/devDeviceCounts";
	}
	
	/**
	 * 查询IO上报数据列表
	 */
	@RequiresPermissions("device:devDeviceCounts:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevDeviceCounts devDeviceCounts)
	{
		startPage();
        List<DevDeviceCounts> list = devDeviceCountsService.selectDevDeviceCountsList(devDeviceCounts);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出IO上报数据列表
	 */
	@RequiresPermissions("device:devDeviceCounts:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevDeviceCounts devDeviceCounts)
    {
    	List<DevDeviceCounts> list = devDeviceCountsService.selectDevDeviceCountsList(devDeviceCounts);
        ExcelUtil<DevDeviceCounts> util = new ExcelUtil<DevDeviceCounts>(DevDeviceCounts.class);
        return util.exportExcel(list, "devDeviceCounts");
    }
	
	/**
	 * 新增IO上报数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存IO上报数据
	 */
	@RequiresPermissions("device:devDeviceCounts:add")
	@Log(title = "IO上报数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevDeviceCounts devDeviceCounts)
	{		
		return toAjax(devDeviceCountsService.insertDevDeviceCounts(devDeviceCounts));
	}

	/**
	 * 修改IO上报数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		DevDeviceCounts devDeviceCounts = devDeviceCountsService.selectDevDeviceCountsById(id);
		mmap.put("devDeviceCounts", devDeviceCounts);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存IO上报数据
	 */
	@RequiresPermissions("device:devDeviceCounts:edit")
	@Log(title = "IO上报数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DevDeviceCounts devDeviceCounts)
	{		
		return toAjax(devDeviceCountsService.updateDevDeviceCounts(devDeviceCounts));
	}
	
	/**
	 * 删除IO上报数据
	 */
	@RequiresPermissions("device:devDeviceCounts:remove")
	@Log(title = "IO上报数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devDeviceCountsService.deleteDevDeviceCountsByIds(ids));
	}
	
}
