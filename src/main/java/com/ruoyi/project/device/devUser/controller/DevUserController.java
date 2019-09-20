package com.ruoyi.project.device.devUser.controller;

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
import com.ruoyi.project.device.devUser.domain.DevUser;
import com.ruoyi.project.device.devUser.service.IDevUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 用户 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Controller
@RequestMapping("/device/devUser")
public class DevUserController extends BaseController
{
    private String prefix = "device/devUser";

	@Autowired
	private IDevUserService devUserService;
	
	@RequiresPermissions("device:devUser:view")
	@GetMapping()
	public String devUser()
	{
	    return prefix + "/devUser";
	}
	
	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("device:devUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevUser devUser)
	{
		startPage();
        List<DevUser> list = devUserService.selectDevUserList(devUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@RequiresPermissions("device:devUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevUser devUser)
    {
    	List<DevUser> list = devUserService.selectDevUserList(devUser);
        ExcelUtil<DevUser> util = new ExcelUtil<DevUser>(DevUser.class);
        return util.exportExcel(list, "devUser");
    }
	
	/**
	 * 新增用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户
	 */
	@RequiresPermissions("device:devUser:add")
	@Log(title = "用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevUser devUser)
	{		
		return toAjax(devUserService.insertDevUser(devUser));
	}

	/**
	 * 修改用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DevUser devUser = devUserService.selectDevUserById(id);
		mmap.put("devUser", devUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户
	 */
	@RequiresPermissions("device:devUser:edit")
	@Log(title = "用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DevUser devUser)
	{		
		return toAjax(devUserService.updateDevUser(devUser));
	}
	
	/**
	 * 删除用户
	 */
	@RequiresPermissions("device:devUser:remove")
	@Log(title = "用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devUserService.deleteDevUserByIds(ids));
	}
	
}
