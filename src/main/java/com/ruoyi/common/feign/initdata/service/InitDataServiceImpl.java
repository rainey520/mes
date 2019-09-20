package com.ruoyi.common.feign.initdata.service;

import com.ruoyi.common.feign.initdata.domain.InitData;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class InitDataServiceImpl implements IInitDataService {

    @Autowired
    private IDevCompanyService devCompanyService;

    @Autowired
    private IUserService userService;
    /**
     * 初始化数据
     * @param initData 初始化数据
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int initData(InitData initData) throws Exception {
        System.out.println(initData);
        //新增公司信息
        DevCompany company = initData.getCompany();
        company.setCreateTime(new Date());
        devCompanyService.insertDevCompany(company);
        //新增用户信息
        User user = initData.getUser();
        user.setCreateTime(new Date());
        userService.register(user);
        return 1;
    }
}
