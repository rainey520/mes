package com.ruoyi.project.quality.mesBatch.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.quality.mesBatch.domain.MesBatchDetail;
import com.ruoyi.project.quality.mesBatch.domain.MesPartLog;
import com.ruoyi.project.quality.mesBatch.service.IMesBatchDetailService;
import com.ruoyi.project.quality.mesBatch.service.IMesPartLogService;
import com.ruoyi.project.quality.mesBatchRule.service.IMesBatchRuleDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MES批准追踪详情 信息操作处理
 * 
 * @author sj
 * @date 2019-07-22
 */
@Controller
@RequestMapping("/mes/mesBatchDetail")
public class MesBatchDetailController extends BaseController
{
    private String prefix = "quality/mesBatchDetail";
	
	@Autowired
	private IMesBatchDetailService mesBatchDetailService;

	@Autowired
	private IMesPartLogService mesPartLogService;

	@Autowired
	private IMesBatchRuleDetailService mesBatchRuleDetailService;
	
	@RequiresPermissions("mes:mesBatchDetail:view")
	@GetMapping()
	public String mesBatchDetail()
	{
	    return prefix + "/mesBatchDetail";
	}
	
	/**
	 * 查询MES批准追踪详情列表
	 */
	@RequiresPermissions("mes:mesBatchDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MesBatchDetail mesBatchDetail)
	{
		startPage();
        List<MesBatchDetail> list = mesBatchDetailService.selectMesBatchDetailList(mesBatchDetail);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出MES批准追踪详情列表
	 */
	@RequiresPermissions("mes:mesBatchDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MesBatchDetail mesBatchDetail)
    {
    	List<MesBatchDetail> list = mesBatchDetailService.selectMesBatchDetailList(mesBatchDetail);
        ExcelUtil<MesBatchDetail> util = new ExcelUtil<MesBatchDetail>(MesBatchDetail.class);
        return util.exportExcel(list, "mesBatchDetail");
    }
	
	/**
	 * 新增MES批准追踪详情
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存MES批准追踪详情
	 */
	@RequiresPermissions("mes:mesBatchDetail:add")
	@Log(title = "MES批准追踪详情", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MesBatchDetail mesBatchDetail)
	{		
		return toAjax(mesBatchDetailService.insertMesBatchDetail(mesBatchDetail));
	}

	/**
	 * 修改MES批准追踪详情
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MesBatchDetail mesBatchDetail = mesBatchDetailService.selectMesBatchDetailById(id);
		mmap.put("mesBatchDetail", mesBatchDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存MES批准追踪详情
	 */
	@RequiresPermissions("mes:mesBatchDetail:edit")
	@Log(title = "MES批准追踪详情", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MesBatchDetail mesBatchDetail)
	{		
		return toAjax(mesBatchDetailService.updateMesBatchDetail(mesBatchDetail));
	}
	
	/**
	 * 删除MES批准追踪详情
	 */
	@RequiresPermissions("mes:mesBatchDetail:remove")
	@Log(title = "MES批准追踪详情", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mesBatchDetailService.deleteMesBatchDetailByIds(ids));
	}

	/**
	 * 查看物料批次二维码
	 */
	@GetMapping("/showCode")
	public String showCode(String code,ModelMap map){
		map.put("code",code);
		return "mes/mesBatch/code";
	}

	/**
	 * MES数据列表查看半成品追踪数据
	 */
	@GetMapping("/partCofMes")
	public String partCofMes1(String mesCode,String partCode,String workCode,ModelMap map){
		map.put("mesCode",mesCode);
		map.put("partCode",partCode);
		map.put("workCode",workCode);
		List<MesPartLog> mesPartLogList = mesPartLogService.selectMesPartLogListByCode(workCode, mesCode, partCode);
		if (StringUtils.isNotEmpty(mesPartLogList) && mesPartLogList.size() > 0) {
			map.put("mesPartLog", mesPartLogList);
		} else {
			map.put("mesRuleList",mesBatchRuleDetailService.selectMesBatchRuleDetailListByPcode(partCode));
		}
		return "mes/mesBatch/partCofMes";
	}
	
}
