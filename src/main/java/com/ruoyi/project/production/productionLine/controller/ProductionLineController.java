package com.ruoyi.project.production.productionLine.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.service.IDevWorkOrderService;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.service.IProductionLineService;
import com.ruoyi.project.production.workstation.domain.Workstation;
import com.ruoyi.project.production.workstation.service.IWorkstationService;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;
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
 * 生产线 信息操作处理
 *
 * @author ruoyi
 * @date 2019-04-11
 */
@Controller
@RequestMapping("/production/productionLine")
public class ProductionLineController extends BaseController {
    private String prefix = "production/productionLine";

    @Autowired
    private IProductionLineService productionLineService;

    @RequiresPermissions("production:productionLine:list")
    @GetMapping("/showLine")
    public String productionLine() {
        User user = JwtUtil.getUser();
        if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
            return prefix + "/productionLineEn";
        }
        return prefix + "/productionLine";
    }

    /**
     * 查询生产线列表
     */
    @RequiresPermissions("production:productionLine:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductionLine productionLine, HttpServletRequest request) {
        startPage();
        List<ProductionLine> list = productionLineService.selectProductionLineList(productionLine,request);
        return getDataTable(list);
    }


    /**
     * 导出生产线列表
     */
    @RequiresPermissions("production:productionLine:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductionLine productionLine,HttpServletRequest request) {
        List<ProductionLine> list = productionLineService.selectProductionLineList(productionLine,request);
        ExcelUtil<ProductionLine> util = new ExcelUtil<ProductionLine>(ProductionLine.class);
        return util.exportExcel(list, "productionLine");
    }

    /**
     * 新增生产线
     */
    @GetMapping("/add")
    public String add() {
        User user = JwtUtil.getUser();
        if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
            return prefix + "/addEn";
        }
        return prefix + "/add";
    }

    /**
     * 新增保存生产线
     */
    @RequiresPermissions("production:productionLine:add")
    @Log(title = "生产线", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProductionLine productionLine,HttpServletRequest request) {
        try {
            return toAjax(productionLineService.insertProductionLine(productionLine,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改生产线
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductionLine productionLine = productionLineService.selectProductionLineById(id);
        mmap.put("productionLine", productionLine);
        User user = JwtUtil.getUser();
        if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
            return prefix + "/editEn";
        }
        return prefix + "/edit";
    }

    /**
     * 修改保存生产线
     */
    @RequiresPermissions("production:productionLine:add")
    @Log(title = "生产线", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductionLine productionLine,HttpServletRequest request) {
        try {
            return toAjax(productionLineService.updateProductionLine(productionLine,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 删除生产线
     */
    @RequiresPermissions("production:productionLine:remove")
    @Log(title = "生产线", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer id,HttpServletRequest request) {
        try {
            return toAjax(productionLineService.deleteProductionLineById(id,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 产线配置硬件
     *
     * @return
     */
    @RequiresPermissions("production:productionLine:devconfig")
    @GetMapping("/devcnfig/{id}")
    public String devConfig(@PathVariable("id") int id, ModelMap mmap) {
        mmap.put("line", productionLineService.selectProductionLineById(id));
        mmap.put("config", productionLineService.selectLineDev(id));
        return prefix + "/devconfig";
    }

    /**
     * 保存相关产线IO口的配置
     *
     * @param line
     * @return
     */
    @ResponseBody
    @RequestMapping("/save/config")
    @RequiresPermissions("production:productionLine:devconfig")
    public AjaxResult saveDevConfig(@RequestBody ProductionLine line,HttpServletRequest request) {
        try {
            return toAjax(productionLineService.updateLineConfigClear(line,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 通过生产线查询责任人信息
     *
     * @param lineId
     * @return
     */
    @PostMapping("/findDeviceLiableByLineId")
    @ResponseBody
    public AjaxResult findDeviceLiableByLineId(Integer lineId) {
        Map map = productionLineService.findDeviceLiableByLineId(lineId);
        return AjaxResult.success("success", map);
    }

    /**
     * 自定义数据
     *
     * @return
     */
    @GetMapping("/custom/{id}")
    @RequiresPermissions("production:productionLine:custom")
    public String custom(@PathVariable("id") int id, ModelMap mmap) {
        ProductionLine productionLine = productionLineService.selectProductionLineById(id);
        if (StringUtils.isNotNull(productionLine) && !StringUtils.isEmpty(productionLine.getParamConfig())) {
            productionLine.setParamArray(JSON.parseArray(productionLine.getParamConfig(), String.class));
        }
        mmap.put("line", productionLine);
        return prefix + "/customParam";
    }

    /**
     * 产线实况
     *
     * @param id   产线编号
     * @param mmap
     * @return
     */
    @GetMapping("/live/{id}")
    @RequiresPermissions("production:productionLine:live")
    public String lineLive(@PathVariable("id") int id, ModelMap mmap) {
        ProductionLine productionLine = productionLineService.selectProductionLineById(id);
        mmap.put("line", productionLine);
        return prefix + "/lineLive";
    }

    /**
     * 检验产线名称的唯一性
     */
    @PostMapping("/checkLineNameUnique")
    @ResponseBody
    public String checkLineNameUnique(ProductionLine productionLine){
        return productionLineService.checkLineNameUnique(productionLine);
    }



    /******************************************************************************************************
     *********************************** app流水线交互逻辑 *************************************************
     ******************************************************************************************************/
    @Autowired
    private IWorkstationService workstationService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IDevWorkOrderService workOrderService;

    /**
     * app端查询流水线信息
     */
    @PostMapping("/applist")
    @ResponseBody
    public AjaxResult appSelectLineList(@RequestBody ProductionLine productionLine){
        try {
            User user = JwtUtil.getUser();
            if (user == null) {
                return AjaxResult.error("未登录或者登录超时");
            }
            if (productionLine != null) {
                productionLine.appStartPage();
                Map<String,Object> map = new HashMap<>(16);
                if (productionLine.getmParentId() != null) {
                    List<Menu> menuApiList = menuService.selectMenuListByParentIdAndUserId(user.getUserId().intValue(), productionLine.getmParentId());
                    map.put("menuList",menuApiList);
                }
                map.put("lineList",productionLineService.appSelectLineList(productionLine));
                return AjaxResult.success("请求成功",map);
            }
            return error();
        } catch (Exception e) {
            return AjaxResult.error("请求失败");
        }
    }

    /**
     * app端查询流水线工位配置的列表
     */
    @PostMapping("/appLineCfDevList")
    @ResponseBody
    public AjaxResult appSelectLineCfDevList(@RequestBody Workstation workstation){
        try {
            return  AjaxResult.success("请求成功",workstationService.appSelectWorkstationList(workstation));
        } catch (Exception e) {
            return error("请求失败");
        }
    }

    /**
     * 通过产线id查询在该产线的工单列表 -- 产线实况
     * lineId 产线id
     */
    @PostMapping("/appWorkInLine")
    @ResponseBody
    public AjaxResult appSelectLineWorkList(@RequestBody DevWorkOrder workOrder){
        try {
            if (workOrder != null) {
                workOrder.appStartPage();
                Map<String,Object> map = new HashMap<>(16);
                if (workOrder != null && workOrder.getMenuList() != null && workOrder.getUid() != null) {
                    map.put("menuList",menuService.selectMenuListByParentIdAndUserId(workOrder.getUid(),workOrder.getMenuList()));
                }
                map.put("workOrderList",workOrderService.selectDevWorkOrderList(workOrder));
                return AjaxResult.success("请求成功",map);
            }
            return error();
        } catch (Exception e) {
            return error("请求失败");
        }
    }

    @ResponseBody
    @PostMapping("/appSelectAllLine")
    public AjaxResult appSelectAllLine(){
        try {
            return AjaxResult.success(productionLineService.selectAllProductionLineByCompanyId());
        }catch (Exception e){
            return error();
        }
    }

}
