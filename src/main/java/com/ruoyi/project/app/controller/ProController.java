package com.ruoyi.project.app.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.app.domain.Product;
import com.ruoyi.project.product.list.service.IDevProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * app产品控制层
 * @Author: Rainey
 * @Date: 2019/9/5 18:04
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/app")
public class ProController {

    @Autowired
    private IDevProductListService productListService;

    /**
     * 查询产品信息
     */
    @RequestMapping("/searchPro")
    @ResponseBody
    public AjaxResult searchProList(@RequestBody Product product){
        if (product != null && StringUtils.isNotEmpty(product.getProductCode())) {
            product.appStartPage();
            List<Product> productList =  productListService.appSearchProList(product);
            return AjaxResult.success(productList);
        }
        return AjaxResult.error();
    }
}
