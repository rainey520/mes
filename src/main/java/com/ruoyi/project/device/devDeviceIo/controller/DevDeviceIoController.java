package com.ruoyi.project.device.devDeviceIo.controller;

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
import com.ruoyi.project.device.devDeviceIo.domain.DevDeviceIo;
import com.ruoyi.project.device.devDeviceIo.service.IDevDeviceIoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备IO 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-02-26
 */
@Controller
@RequestMapping("/device/devDeviceIo")
public class DevDeviceIoController extends BaseController
{
    private String prefix = "device/devDeviceIo";
	
	@Autowired
	private IDevDeviceIoService devDeviceIoService;
	
	@RequiresPermissions("device:devDeviceIo:view")
	@GetMapping()
	public String devDeviceIo()
	{
	    return prefix + "/devDeviceIo";
	}
	
	/**
	 * 查询设备IO列表
	 */
	@RequiresPermissions("device:devDeviceIo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevDeviceIo devDeviceIo)
	{
		startPage();
        List<DevDeviceIo> list = devDeviceIoService.selectDevDeviceIoList(devDeviceIo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出设备IO列表
	 */
	@RequiresPermissions("device:devDeviceIo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevDeviceIo devDeviceIo)
    {
    	List<DevDeviceIo> list = devDeviceIoService.selectDevDeviceIoList(devDeviceIo);
        ExcelUtil<DevDeviceIo> util = new ExcelUtil<DevDeviceIo>(DevDeviceIo.class);
        return util.exportExcel(list, "devDeviceIo");
    }
	
	/**
	 * 新增设备IO
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备IO
	 */
	@RequiresPermissions("device:devDeviceIo:add")
	@Log(title = "设备IO", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevDeviceIo devDeviceIo)
	{		
		return toAjax(devDeviceIoService.insertDevDeviceIo(devDeviceIo));
	}

	/**
	 * 修改设备IO
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DevDeviceIo devDeviceIo = devDeviceIoService.selectDevDeviceIoById(id);
		mmap.put("devDeviceIo", devDeviceIo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备IO
	 */
	@RequiresPermissions("device:devDeviceIo:edit")
	@Log(title = "设备IO", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DevDeviceIo devDeviceIo)
	{		
		return toAjax(devDeviceIoService.updateDevDeviceIo(devDeviceIo));
	}
	
	/**
	 * 删除设备IO
	 */
	@RequiresPermissions("device:devDeviceIo:remove")
	@Log(title = "设备IO", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devDeviceIoService.deleteDevDeviceIoByIds(ids));
	}
	
}
