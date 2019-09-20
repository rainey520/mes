package com.ruoyi.project.production.singleWork.controller;

import com.ruoyi.common.constant.WorkConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.service.IDevWorkOrderService;
import com.ruoyi.project.production.singleWork.domain.SingleWork;
import com.ruoyi.project.production.singleWork.domain.SingleWorkOrder;
import com.ruoyi.project.production.singleWork.service.ISingleWorkOrderService;
import com.ruoyi.project.production.singleWork.service.ISingleWorkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 单工位与工单进行配置 信息操作处理
 *
 * @author sj
 * @date 2019-07-09
 */
@Controller
@RequestMapping("/production/singleWorkOrder")
public class SingleWorkOrderController extends BaseController {
    private String prefix = "production/singleWorkOrder";

    @Autowired
    private ISingleWorkOrderService singleWorkOrderService;

    @Autowired
    private ISingleWorkService singleWorkService;

    @Autowired
    private IDevWorkOrderService workOrderService;

    /**
     * @param type     0、单工位配置  1、工单配置
     * @param orderId  工单id
     * @param singleId 单工位id
     * @param modelMap
     * @return
     */
    @GetMapping()
    public String singleWorkOrder(Integer type, Integer orderId, Integer singleId, ModelMap modelMap) {
        modelMap.put("type", type);
        modelMap.put("orderId", orderId);
        modelMap.put("singleId", singleId);
        DevWorkOrder workOrder = null;
        if (orderId != 0) {
            workOrder = workOrderService.selectDevWorkOrderById(orderId);
        }
        if (StringUtils.isNotNull(workOrder)) {
            modelMap.put("workStatus", workOrder.getWorkorderStatus());
        } else {
            modelMap.put("workStatus", 0);
        }
        return prefix + "/singleWorkOrder";
    }

    /**
     * 查询单工位与工单进行配置列表
     */
    @RequiresPermissions("production:singleWorkOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SingleWorkOrder singleWorkOrder) {
        startPage();
        List<SingleWorkOrder> list = singleWorkOrderService.selectSingleWorkOrderList(singleWorkOrder);
        return getDataTable(list);
    }


    /**
     * 新增单工位与工单进行配置
     *
     * @param type     0、单工位配置  1、工单配置
     * @param orderId  工单id
     * @param singleId 单工位id
     * @return
     */
    @GetMapping("/add")
    public String add(Integer type, Integer orderId, Integer singleId, ModelMap modelMap) {
        modelMap.put("type", type);
        if (type == 0) {
            SingleWork singleWork = singleWorkService.selectSingleWorkById(singleId);
            List<DevWorkOrder> workOrders = null;
            if (singleWork != null) {
                // 查询对应车间还未开始的公安单信息
                workOrders = workOrderService.selectAllNotConfigBySwId(singleWork.getParentId(), WorkConstants.WORK_STATUS_NOSTART,
                        WorkConstants.SING_SINGLE, singleId, JwtUtil.getUser().getCompanyId());
            }
            modelMap.put("works", workOrders);
        }
        modelMap.put("orderId", orderId);
        modelMap.put("singleId", singleId);
        return prefix + "/add";
    }

    /**
     * 新增保存单工位与工单进行配置
     */
    @RequiresPermissions("production:singleWorkOrder:add")
    @Log(title = "单工位与工单进行配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SingleWorkOrder singleWorkOrder) {
        try {
            return toAjax(singleWorkOrderService.insertSingleWorkOrder(singleWorkOrder));
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改单工位与工单进行配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Integer type, Integer orderId, Integer singleId, ModelMap modelMap) {
        SingleWorkOrder singleWorkOrder = singleWorkOrderService.selectSingleWorkOrderById(id);
        modelMap.put("singleWorkOrder", singleWorkOrder);
        modelMap.put("type", type);
        if (type == 1) {
            modelMap.put("orderId", orderId);
            modelMap.put("singleP", singleId);
            //查询未配置的工位信息
            List<SingleWork> singleWorks = singleWorkService.selectAllNotConfigWorkByOrderId(orderId, singleId);
            if (singleWorks == null) singleWorks = new ArrayList<>();
            //查询对应配置工位
            singleWorks.add(singleWorkService.selectSingleWorkById(singleWorkOrder.getSingleId()));
            modelMap.put("works", singleWorks);
        } else {
            modelMap.put("singleId", singleId);
            // 车间id
            SingleWork singleWork = singleWorkService.selectSingleWorkById(singleId);
            modelMap.put("works",  workOrderService.selectAllNotConfigBySwId(singleWork.getParentId(), WorkConstants.WORK_STATUS_NOSTART,
                    WorkConstants.SING_SINGLE, singleId, JwtUtil.getUser().getCompanyId()));
            modelMap.put("singleP", singleWork.getParentId());
        }
        return prefix + "/edit";
    }

    /**
     * 修改保存单工位与工单进行配置
     */
    @RequiresPermissions("production:singleWorkOrder:edit")
    @Log(title = "单工位与工单进行配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SingleWorkOrder singleWorkOrder) {
        return toAjax(singleWorkOrderService.updateSingleWorkOrder(singleWorkOrder));
    }

    /**
     * 删除单工位与工单进行配置
     */
    @RequiresPermissions("production:singleWorkOrder:remove")
    @Log(title = "单工位与工单进行配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(singleWorkOrderService.deleteSingleWorkOrderByIds(ids));
    }

}
