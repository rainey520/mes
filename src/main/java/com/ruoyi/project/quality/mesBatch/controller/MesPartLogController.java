package com.ruoyi.project.quality.mesBatch.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.quality.mesBatch.domain.MesPartLog;
import com.ruoyi.project.quality.mesBatch.service.IMesPartLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 半成品mes批次追踪记录 信息操作处理
 * 
 * @author sj
 * @date 2019-08-10
 */
@Controller
@RequestMapping("/mes/mesPartLog")
public class MesPartLogController extends BaseController
{
    private String prefix = "quality/mesPartLog";
	
	@Autowired
	private IMesPartLogService mesPartLogService;
	
	@RequiresPermissions("mes:mesPartLog:view")
	@GetMapping()
	public String mesPartLog()
	{
	    return prefix + "/mesPartLog";
	}
	
	/**
	 * 查询半成品mes批次追踪记录列表
	 */
	@RequiresPermissions("mes:mesPartLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MesPartLog mesPartLog)
	{
		startPage();
        List<MesPartLog> list = mesPartLogService.selectMesPartLogList(mesPartLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出半成品mes批次追踪记录列表
	 */
	@RequiresPermissions("mes:mesPartLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MesPartLog mesPartLog)
    {
    	List<MesPartLog> list = mesPartLogService.selectMesPartLogList(mesPartLog);
        ExcelUtil<MesPartLog> util = new ExcelUtil<MesPartLog>(MesPartLog.class);
        return util.exportExcel(list, "mesPartLog");
    }
	
	/**
	 * 新增半成品mes批次追踪记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存半成品mes批次追踪记录
	 */
	@RequiresPermissions("mes:mesBatch:add")
	@Log(title = "半成品mes批次追踪记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody MesPartLog mesPartLog)
	{
		return toAjax(mesPartLogService.insertMesPartLog(mesPartLog));
	}

	/**
	 * 修改半成品mes批次追踪记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MesPartLog mesPartLog = mesPartLogService.selectMesPartLogById(id);
		mmap.put("mesPartLog", mesPartLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存半成品mes批次追踪记录
	 */
	@RequiresPermissions("mes:mesPartLog:edit")
	@Log(title = "半成品mes批次追踪记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MesPartLog mesPartLog)
	{		
		return toAjax(mesPartLogService.updateMesPartLog(mesPartLog));
	}
	
	/**
	 * 删除半成品mes批次追踪记录
	 */
	@RequiresPermissions("mes:mesPartLog:remove")
	@Log(title = "半成品mes批次追踪记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mesPartLogService.deleteMesPartLogByIds(ids));
	}
	
}
