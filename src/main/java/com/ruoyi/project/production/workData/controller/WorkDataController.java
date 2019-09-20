package com.ruoyi.project.production.workData.controller;

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
import com.ruoyi.project.production.workData.domain.WorkData;
import com.ruoyi.project.production.workData.service.IWorkDataService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工单数据 信息操作处理
 * 
 * @author zqm
 * @date 2019-04-16
 */
@Controller
@RequestMapping("/production/workData")
public class WorkDataController extends BaseController
{
    private String prefix = "production/workData";
	
	@Autowired
	private IWorkDataService workDataService;
	
	@RequiresPermissions("production:workData:view")
	@GetMapping()
	public String workData()
	{
	    return prefix + "/workData";
	}
	
	/**
	 * 查询工单数据列表
	 */
	@RequiresPermissions("production:workData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WorkData workData)
	{
		startPage();
        List<WorkData> list = workDataService.selectWorkDataList(workData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工单数据列表
	 */
	@RequiresPermissions("production:workData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WorkData workData)
    {
    	List<WorkData> list = workDataService.selectWorkDataList(workData);
        ExcelUtil<WorkData> util = new ExcelUtil<WorkData>(WorkData.class);
        return util.exportExcel(list, "workData");
    }
	
	/**
	 * 新增工单数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工单数据
	 */
	@RequiresPermissions("production:workData:add")
	@Log(title = "工单数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WorkData workData)
	{		
		return toAjax(workDataService.insertWorkData(workData));
	}

	/**
	 * 修改工单数据
	 */
	@GetMapping("/edit/{dataId}")
	public String edit(@PathVariable("dataId") Integer dataId, ModelMap mmap)
	{
		WorkData workData = workDataService.selectWorkDataById(dataId);
		mmap.put("workData", workData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工单数据
	 */
	@RequiresPermissions("production:workData:edit")
	@Log(title = "工单数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WorkData workData)
	{		
		return toAjax(workDataService.updateWorkData(workData));
	}
	
	/**
	 * 删除工单数据
	 */
	@RequiresPermissions("production:workData:remove")
	@Log(title = "工单数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(workDataService.deleteWorkDataByIds(ids));
	}
	
}
