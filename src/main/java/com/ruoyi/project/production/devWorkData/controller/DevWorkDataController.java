package com.ruoyi.project.production.devWorkData.controller;

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
import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import com.ruoyi.project.production.devWorkData.service.IDevWorkDataService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工单数据 信息操作处理
 * 
 * @author zqm
 * @date 2019-04-15
 */
@Controller
@RequestMapping("/production/devWorkData")
public class DevWorkDataController extends BaseController
{
    private String prefix = "production/devWorkData";
	
	@Autowired
	private IDevWorkDataService devWorkDataService;
	
	@RequiresPermissions("production:devWorkData:view")
	@GetMapping()
	public String devWorkData()
	{
	    return prefix + "/devWorkData";
	}
	
	/**
	 * 查询工单数据列表
	 */
	@RequiresPermissions("production:devWorkData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevWorkData devWorkData)
	{
		startPage();
        List<DevWorkData> list = devWorkDataService.selectDevWorkDataList(devWorkData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工单数据列表
	 */
	@RequiresPermissions("production:devWorkData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevWorkData devWorkData)
    {
    	List<DevWorkData> list = devWorkDataService.selectDevWorkDataList(devWorkData);
        ExcelUtil<DevWorkData> util = new ExcelUtil<DevWorkData>(DevWorkData.class);
        return util.exportExcel(list, "devWorkData");
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
	@RequiresPermissions("production:devWorkData:add")
	@Log(title = "工单数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevWorkData devWorkData)
	{		
		return toAjax(devWorkDataService.insertDevWorkData(devWorkData));
	}

	/**
	 * 修改工单数据
	 */
	@GetMapping("/edit/{dataId}")
	public String edit(@PathVariable("dataId") Integer dataId, ModelMap mmap)
	{
		DevWorkData devWorkData = devWorkDataService.selectDevWorkDataById(dataId);
		mmap.put("devWorkData", devWorkData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工单数据
	 */
	@RequiresPermissions("production:devWorkData:edit")
	@Log(title = "工单数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DevWorkData devWorkData)
	{		
		return toAjax(devWorkDataService.updateDevWorkData(devWorkData));
	}
	
	/**
	 * 删除工单数据
	 */
	@RequiresPermissions("production:devWorkData:remove")
	@Log(title = "工单数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devWorkDataService.deleteDevWorkDataByIds(ids));
	}
	
}
