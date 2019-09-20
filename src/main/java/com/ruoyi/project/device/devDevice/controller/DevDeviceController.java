package com.ruoyi.project.device.devDevice.controller;

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
import com.ruoyi.project.device.devDevice.domain.DevDevice;
import com.ruoyi.project.device.devDevice.service.IDevDeviceService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-02-26
 */
@Controller
@RequestMapping("/device/devDevice")
public class DevDeviceController extends BaseController
{
    private String prefix = "device/devDevice";
	
	@Autowired
	private IDevDeviceService devDeviceService;
	
	@RequiresPermissions("device:devDevice:view")
	@GetMapping()
	public String devDevice()
	{
	    return prefix + "/devDevice";
	}
	
	/**
	 * 查询设备列表
	 */
	@RequiresPermissions("device:devDevice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevDevice devDevice)
	{
		startPage();
        List<DevDevice> list = devDeviceService.selectDevDeviceList(devDevice);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出设备列表
	 */
	@RequiresPermissions("device:devDevice:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevDevice devDevice)
    {
    	List<DevDevice> list = devDeviceService.selectDevDeviceList(devDevice);
        ExcelUtil<DevDevice> util = new ExcelUtil<DevDevice>(DevDevice.class);
        return util.exportExcel(list, "devDevice");
    }
	
	/**
	 * 新增设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备
	 */
	@RequiresPermissions("device:devDevice:add")
	@Log(title = "设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevDevice devDevice)
	{		
		return toAjax(devDeviceService.insertDevDevice(devDevice));
	}

	/**
	 * 修改设备
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DevDevice devDevice = devDeviceService.selectDevDeviceById(id);
		mmap.put("devDevice", devDevice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备
	 */
	@RequiresPermissions("device:devDevice:edit")
	@Log(title = "设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DevDevice devDevice)
	{		
		return toAjax(devDeviceService.updateDevDevice(devDevice));
	}
	
	/**
	 * 删除设备
	 */
	@RequiresPermissions("device:devDevice:remove")
	@Log(title = "设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devDeviceService.deleteDevDeviceByIds(ids));
	}
	
}
