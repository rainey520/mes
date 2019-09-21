package com.ruoyi.project.product.list.controller;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.product.importConfig.domain.ImportConfig;
import com.ruoyi.project.product.importConfig.service.IImportConfigService;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.service.IDevProductListService;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品管理 信息操作处理
 *
 * @author ruoyi
 * @date 2019-04-10
 */
@Controller
@RequestMapping("/product")
public class DevProductListController extends BaseController {
    private String prefix = "product/list";

    private String prefixPart = "product/part";

    @Autowired
    private IDevProductListService devProductListService;

    @Autowired
    private IImportConfigService configService;

    @RequiresPermissions("device:devProductList:view")
    @GetMapping()
    public String devProductList() {
        return prefix + "/devProductList";
    }

    /**
     * 查询产品管理列表
     */
    @RequiresPermissions("device:devProductList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DevProductList devProductList, HttpServletRequest request) {
        startPage();
        devProductList.setCompanyId(JwtUtil.getTokenUser(request).getCompanyId());
        devProductList.setSign(0);
        List<DevProductList> list = devProductListService.selectDevProductListList(devProductList);
        return getDataTable(list);
    }


    /**
     * 导出产品管理列表
     */
    @RequiresPermissions("device:devProductList:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevProductList devProductList) {
        //编辑为产品
        devProductList.setSign(0);
        devProductList.setCompanyId(JwtUtil.getUser().getCompanyId());
        List<DevProductList> list = devProductListService.selectDevProductListList(devProductList);
        ExcelUtil<DevProductList> util = new ExcelUtil<DevProductList>(DevProductList.class);
        return util.exportExcel(list, "产品编码");
    }

