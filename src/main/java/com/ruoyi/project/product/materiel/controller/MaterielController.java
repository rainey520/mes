package com.ruoyi.project.product.materiel.controller;

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
import com.ruoyi.project.product.materiel.domain.Materiel;
import com.ruoyi.project.product.materiel.service.IMaterielService;
import com.ruoyi.project.system.menu.service.IMenuService;
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
 * 物料 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/materiel")
public class MaterielController extends BaseController {
    private String prefix = "product/materiel";

    @Autowired
    private IMaterielService materielService;

    @Autowired
    private IImportConfigService configService;

    @RequiresPermissions("erp:materiel:view")
    @GetMapping()
    public String materiel() {
        return prefix + "/materiel";
    }

    /**
     * 查询物料列表
     */
    @RequiresPermissions("erp:materiel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Materiel materiel, HttpServletRequest request) {
        startPage();
        List<Materiel> list = materielService.selectMaterielList(materiel,request);
        return getDataTable(list);
    }

    /**
     * 导出模板
     *
     * @return
     */
    @RequiresPermissions("erp:materiel:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Materiel> materielListExcelUtil = new ExcelUtil<>(Materiel.class);
        return materielListExcelUtil.importTemplateExcel("物料数据");
    }

    /**
     * 导入物料列表
     *
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "物料导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("erp:materiel:export")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport)  {
        String message = null;
        try {
            message = materielService.importMateriel(file, updateSupport,2);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(message);
    }

    /**
     * 物料导入配置
     * @return
     */
    @Log(title = "物料导入配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("erp:materiel:export")
    @GetMapping("/importConfig")
    public String mConfig(int type,ModelMap modelMap){
        modelMap.put("config",configService.selectImportConfigByType(type));
        return prefix+"/mconfig";
    }

    /**
     * 添加物料导入配置
     * @param config 配置信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/addMConfig")
    @RequiresPermissions("erp:materiel:export")
    public AjaxResult addImportConfig(ImportConfig config){
        config.setcType(2);
        return toAjax(configService.insertImportConfig(config));
    }

    /**
     * 导出物料列表
     */
    @RequiresPermissions("erp:materiel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Materiel materiel,HttpServletRequest request) {
        List<Materiel> list = materielService.selectMaterielList(materiel,request);
        ExcelUtil<Materiel> util = new ExcelUtil<Materiel>(Materiel.class);
        return util.exportExcel(list, "materiel");
    }

    /**
     * 新增物料
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物料
     */
    @RequiresPermissions("erp:materiel:edit")
    @Log(title = "物料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Materiel materiel,HttpServletRequest request) {
        return toAjax(materielService.insertMateriel(materiel,request));
    }

    /**
     * 修改物料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Materiel materiel = materielService.selectMaterielById(id);
        mmap.put("materiel", materiel);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料
     */
    @RequiresPermissions("erp:materiel:edit")
    @Log(title = "物料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Materiel materiel) {
        return toAjax(materielService.updateMateriel(materiel));
    }

    /**
     * 删除物料
     */
    @RequiresPermissions("erp:materiel:remove")
    @Log(title = "物料", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids,HttpServletRequest request) {
        try {
            return toAjax(materielService.deleteMaterielByIds(ids,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 通过供应商id查询和供应商关联的物料列表
     *
     * @param supplierId
     * @return
     */
    @RequestMapping("/materielListBySupplierId")
    @ResponseBody
    public AjaxResult materielListBySupplierId(Integer supplierId,HttpServletRequest request) {
        List<Materiel> materielList = materielService.materielListBySupplierId(supplierId,request);
        return AjaxResult.success("success", materielList);
    }

    /**
     * 通过物料id查询物料相关信息
     *
     * @param supplierId
     * @param materielId 物料id
     * @return 结果
     */
    @RequestMapping("/materielListByMaterielId")
    @ResponseBody
    public AjaxResult materielListByMaterielId(Integer supplierId, Integer materielId) {
        Materiel materiel= materielService.materielListByMaterielId(supplierId,materielId);
        return AjaxResult.success("success", materiel);
    }

    /**
     * 根据供应商id查询对应物料信息
     * @param sid 供应商id
     * @return 物料信息
     */
    @ResponseBody
    @RequestMapping("/selectMaterielBySupplierId")
    public AjaxResult selectMaterielBySupplierId(int sid,HttpServletRequest request){
        return AjaxResult.success("success",materielService.selectMaterielBySupplierId(sid,request));
    }

    @PostMapping("/checkMaterielCodeUnique")
    @ResponseBody
    public String checkMaterielCodeUnique(Materiel materiel,HttpServletRequest request){
        materiel.setCompanyId(JwtUtil.getTokenUser(request).getCompanyId());
        return materielService.checkMaterielCodeUnique(materiel);
    }

    /******************************************************************************************************
     *********************************** app物料交互逻辑 ***************************************************
     ******************************************************************************************************/

    @Autowired
    private IMenuService menuService;
    /**
     * app端查询物料信息
     */
    @PostMapping("/applist")
    @ResponseBody
    public AjaxResult appSelectMaterielList(@RequestBody Materiel materiel,HttpServletRequest request){
        try {
            if (materiel != null) {
                materiel.appStartPage();
                Map<String,Object> map = new HashMap<>(16);
                if (materiel.getUid() != null && materiel.getmParentId() != null) {
                    map.put("menuList",menuService.selectMenuListByParentId(materiel.getUid(),materiel.getmParentId()));
                }
                map.put("materielList",materielService.selectMaterielList(materiel,request));
                return AjaxResult.success("请求成功",map);
            }
            return error();
        } catch (Exception e) {
            return AjaxResult.error("请求失败");
        }
    }
}
