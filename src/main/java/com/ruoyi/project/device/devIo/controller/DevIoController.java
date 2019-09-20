package com.ruoyi.project.device.devIo.controller;

import java.util.Collections;
import java.util.List;

import com.ruoyi.project.device.devList.service.IDevListService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.device.devIo.service.IDevIoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 硬件IO口配置 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
@Controller
@RequestMapping("/device/devIo")
public class DevIoController extends BaseController
{
    private String prefix = "device/devIo";
	
	@Autowired
	private IDevIoService devIoService;

	@Autowired
	private IDevListService devListService;
	
	@RequiresPermissions("device:devIo:view")
	@GetMapping("/{devId}")
	public String devIo(@PathVariable("devId")int devId,ModelMap mmap)
	{
		mmap.put("devId",devId);
		mmap.put("dev",devListService.selectDevListAndIoById(devId));
	    return prefix + "/devIo";
	}

	@RequiresPermissions("device:devIo:view")
	@GetMapping("/two/{devId}")
	public String devIoTwo(@PathVariable("devId")int devId,ModelMap mmap)
	{
		mmap.put("devId",devId);
		mmap.put("dev",devListService.selectDevListAndIoById(devId));
		return prefix + "/devIo2";
	}
	
	/**
	 * 查询硬件IO口配置列表
	 */
	@RequiresPermissions("device:devIo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevIo devIo)
	{
		startPage();
        List<DevIo> list = devIoService.selectDevIoList(devIo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出硬件IO口配置列表
	 */
	@RequiresPermissions("device:devIo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevIo devIo)
    {
    	List<DevIo> list = devIoService.selectDevIoList(devIo);
        ExcelUtil<DevIo> util = new ExcelUtil<DevIo>(DevIo.class);
        return util.exportExcel(list, "devIo");
    }
	
	/**
	 * 新增硬件IO口配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存硬件IO口配置
	 */
	@RequiresPermissions("device:devIo:add")
	@Log(title = "硬件IO口配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DevIo devIo)
	{		
		return toAjax(devIoService.insertDevIo(devIo));
	}

	/**
	 * 修改硬件IO口配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DevIo devIo = devIoService.selectDevIoById(id);
		mmap.put("devIo", devIo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存硬件IO口配置
	 */
	@RequiresPermissions("device:devIo:edit")
	@Log(title = "硬件IO口配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@RequestBody List<DevIo> devIo)
	{
		return toAjax(devIoService.updateDevIo(devIo));
	}
	
	/**
	 * 删除硬件IO口配置
	 */
	@RequiresPermissions("device:devIo:remove")
	@Log(title = "硬件IO口配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(devIoService.deleteDevIoByIds(ids));
	}

	/**
	 * 根据对应的硬件编号查询对应的IO信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/byDevId")
	@ResponseBody
	public  TableDataInfo selectDevIoByDevId(int id,int line_id){
		return  getDataTable(devIoService.selectDevIoByDevId(id,line_id));
	}

	/**
	 * 查询对应产线已经配置的IO口信息
	 * @param lien_id 产线编号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/byLineId")
	public TableDataInfo selectLineIoByLineId(int lien_id){
		return getDataTable(devIoService.selectLineDevIO(lien_id));
	}

}
