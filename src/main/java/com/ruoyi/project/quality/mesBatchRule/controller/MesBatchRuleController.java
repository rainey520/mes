package com.ruoyi.project.quality.mesBatchRule.controller;

import com.ruoyi.common.constant.MesConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.service.IDevProductListService;
import com.ruoyi.project.product.materiel.service.IMaterielService;
import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRule;
import com.ruoyi.project.quality.mesBatchRule.service.IMesBatchRuleDetailService;
import com.ruoyi.project.quality.mesBatchRule.service.IMesBatchRuleService;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MES批准追踪规则 信息操作处理
 * 
 * @author sj
 * @date 2019-07-22
 */
@Controller
@RequestMapping("/mes/mesBatchRule")
public class MesBatchRuleController extends BaseController
{
    private String prefix = "quality/mesBatchRule";
	
	@Autowired
	private IMesBatchRuleService mesBatchRuleService;

	@Autowired
	private IDevProductListService productListService;

	@Autowired
	private IMesBatchRuleDetailService ruleDetailService;

	@Autowired
	private IMaterielService materielService;

	@RequiresPermissions("mes:mesBatchRule:view")
	@GetMapping()
	public String mesBatchRule()
	{
		User user = JwtUtil.getUser();
		if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
			return prefix + "/mesBatchRuleEn";
		}
		return prefix + "/mesBatchRule";
	}
	
	/**
	 * 查询MES批准追踪规则列表
	 */
	@RequiresPermissions("mes:mesBatchRule:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MesBatchRule mesBatchRule)
	{
		startPage();
        List<MesBatchRule> list = mesBatchRuleService.selectMesBatchRuleList(mesBatchRule);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出MES批准追踪规则列表
	 */
	@RequiresPermissions("mes:mesBatchRule:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MesBatchRule mesBatchRule)
    {
    	List<MesBatchRule> list = mesBatchRuleService.selectMesBatchRuleList(mesBatchRule);
        ExcelUtil<MesBatchRule> util = new ExcelUtil<MesBatchRule>(MesBatchRule.class);
        return util.exportExcel(list, "mesBatchRule");
    }
	
	/**
	 * 新增MES批准追踪规则
	 */
	@GetMapping("/add")
	public String add()
	{
		User user = JwtUtil.getUser();
		if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
			return prefix + "/add1En";
		}
		return prefix + "/add1";
	}
	
	/**
	 * 新增保存MES批准追踪规则
	 */
	@RequiresPermissions("mes:mesBatchRule:add")
	@Log(title = "MES批准追踪规则", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody MesBatchRule mesBatchRule)
	{
		try {
			return toAjax(mesBatchRuleService.insertMesBatchRule(mesBatchRule));
		} catch (BusinessException e) {
			return error(e.getMessage());
		}
	}

	/**
	 * 修改MES批准追踪规则
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MesBatchRule mesBatchRule = mesBatchRuleService.selectMesBatchRuleById(id);
		mmap.put("mesBatchRule", mesBatchRule);
		User user = JwtUtil.getUser();
		if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
			return prefix + "/edit1En";
		}
		return prefix + "/edit1";
	}

	/**
	 * 查看MES批准追踪规则
	 */
	@GetMapping("/showRuleDetail")
	public String showRuleDetail(Integer id, ModelMap mmap)
	{
		MesBatchRule mesBatchRule = mesBatchRuleService.selectMesBatchRuleById(id);
		mmap.put("mesBatchRule", mesBatchRule);
		User user = JwtUtil.getUser();
		if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
			return prefix + "/ruleDetailEn";
		}
		return prefix + "/ruleDetail";
	}
	
	/**
	 * 修改保存MES批准追踪规则
	 */
	@RequiresPermissions("mes:mesBatchRule:add")
	@Log(title = "MES批准追踪规则", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@RequestBody MesBatchRule mesBatchRule)
	{
		try {
			return toAjax(mesBatchRuleService.updateMesBatchRule(mesBatchRule));
		} catch (BusinessException e) {
			return error(e.getMessage());
		}
	}
	
	/**
	 * 删除MES批准追踪规则
	 */
	@RequiresPermissions("mes:mesBatchRule:remove")
	@Log(title = "MES批准追踪规则", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		try {
			return toAjax(mesBatchRuleService.deleteMesBatchRuleByIds(ids));
		} catch (BusinessException e) {
			return error(e.getMessage());
		}
	}

	/**
	 * 校验追踪规格名称的唯一性
	 */
	@PostMapping("/checkMesRuleNameUnique")
	@ResponseBody
	public String checkMesRuleNameUnique(MesBatchRule mesBatchRule){
		return mesBatchRuleService.checkMesRuleNameUnique(mesBatchRule);
	}

	/**
	 * 查看规则配置明细
	 */
	@GetMapping("/showCfDetail")
	public String showCfDetail(int id,int pType,ModelMap map){
		map.put("ruleId",id);
		map.put("pType",pType);
		map.put("proList",productListService.selectProductAllByCompanyId(pType));
		User user = JwtUtil.getUser();
		if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
			return prefix + "/cfDetailEn";
		}
		return prefix + "/cfDetail";
	}

	/**
	 * 查询规则明细
	 */
	@PostMapping("/selectMesBatchRuleById")
	@ResponseBody
	public AjaxResult selectMesBatchRuleById(int ruleId){
		Map<String,Object> map = new HashMap<>(16);
		map.put("ruleList",ruleDetailService.selectMesBatchRuleByRuleId(ruleId));
		map.put("mesCode", CodeUtils.getMesCode());
		return AjaxResult.success(map);
	}

	/**
	 * 配置规格选择不同类型查询不同数据
	 */
	@PostMapping("/selectPType")
	@ResponseBody
	public AjaxResult selectPType(Integer pType){
		// 查询半成品和物料
		HashMap<String,Object> map = new HashMap<>(16);
		if (pType != null && pType.equals(MesConstants.MES_TYPE_PRO)) {
		    map.put("parList",productListService.selectProAllBySign(MesConstants.MES_TYPE_PARTS));
		    map.put("matList",materielService.selectAllMatByComId());
		} else if (pType != null && pType.equals(MesConstants.MES_TYPE_PARTS)){
			map.put("parList", Collections.emptyList());
			map.put("matList",materielService.selectAllMatByComId());
		}
		return AjaxResult.success(map);
	}

	/**
	 * mes规则有效性修改
	 */
	@RequiresPermissions("mes:mesBatchRule:add")
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(MesBatchRule mesBatchRule) {
		try {
			return toAjax(mesBatchRuleService.changeStatus(mesBatchRule));
		} catch (BusinessException e) {
			return error(e.getMessage());
		}
	}


	/******************************************************************************************************
	 *********************************** app端MES规则交互 **************************************************
	 ******************************************************************************************************/

	@Autowired
	private IDevProductListService devProductListService;

	/**
	 * app端查看MES规则列表
	 */
	@PostMapping("/applist")
	@ResponseBody
	public AjaxResult appSelectList(@RequestBody MesBatchRule mesBatchRule){
		try {
			return AjaxResult.success("请求成功",mesBatchRuleService.selectMesBatchRuleList(mesBatchRule));
		} catch (Exception e) {
			return error("请求失败");
		}
	}

	/**
	 * app查看对应规则配置的产品信息
	 */
	@PostMapping("/appDetailList")
	@ResponseBody
	public AjaxResult appSelectDetailList(@RequestBody DevProductList product){
		try {
			return AjaxResult.success(devProductListService.selectDevProductListList(product));
		} catch (Exception e) {
			return error();
		}
	}
}
