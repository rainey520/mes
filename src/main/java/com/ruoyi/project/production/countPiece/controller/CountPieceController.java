package com.ruoyi.project.production.countPiece.controller;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.production.countPiece.domain.CountPiece;
import com.ruoyi.project.production.countPiece.service.ICountPieceService;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 计件管理数据 信息操作处理
 *
 * @author sj
 * @date 2019-07-04
 */
@Controller
@RequestMapping("/production/countPiece")
public class CountPieceController extends BaseController {
    private String prefix = "production/countPiece";

    @Autowired
    private ICountPieceService countPieceService;

    @RequiresPermissions("production:countPiece:view")
    @GetMapping()
    public String countPiece() {
        return prefix + "/countPiece";
    }

    /**
     * 我的计件
     */
    @RequiresPermissions("production:countPiece:list")
    @GetMapping("/myCountPiece")
    public String myCountPiece(ModelMap mmap) {
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        mmap.put("cpUserId", user.getUserId());
        return prefix + "/myCountPiece";
    }

    /**
     * 查询计件管理数据统计列表
     */
    @RequiresPermissions("production:countPiece:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CountPiece countPiece) {
        startPage();
        List<CountPiece> list = countPieceService.selectCountPieceList(countPiece);
        return getDataTable(list);
    }


    /**
     * 导出计件管理数据列表
     */
    @RequiresPermissions("production:countPiece:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CountPiece countPiece) {
        List<CountPiece> list = countPieceService.selectCountPieceList(countPiece);
        ExcelUtil<CountPiece> util = new ExcelUtil<CountPiece>(CountPiece.class);
        return util.exportExcel(list, "计件数据");
    }

    /**
     * 新增计件管理数据
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存计件管理数据
     */
    @RequiresPermissions("production:countPiece:add")
    @Log(title = "计件管理数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CountPiece countPiece) {
        try {
            return toAjax(countPieceService.insertCountPiece(countPiece));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改计件管理数据
     */
    @GetMapping("/edit/{cpId}")
    public String edit(@PathVariable("cpId") Integer cpId, ModelMap mmap) {
        CountPiece countPiece = countPieceService.selectCountPieceById(cpId);
        mmap.put("countPiece", countPiece);
        return prefix + "/edit";
    }

    /**
     * 修改保存计件管理数据
     */
    @RequiresPermissions("production:countPiece:edit")
    @Log(title = "计件管理数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CountPiece countPiece) {
        try {
            return toAjax(countPieceService.updateCountPiece(countPiece));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 删除计件管理数据
     */
    @RequiresPermissions("production:countPiece:remove")
    @Log(title = "计件管理数据", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(countPieceService.deleteCountPieceByIds(ids));
    }

    /**
     * 查看计件明细
     */
    @GetMapping("/showDetaiByUid")
    public String showDetaiByUid(Integer cpUserId, ModelMap modelMap) {
        modelMap.put("cpUserId", cpUserId);
        return prefix + "/detail";
    }

    /**
     * 查询计件管理数据明细列表
     */
    @RequiresPermissions("production:countPiece:list")
    @PostMapping("/listDetail")
    @ResponseBody
    public TableDataInfo listDetail(CountPiece countPiece) {
        startPage();
        List<CountPiece> list = countPieceService.selectCountPieceListDetail(countPiece);
        return getDataTable(list);
    }

    /**
     * 查看指定日期的计件详情
     */
    @GetMapping("/showDetaiByCpDate")
    public String showDetaiByCpDate(String cpDate, ModelMap modelMap) {
        modelMap.put("cpDate", cpDate);
        return prefix + "/detailByDate";
    }

    /**
     * 查看个人指定日期的计件详情
     */
    @GetMapping("/showMyDetaiByCpDate")
    public String showMyDetaiByCpDate(String cpDate, Integer cpUserId, ModelMap modelMap) {
        modelMap.put("cpDate", cpDate);
        modelMap.put("cpUserId", cpUserId);
        return prefix + "/myDetailByDate";
    }

    /**
     * 查询指定日起的计件信息
     */
    @RequiresPermissions("production:countPiece:list")
    @PostMapping("/listByDate")
    @ResponseBody
    public TableDataInfo listByDate(CountPiece countPiece) {
        startPage();
        List<CountPiece> list = countPieceService.selectCountPieceListByDate(countPiece);
        return getDataTable(list);
    }


    /******************************************************************************************************
     *********************************** app端计件统计交互 *************************************************
     ******************************************************************************************************/
    /**
     * app端查询个人计件列表信息
     */
    @PostMapping("/myCountPiece/applist")
    @ResponseBody
    public AjaxResult appSelectList(@RequestBody CountPiece countPiece){
        try {
            return AjaxResult.success("请求成功",countPieceService.selectCountPieceList(countPiece));
        } catch (Exception e) {
            return error("请求失败");
        }
    }
    /**
     * app端查询所有人的计件信息
     */
    @PostMapping("/applist")
    @ResponseBody
    public AjaxResult appSelectList1(@RequestBody CountPiece countPiece){
        try {
            return AjaxResult.success("请求成功",countPieceService.selectCountPieceList(countPiece));
        } catch (Exception e) {
            return error("请求失败");
        }
    }

    /**
     * app端计件明细列表
     */
    @PostMapping("/appDetail")
    @ResponseBody
    public AjaxResult appSelectDetailList(@RequestBody CountPiece countPiece){
        try {
            if (countPiece != null) {
                countPiece.appStartPage();
                return AjaxResult.success("请求成功",countPieceService.appSelectDetailList(countPiece));
            }
            return error();
        } catch (Exception e) {
            return error("请求失败");
        }
    }
    @PostMapping("/myCountPiece/appDetail")
    @ResponseBody
    public AjaxResult appSelectDetailList1(@RequestBody CountPiece countPiece){
        try {
            if (countPiece != null) {
                countPiece.appStartPage();
                return AjaxResult.success("请求成功",countPieceService.appSelectDetailList(countPiece));
            }
            return error();
        } catch (Exception e) {
            return error("请求失败");
        }
    }

}
