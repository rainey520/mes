package com.ruoyi.project.production.workOrderChange.controller;

import java.util.List;

import com.ruoyi.project.production.devWorkOrder.service.IDevWorkOrderService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.project.production.workOrderChange.domain.WorkOrderChange;
import com.ruoyi.project.production.workOrderChange.service.IWorkOrderChangeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;


/**
 * 工单变更 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/production/workOrderChange")
public class WorkOrderChangeController extends BaseController
{
    private String prefix = "production/workOrderChange";
	
	@Autowired
	private IWorkOrderChangeService workOrderChangeService;

	@Autowired
	private IDevWorkOrderService devWorkOrderService;

	@GetMapping()
	public String workOrderChange(int order_id,ModelMap mmap)
	{
		mmap.put("order",devWorkOrderService.selectDevWorkOrderById(order_id));
	    return prefix + "/workOrderChange";
	}
	
	/**
	 * 查询工单变更列表
	 */
	@RequiresPermissions("production:workOrderChange:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WorkOrderChange workOrderChange, HttpServletRequest request)
	{
		startPage();
        List<WorkOrderChange> list = workOrderChangeService.selectWorkOrderChangeList(workOrderChange,request);
		return getDataTable(list);
	}

}
