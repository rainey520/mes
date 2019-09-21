package com.ruoyi.project.quality.mesBatch.controller;

import com.ruoyi.common.constant.MesConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.service.IDevProductListService;
import com.ruoyi.project.quality.mesBatch.domain.MesBatch;
import com.ruoyi.project.quality.mesBatch.domain.MesBatchDetail;
import com.ruoyi.project.quality.mesBatch.domain.MesData;
import com.ruoyi.project.quality.mesBatch.service.IMesBatchDetailService;
import com.ruoyi.project.quality.mesBatch.service.IMesBatchService;
import com.ruoyi.project.quality.mesBatchRule.service.IMesBatchRuleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MES批准追踪 信息操作处理
 *
 * @author sj
 * @date 2019-07-22
 */
@Controller
@RequestMapping("/mes/mesBatch")
public class MesBatchController extends BaseController {
    private String prefix = "quality/mesBatch";
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(MesBatchController.class);

    @Autowired
    private IMesBatchService mesBatchService;

    @Autowired
    private IMesBatchDetailService mesBatchDetailService;

    @Autowired
    private IDevProductListService productListService;

    @Autowired
    private IMesBatchRuleService mesBatchRuleService;


    @RequiresPermissions("mes:mesBatch:view")
    @GetMapping()
    public String mesBatch() {
        return prefix + "/mesBatch";
    }

    /**
     * 查询MES批准追踪列表
     */
    @RequiresPermissions("mes:mesBatch:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MesBatch mesBatch) {
        startPage();
        List<MesBatch> list = mesBatchService.selectMesBatchList(mesBatch);
        return getDataTable(list);
    }


    /**
     * 导出MES批准追踪列表
     */
    @RequiresPermissions("mes:mesBatch:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MesBatch mesBatch) {
        List<MesBatch> list = mesBatchService.selectMesBatchList(mesBatch);
        ExcelUtil<MesBatch> util = new ExcelUtil<MesBatch>(MesBatch.class);
        return util.exportExcel(list, "MES批次追踪数据");
    }

    /**
     * 新增MES批准追踪
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    
    /**
     * 新增保存MES批准追踪
     */
    @RequiresPermissions("mes:mesBatch:add")
    @Log(title = "MES批准追踪", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody MesBatch mesBatch) {
        try {
            return toAjax(mesBatchService.insertMesBatch(mesBatch));
        } catch (BusinessException e) {
            LOGGER.error("新增MES追踪批次出现异常：" + e.getMessage());
            return error(e.getMessage());
        }
    }

    /**
     * 修改MES批准追踪
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        MesBatch mesBatch = mesBatchService.selectMesBatchById(id);
        mmap.put("mesBatch", mesBatch);
        return prefix + "/edit";
    }

    /**
     * 修改保存MES批准追踪
     */
    @RequiresPermissions("mes:mesBatch:add")
    @Log(title = "MES批准追踪", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody MesBatch mesBatch) {
        return toAjax(mesBatchService.updateMesBatch(mesBatch));
    }

    /**
     * 删除MES批准追踪
     */
    @RequiresPermissions("mes:mesBatch:remove")
    @Log(title = "MES批准追踪", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(mesBatchService.deleteMesBatchByIds(ids));
    }

    /**
     * 查看MES主码批次明细
     *
     * @return
     */
    @GetMapping("/showMesDetailByMesCode")
    public String showMesDetailByMesCode(int id, String mesCode, String workCode, ModelMap map) {
        map.put("mesDetails", mesBatchDetailService.selectMesBatchDetailListByBId(id));
        map.put("mesCode", mesCode);
        map.put("workCode", workCode);
        return prefix + "/mesDetail";
    }

    /**
     * 工单生产产品未配置MES进行配置调整页面
     */
    @GetMapping("/woCfMesRule")
    public String woCfMesRule(String proCode, ModelMap map) {
        DevProductList product = productListService.selectProductByCompanyIdAndCode(JwtUtil.getUser().getCompanyId(), proCode);
        if (StringUtils.isNotNull(product) && StringUtils.isNotNull(product.getSign())) {
            map.put("pro", product);
            // 查询对应的MES规则信息
            map.put("mesRules", mesBatchRuleService.selectMesRuleByType(product.getSign()));
        }
        return "production/devWorkOrder/woCfMes";
    }

    /**
     * 通过批次查询mes追溯详情
     */
    @GetMapping("/selectMesData")
    public String selectMesData(String batchCode, ModelMap map) {
        // 查询mes追溯主表
        MesData mesData = mesBatchService.selectMesDataByBatchCode(batchCode);
        // 反追溯
        if (mesData != null && mesData.getMesSign().equals(MesConstants.MES_SIGN_BACK)) {
            map.put("mesCode", batchCode);
            return prefix + "/mesBatch1";
            // 正向追溯
        } else {
            map.put("mesCode", batchCode);
            map.put("mesData", mesData);
            // 无二维码页面
            // return prefix + "/mesData";
            // 有二维码页面
            return prefix + "/mesBatchData";
        }
    }

    /**
     * 删除mes追溯以及追溯明细
     */
    @PostMapping("/removeMesData")
    @ResponseBody
    public AjaxResult removeMesData(int id){
        return toAjax(mesBatchService.removeMesData(id));
    }

    /**
     * 分页查询数据
     */
    @PostMapping("/selectMesInfo")
    @ResponseBody
    public TableDataInfo selectMesInfo(MesBatchDetail mesData) {
        startPage();
        List<MesData> list = mesBatchService.selectMesDataByPage(mesData);
        TableDataInfo dataTable = getDataTable(list);
        // 查询总的条数
        int count = mesBatchDetailService.selectMesBatchTotal(mesData.getBatchCode());
        dataTable.setTotal(count);
        return dataTable;
    }

    /**
     * 输入工单号查询工单相关追溯信息
     */
    @GetMapping("/selectWorkData")
    public String selectWorkData(String workCode,ModelMap map){
        map.put("workCode",workCode);
        map.put("mesData",mesBatchService.selectMesDataByWorkCode(workCode));
        return prefix + "/workMesData";
    }

}