    /**
     * 新增产品管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品管理
     */
    @RequiresPermissions("device:devProductList:add")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DevProductList devProductList, HttpServletRequest request) {
        return toAjax(devProductListService.insertDevProductList(devProductList, request));
    }

    /**
     * 修改产品管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        DevProductList devProductList = devProductListService.selectDevProductListById(id);
        mmap.put("product", devProductList);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品管理
     */
    @RequiresPermissions("device:devProductList:edit")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DevProductList devProductList, HttpServletRequest request) {
        devProductList.setCompanyId(JwtUtil.getTokenUser(request).getCompanyId());
        return toAjax(devProductListService.updateDevProductList(devProductList));
    }

    /**
     * 删除产品管理
     */
    @RequiresPermissions("device:devProductList:remove")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids, HttpServletRequest request) {
        try {
            return toAjax(devProductListService.deleteDevProductListByIds(ids, request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 导出模板
     *
     * @return
     */
    @RequiresPermissions("device:devProductList:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<DevProductList> productListExcelUtil = new ExcelUtil<>(DevProductList.class);
        return productListExcelUtil.importTemplateExcel("产品数据");
    }

    @Log(title = "产品导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("device:devProductList:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        try {
            return AjaxResult.success(devProductListService.importProduct(file, updateSupport, 0));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 通过产品id查询产品信息
     *
     * @param productId
     * @return
     */
    @PostMapping("/findProductInfo")
    @ResponseBody
    public AjaxResult findProductInfo(Integer productId, HttpServletRequest request) {
        DevProductList productCode = devProductListService.findProductInfo(productId, request);
        return AjaxResult.success("成功", productCode);
    }

    /**
     * 校验产品编码唯一性
     *
     * @return
     */
    @PostMapping("/checkProductCodeUnique")
    @ResponseBody
    public String checkProductCodeUnique(DevProductList product, HttpServletRequest request) {
        return devProductListService.checkProductCodeUnique(product, request);
    }

    /**
     * 通过客户id查询相关产品信息
     *
     * @param customerId 客户id
     * @return 结果
     */
    @PostMapping("/findProductByCustomerId")
    @ResponseBody
    public AjaxResult findProductByCustomerId(Integer customerId) {
        List<DevProductList> productList = devProductListService.findProductByCustomerId(customerId);
        return AjaxResult.success("success", productList);
    }

    /**
     * 通过产品id和客户id查询产品信息
     *
     * @param productId  产品id
     * @param customerId 客户id
     * @return 结果
     */
    @PostMapping("/findProductByProIdAndCusId")
    @ResponseBody
    public AjaxResult findProductByProIdAndCusId(Integer productId, Integer customerId) {
        return AjaxResult.success("success", devProductListService.findProductByProIdAndCusId(productId, customerId));
    }

    /**
     * 查询对应客户关联的产品信息
     *
     * @param customerId 客户编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectProductByCustomerId")
    public AjaxResult selectProductByCustomerId(int customerId) {
        List<DevProductList> productLists = devProductListService.selectProductByCustomerId(customerId);
        return AjaxResult.success("success", productLists);
    }

    /**
     * 查询对应的产品数据
     *
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectProductAllByOrderId")
    public AjaxResult selectProductAllByOrderId(int orderId, HttpServletRequest request) {
        return AjaxResult.success("success", devProductListService.selectProductAllByOrderId(orderId, request));
    }


    /********************产品导入配置**********************/

    @RequestMapping("/importProductConfig")
    @RequiresPermissions("device:devProductList:importConfig")
    public String importProductConfig(int type, ModelMap mmap) {
        mmap.put("config", configService.selectImportConfigByType(type));
        return prefix + "/pconfig";
    }

    /**
     * 添加产品导入配置
     *
     * @param config 配置信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/addProductConfig")
    @RequiresPermissions("device:devProductList:importConfig")
    public AjaxResult addImportConfig(ImportConfig config) {
        config.setcType(0);
        return toAjax(configService.insertImportConfig(config));
    }


    /********************半成品相关操作**********************/


    @GetMapping("/part")
    @RequiresPermissions("device:devPart:view")
    public String devPartList() {
        return prefixPart + "/part";
    }

    /**
     * 半成品导入配置
     *
     * @param type 配置类型
     * @param mmap 缓存数据
     * @return
     */
    @RequestMapping("/importPartConfig")
    @RequiresPermissions("device:devPart:importConfig")
    public String importPartConfig(int type, ModelMap mmap) {
        mmap.put("config", configService.selectImportConfigByType(type));
        return prefixPart + "/partconfig";
    }

    /**
     * 添加半成品导入配置
     *
     * @param config 配置信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPartConfig")
    @RequiresPermissions("device:devPart:importConfig")
    public AjaxResult addImportPartConfig(ImportConfig config) {
        config.setcType(1);
        return toAjax(configService.insertImportConfig(config));
    }

    /**
     * 新增半成品管理
     */
    @GetMapping("/addPart")
    public String addPart() {
        return prefixPart + "/add";
    }

    /**
     * 新增保存半成品管理
     */
    @RequiresPermissions("device:devPart:add")
    @Log(title = "半成品管理", businessType = BusinessType.INSERT)
    @PostMapping("/addPartSave")
    @ResponseBody
    public AjaxResult addPartSave(DevProductList devProductList, HttpServletRequest request) {
        return toAjax(devProductListService.insertDevProductList(devProductList, request));
    }

    /**
     * 查询半成品管理列表
     */
    @RequiresPermissions("device:devPart:list")
    @PostMapping("/partList")
    @ResponseBody
    public TableDataInfo partList(DevProductList devProductList, HttpServletRequest request) {
        startPage();
        devProductList.setCompanyId(JwtUtil.getTokenUser(request).getCompanyId());
        devProductList.setSign(1);
        List<DevProductList> list = devProductListService.selectDevProductListList(devProductList);
        return getDataTable(list);
    }

    /**
     * 修改半成品管理
     */
    @GetMapping("/editPart/{id}")
    public String editPart(@PathVariable("id") Integer id, ModelMap mmap) {
        DevProductList devProductList = devProductListService.selectDevProductListById(id);
        mmap.put("product", devProductList);
        return prefixPart + "/edit";
    }

    /**
     * 修改保存半成品管理
     */
    @RequiresPermissions("device:devPart:add")
    @Log(title = "半成品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/editPart")
    @ResponseBody
    public AjaxResult editPartSave(DevProductList devProductList) {
        devProductList.setCompanyId(JwtUtil.getUser().getCompanyId());
        return toAjax(devProductListService.updateDevProductList(devProductList));
    }

    /**
     * 删除产品管理
     */
    @RequiresPermissions("device:devPart:remove")
    @Log(title = "半成品管理", businessType = BusinessType.DELETE)
    @PostMapping("/removePart")
    @ResponseBody
    public AjaxResult removePart(String ids, HttpServletRequest request) {
        try {
            return toAjax(devProductListService.deleteDevProductListByIds(ids, request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "半成品导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("device:devPart:import")
    @PostMapping("/importPart")
    @ResponseBody
    public AjaxResult importPart(MultipartFile file, boolean updateSupport) throws Exception {
        try {
            return AjaxResult.success(devProductListService.importProduct(file, updateSupport, 1));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 导出产品管理列表
     */
    @RequiresPermissions("device:devPart:import")
    @PostMapping("/exportPart")
    @ResponseBody
    public AjaxResult exportPart(DevProductList devProductList) {
        devProductList.setSign(1);
        List<DevProductList> list = devProductListService.selectDevProductListList(devProductList);
        ExcelUtil<DevProductList> util = new ExcelUtil<DevProductList>(DevProductList.class);
        return util.exportExcel(list, "半成品编码");
    }


    /**
     * 查看mes配置规格明细
     */
    @RequiresPermissions("mes:mesBatchRule:list")
    @PostMapping("/mesCfList")
    @ResponseBody
    public TableDataInfo mesCfList(DevProductList productList) {
        startPage();
        List<DevProductList> list = devProductListService.selectMesCfList(productList);
        return getDataTable(list);
    }

    /**
     * 保存 MES 规则
     *
     * @param id     产品/半成品
     * @param ruleId 对应规则id
     * @return
     */
    @RequiresPermissions("mes:mesBatchRule:remove")
    @ResponseBody
    @PostMapping("/saveMesRule")
    public AjaxResult saveMesRuleConfig(int id, int ruleId) {
        try {
            return toAjax(devProductListService.saveMesRuleConfig(id,ruleId));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 取消MES 规则配置
     *
     * @param id 对应产品/半成品id
     * @return
     */
    @RequiresPermissions("mes:mesBatchRule:remove")
    @ResponseBody
    @RequestMapping("/cancelMes")
    public AjaxResult cancel(Integer id) {
        try {
            return toAjax(devProductListService.cancel(id));
        } catch (Exception e) {
            return error();
        }
    }

    @GetMapping("/mesConfig")
    public String mesConfig(int id, ModelMap modelMap) {
        modelMap.put("id", id);
        modelMap.put("rule", devProductListService.selectMesBatchRuleByPbId(id));
        return prefix + "/mesConfig";
    }

    @GetMapping("/mesParConfig")
    public String mesParConfig(int id, ModelMap modelMap) {
        modelMap.put("id", id);
        modelMap.put("rule", devProductListService.selectMesBatchRuleByPbId(id));
        return prefixPart + "/mesParConfig";
    }

    /******************************************************************************************************
     *********************************** APP产品、半成品编码查询 *******************************************
     ******************************************************************************************************/

    @Autowired
    private IMenuService menuService;

    /**
     * app查询产品或者半成品
     */
    @PostMapping("/applist")
    @ResponseBody
    public AjaxResult appSelectProList(@RequestBody DevProductList product) {
        try {
            User user = JwtUtil.getUser();
            if (product != null && user != null) {
                product.appStartPage();
                Map<String, Object> map = new HashMap<>(16);
                if (product.getmParentId() != null) {
                    map.put("menuList", menuService.selectMenuListByParentIdAndUserId(user.getUserId().intValue(), product.getmParentId()));
                }
                map.put("productList", devProductListService.selectDevProductListList(product));
                return AjaxResult.success("请求成功", map);
            }
            return error();
        } catch (Exception e) {
            return AjaxResult.error("请求失败");
        }
    }

    /**
     * 查询所以查询信息
     * @return
     */
    @ResponseBody
    @PostMapping("/appProList")
    public AjaxResult appSelectAllProList(){
        try {
            return AjaxResult.success(devProductListService.selectProductAll());
        }catch (Exception e){
            return error(e.getMessage());
        }
    }

}
