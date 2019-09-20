package com.ruoyi.project.device.devUserCompany.controller;

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
import com.ruoyi.project.device.devUserCompany.domain.DevUserCompany;
import com.ruoyi.project.device.devUserCompany.service.IDevUserCompanyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 用户公司 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-02-02
 */
@Controller
@RequestMapping("/device/devUserCompany")
public class DevUserCompanyController extends BaseController
{
    private String prefix = "device/devUserCompany";
	
	@Autowired
	private IDevUserCompanyService devUserCompanyService;
	
	@RequiresPermissions("device:devUserCompany:view")
	@GetMapping()
	public String devUserCompany()
	{
	    return prefix + "/devUserCompany";
	}
	
	/**
	 * 查询用户公司列表
	 */
	@RequiresPermissions("device:devUserCompany:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevUserCompany devUserCompany)
	{
		startPage();
        List<DevUserCompany> list = devUserCompanyService.selectDevUserCompanyList(devUserCompany);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户公司列表
	 */
	@RequiresPermissions("device:devUserCompany:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevUserCompany devUserCompany)
    {
    	List<DevUserCompany> list = devUserCompanyService.selectDevUserCompanyList(devUserCompany);
        ExcelUtil<DevUserCompany> util = new ExcelUtil<DevUserCompany>(DevUserCompany.class);
        return util.exportExcel(list, "devUserCompany");
    }
	
	/**
	 * 新增用户公司
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户公司
	 */
	@RequiresPermissions("device:devUserCompany:add")
	@Log(title = "用户公司", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevUserCompany devUserCompany)
	{		
		return toAjax(devUserCompanyService.insertDevUserCompany(devUserCompany));
	}

	/**
	 * 修改用户公司
	 */
	@GetMapping("/edit/{companyId}")
	public String edit(@PathVariable("companyId") Integer companyId, ModelMap mmap)
	{
		DevUserCompany devUserCompany = devUserCompanyService.selectDevUserCompanyById(companyId);
		mmap.put("devUserCompany", devUserCompany);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户公司
	 */
	@RequiresPermissions("device:devUserCompany:edit")
	@Log(title = "用户公司", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DevUserCompany devUserCompany)
	{		
		return toAjax(devUserCompanyService.updateDevUserCompany(devUserCompany));
	}
	
	/**
	 * 删除用户公司
	 */
	@RequiresPermissions("device:devUserCompany:remove")
	@Log(title = "用户公司", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devUserCompanyService.deleteDevUserCompanyByIds(ids));
	}
	
}
