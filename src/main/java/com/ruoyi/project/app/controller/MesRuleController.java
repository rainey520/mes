package com.ruoyi.project.app.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.app.domain.Product;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.service.IDevProductListService;
import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRule;
import com.ruoyi.project.quality.mesBatchRule.service.IMesBatchRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * mes规则app交互
 * @Author: Rainey
 * @Date: 2019/9/24 8:40
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/mesRule")
public class MesRuleController {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(MesRuleController.class);

    @Autowired
    private IMesBatchRuleService mesBatchRuleService;

    @Autowired
    private IDevProductListService productService;

    /**
     * 查询mes规则列表
     */
    @RequestMapping("/list")
    public AjaxResult list(@RequestBody MesBatchRule mesBatchRule){
        try {
            if (mesBatchRule != null) {
                mesBatchRule.appStartPage();
                List<MesBatchRule> mesBatchRules = mesBatchRuleService.appSelectMesRuleList(mesBatchRule);
                return AjaxResult.success(mesBatchRules);
            }
        } catch (Exception e) {
            LOGGER.error("app查询mes规则列表出现异常：" +e.getMessage());
        }
        return AjaxResult.error();
    }

    /**
     * 查询规则配置的产品列表
     */
    @RequestMapping("/proByRuleId")
    public AjaxResult proByRuleId(@RequestBody DevProductList product){
        try {
            if (product != null && product.getRuleId() != null) {
                return AjaxResult.success(productService.selectDevProductListList(product));
            }
        } catch (Exception e) {
            LOGGER.error("app查询规则id配置的产品信息出现异常：" + e.getMessage());
        }
        return AjaxResult.error();
    }
}
