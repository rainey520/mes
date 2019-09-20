package com.ruoyi.project.production.workstation.controller;

import com.ruoyi.common.constant.FileConstants;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.product.list.service.IDevProductListService;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.service.IDevWorkOrderService;
import com.ruoyi.project.production.workstation.domain.Workstation;
import com.ruoyi.project.production.workstation.service.IWorkstationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工位配置 信息操作处理
 * 
 * @author sj
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/production/workstation")
public class WorkstationController extends BaseController
{
    private String prefix = "production/workstation";

	@Autowired
	private IWorkstationService workstationService;

	@Autowired
	private IDevWorkOrderService devWorkOrderService;
	@Autowired
	private IDevProductListService productListService;


	@GetMapping("/{id}")
	public String workstation(@PathVariable("id") int id, ModelMap mmap)
	{
		mmap.put("line",id);
	    return prefix + "/workstation";
	}
	
	/**
	 * 查询工位配置列表
	 */
	@RequiresPermissions("production:workstation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Workstation workstation)
	{
		startPage();
        List<Workstation> list = workstationService.selectWorkstationList(workstation);
		return getDataTable(list);
	}

	/**
	 * 新增工位配置
	 */
	@GetMapping("/add")
	public String add(int line,ModelMap mmap)
	{
		mmap.put("line",line);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工位配置
	 */
	@RequiresPermissions("production:workstation:add")
	@Log(title = "工位配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Workstation workstation,HttpServletRequest request)
	{
	    try {
	    	workstation.setCompanyId(JwtUtil.getTokenUser(request).getCompanyId());
            workstationService.insertWorkstation(workstation);
            return AjaxResult.success();
        }catch (Exception e){
	    	e.printStackTrace();
	        return AjaxResult.error(e.getMessage());
        }

	}

	/**
	 * 修改工位配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Workstation workstation = workstationService.selectWorkstationById(id);
		mmap.put("workstation", workstation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工位配置
	 */
	@RequiresPermissions("production:workstation:edit")
	@Log(title = "工位配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Workstation workstation)
	{
		try {
			workstationService.updateWorkstation(workstation);
			return AjaxResult.success();
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		}
	}
	
	/**
	 * 删除工位配置
	 */
	@RequiresPermissions("production:workstation:remove")
	@Log(title = "工位配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(Integer id)
	{
		return toAjax(workstationService.deleteWorkstationById(id));
	}

	/**
	 * 通过产线id以及作业指导书id查询所有工位信息以及作业指导书列表
	 */
	@PostMapping("/findByLineId")
	@ResponseBody
	public AjaxResult findByLineId(Integer lineId){
		Map map = new HashMap<String,Object>(16);
		map.put("proList", productListService.selectNotConfigByLineId2(lineId, JwtUtil.getUser().getCompanyId(), FileConstants.SOP_TAG_LINE));
		map.put("work",workstationService.selectAllByLineId(lineId));
		return AjaxResult.success(map);
	}

	@RequestMapping("/work/{workid}")
	public String workstation2(@PathVariable("workid")int workid,ModelMap modelMap){
		DevWorkOrder order = devWorkOrderService.selectDevWorkOrderById(workid);
		modelMap.put("line",order.getLineId());
		return prefix + "/workstation";
	}
	
}
