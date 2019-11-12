package com.ruoyi.project.quality.mesInput.controller;

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
import com.ruoyi.project.quality.mesInput.domain.MesInput;
import com.ruoyi.project.quality.mesInput.service.IMesInputService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * mes录入记录 信息操作处理
 * 
 * @author sj
 * @date 2019-11-12
 */
@Controller
@RequestMapping("/quality/mesInput")
public class MesInputController extends BaseController
{
    private String prefix = "quality/mesInput";
	
	@Autowired
	private IMesInputService mesInputService;
	
	@RequiresPermissions("quality:mesInput:view")
	@GetMapping()
	public String mesInput()
	{
	    return prefix + "/mesInput";
	}
	
	/**
	 * 查询mes录入记录列表
	 */
	@RequiresPermissions("quality:mesInput:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MesInput mesInput)
	{
		startPage();
        List<MesInput> list = mesInputService.selectMesInputList(mesInput);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出mes录入记录列表
	 */
	@RequiresPermissions("quality:mesInput:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MesInput mesInput)
    {
    	List<MesInput> list = mesInputService.selectMesInputList(mesInput);
        ExcelUtil<MesInput> util = new ExcelUtil<MesInput>(MesInput.class);
        return util.exportExcel(list, "mesInput");
    }
	
	/**
	 * 新增mes录入记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存mes录入记录
	 */
	@RequiresPermissions("quality:mesInput:add")
	@Log(title = "mes录入记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MesInput mesInput)
	{		
		return toAjax(mesInputService.insertMesInput(mesInput));
	}

	/**
	 * 修改mes录入记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MesInput mesInput = mesInputService.selectMesInputById(id);
		mmap.put("mesInput", mesInput);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存mes录入记录
	 */
	@RequiresPermissions("quality:mesInput:edit")
	@Log(title = "mes录入记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MesInput mesInput)
	{		
		return toAjax(mesInputService.updateMesInput(mesInput));
	}
	
	/**
	 * 删除mes录入记录
	 */
	@RequiresPermissions("quality:mesInput:remove")
	@Log(title = "mes录入记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mesInputService.deleteMesInputByIds(ids));
	}
	
}
