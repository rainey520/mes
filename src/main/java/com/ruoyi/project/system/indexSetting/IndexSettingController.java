package com.ruoyi.project.system.indexSetting;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.production.filesource.domain.FileSourceInfo;
import com.ruoyi.project.production.filesource.service.IFileSourceInfoService;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页设置控制层
 *
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.project.system.user.controller
 * @Author: Administrator
 * @Date: 2019/3/30 17:23
 * @Description //TODO
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/indexSetting")
public class IndexSettingController {

    private String prefix = "system/indexSetting";

    @Autowired
    private IDevCompanyService companyService;

    @Autowired
    private IFileSourceInfoService fileInfoService;

    /**
     * 跳转到首页设置界面
     *
     * @return
     */
    @GetMapping("/indexSetting")
    public String userDetail(ModelMap mmap) {
        User user = JwtUtil.getUser();
        DevCompany company = companyService.selectDevCompanyById(user.getCompanyId());
        if (company != null) {
            // 查询该公司的轮播图信息
            List<FileSourceInfo> picList =  fileInfoService.selectFileByComPic(user.getCompanyId(),0);
            if (StringUtils.isNotEmpty(picList)) {
                mmap.put("picList",picList);
            } else {
                mmap.put("picList",null);
            }
        }
        mmap.put("company", company);
        if (UserConstants.LANGUAGE_EN.equals(user.getLangVersion())) {
            return prefix + "/indexSettingEn";
        }
        return prefix + "/indexSetting";
    }
}
