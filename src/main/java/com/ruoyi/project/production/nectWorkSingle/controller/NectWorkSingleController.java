package com.ruoyi.project.production.nectWorkSingle.controller;

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
import com.ruoyi.project.production.nectWorkSingle.domain.NectWorkSingle;
import com.ruoyi.project.production.nectWorkSingle.service.INectWorkSingleService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工单单工位关联 信息操作处理
 * 
 * @author sj
 * @date 2019-07-03
 */
@Controller
@RequestMapping("/production/nectWorkSingle")
public class NectWorkSingleController extends BaseController
{
    private String prefix = "production/nectWorkSingle";
	
	@Autowired
	private INectWorkSingleService nectWorkSingleService;
	
	@RequiresPermissions("production:nectWorkSingle:view")
	@GetMapping()
	public String nectWorkSingle()
	{
	    return prefix + "/nectWorkSingle";
	}
	
	/**
	 * 查询工单单工位关联列表
	 */
	@RequiresPermissions("production:nectWorkSingle:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(NectWorkSingle nectWorkSingle)
	{
		startPage();
        List<NectWorkSingle> list = nectWorkSingleService.selectNectWorkSingleList(nectWorkSingle);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工单单工位关联列表
	 */
	@RequiresPermissions("production:nectWorkSingle:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NectWorkSingle nectWorkSingle)
    {
    	List<NectWorkSingle> list = nectWorkSingleService.selectNectWorkSingleList(nectWorkSingle);
        ExcelUtil<NectWorkSingle> util = new ExcelUtil<NectWorkSingle>(NectWorkSingle.class);
        return util.exportExcel(list, "nectWorkSingle");
    }
	
	/**
	 * 新增工单单工位关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工单单工位关联
	 */
	@RequiresPermissions("production:nectWorkSingle:add")
	@Log(title = "工单单工位关联", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(NectWorkSingle nectWorkSingle)
	{		
		return toAjax(nectWorkSingleService.insertNectWorkSingle(nectWorkSingle));
	}

	/**
	 * 修改工单单工位关联
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		NectWorkSingle nectWorkSingle = nectWorkSingleService.selectNectWorkSingleById(id);
		mmap.put("nectWorkSingle", nectWorkSingle);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工单单工位关联
	 */
	@RequiresPermissions("production:nectWorkSingle:edit")
	@Log(title = "工单单工位关联", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(NectWorkSingle nectWorkSingle)
	{		
		return toAjax(nectWorkSingleService.updateNectWorkSingle(nectWorkSingle));
	}
	
	/**
	 * 删除工单单工位关联
	 */
	@RequiresPermissions("production:nectWorkSingle:remove")
	@Log(title = "工单单工位关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(nectWorkSingleService.deleteNectWorkSingleByIds(ids));
	}
	
}
