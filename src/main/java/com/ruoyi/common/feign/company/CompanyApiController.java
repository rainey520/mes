package com.ruoyi.common.feign.company;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/c")
public class CompanyApiController {
    @Autowired
    private IDevCompanyService companyService;
    /***
     * 初始化公司数据
     * @param company 公司数据
     * @return
     */
    @RequestMapping("/init")
    public AjaxResult initCompanyInfo(@RequestBody DevCompany company){
        try {
            company.setCreateTime(new Date());
            companyService.insertDevCompany(company);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }
}
