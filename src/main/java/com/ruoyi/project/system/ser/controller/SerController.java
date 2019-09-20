package com.ruoyi.project.system.ser.controller;

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
import com.ruoyi.project.system.ser.domain.Ser;
import com.ruoyi.project.system.ser.service.ISerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 服务器管理 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-06-03
 */
@Controller
@RequestMapping("/system/ser")
public class SerController extends BaseController
{
    private String prefix = "system/ser";
	
	@Autowired
	private ISerService serService;
	
	@RequiresPermissions("system:ser:view")
	@GetMapping()
	public String ser()
	{
	    return prefix + "/ser";
	}
	
	/**
	 * 查询服务器管理列表
	 */
	@RequiresPermissions("system:ser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Ser ser)
	{
		startPage();
        List<Ser> list = serService.selectSerList(ser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出服务器管理列表
	 */
	@RequiresPermissions("system:ser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Ser ser)
    {
    	List<Ser> list = serService.selectSerList(ser);
        ExcelUtil<Ser> util = new ExcelUtil<Ser>(Ser.class);
        return util.exportExcel(list, "ser");
    }
	
	/**
	 * 新增服务器管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存服务器管理
	 */
	@RequiresPermissions("system:ser:add")
	@Log(title = "服务器管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Ser ser)
	{		
		return toAjax(serService.insertSer(ser));
	}

	/**
	 * 修改服务器管理
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Ser ser = serService.selectSerById(id);
		mmap.put("ser", ser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存服务器管理
	 */
	@RequiresPermissions("system:ser:edit")
	@Log(title = "服务器管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Ser ser)
	{		
		return toAjax(serService.updateSer(ser));
	}
	
	/**
	 * 删除服务器管理
	 */
	@RequiresPermissions("system:ser:remove")
	@Log(title = "服务器管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(serService.deleteSerByIds(ids));
	}
	
}
