package com.ruoyi.project.device.devModel.controller;

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
import com.ruoyi.project.device.devModel.domain.DevModel;
import com.ruoyi.project.device.devModel.service.IDevModelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 硬件型号 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
@Controller
@RequestMapping("/device/devModel")
public class DevModelController extends BaseController
{
    private String prefix = "device/devModel";
	
	@Autowired
	private IDevModelService devModelService;
	
	@RequiresPermissions("device:devModel:view")
	@GetMapping()
	public String devModel()
	{
	    return prefix + "/devModel";
	}
	
	/**
	 * 查询硬件型号列表
	 */
	@RequiresPermissions("device:devModel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevModel devModel)
	{
		startPage();
        List<DevModel> list = devModelService.selectDevModelList(devModel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出硬件型号列表
	 */
	@RequiresPermissions("device:devModel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevModel devModel)
    {
    	List<DevModel> list = devModelService.selectDevModelList(devModel);
        ExcelUtil<DevModel> util = new ExcelUtil<DevModel>(DevModel.class);
        return util.exportExcel(list, "devModel");
    }
	
	/**
	 * 新增硬件型号
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存硬件型号
	 */
	@RequiresPermissions("device:devModel:add")
	@Log(title = "硬件型号", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevModel devModel)
	{		
		return toAjax(devModelService.insertDevModel(devModel));
	}

	/**
	 * 修改硬件型号
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DevModel devModel = devModelService.selectDevModelById(id);
		mmap.put("devModel", devModel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存硬件型号
	 */
	@RequiresPermissions("device:devModel:edit")
	@Log(title = "硬件型号", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DevModel devModel)
	{		
		return toAjax(devModelService.updateDevModel(devModel));
	}
	
	/**
	 * 删除硬件型号
	 */
	@RequiresPermissions("device:devModel:remove")
	@Log(title = "硬件型号", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devModelService.deleteDevModelByIds(ids));
	}
	
}
