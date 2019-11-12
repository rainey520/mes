package com.ruoyi.project.device.devCompany.service;

import com.ruoyi.common.constant.CompanyConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司 服务层实现
 *
 * @author ruoyi
 * @date 2019-01-31
 */
@Service("devCompany")
public class DevCompanyServiceImpl implements IDevCompanyService {
    @Autowired
    private DevCompanyMapper devCompanyMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询公司信息
     *
     * @param id 公司ID
     * @return 公司信息
     */
    @Override
    public DevCompany selectDevCompanyById(Integer id) {
        return devCompanyMapper.selectDevCompanyById(id);
    }

    /**
     * 查询公司列表
     *
     * @param devCompany 公司信息
     * @return 公司集合
     */
    @Override
    public List<DevCompany> selectDevCompanyList(DevCompany devCompany) {
        return devCompanyMapper.selectDevCompanyList(devCompany);
    }

    @Override
    public List<DevCompany> selectDevCompanyAll() {
        return devCompanyMapper.selectDevCompanyAll();
    }

    /**
     * 新增公司
     *
     * @param devCompany 公司信息
     * @return 结果
     */
    @Override
    public int insertDevCompany(DevCompany devCompany) {
        int row = devCompanyMapper.insertDevCompany(devCompany);
        return row;
    }

    /**
     * 修改公司
     *
     * @param devCompany 公司信息
     * @return 结果
     */
    @Override
    public int updateDevCompany(DevCompany devCompany, HttpServletRequest request) {
        return devCompanyMapper.updateDevCompany(devCompany);
    }

    /**
     * 删除公司对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDevCompanyByIds(String ids) {
        return devCompanyMapper.deleteDevCompanyByIds(Convert.toStrArray(ids));
    }

    @Override
    public DevCompany selectDevCompanyByComCode(String uniqueCode) {
        return devCompanyMapper.selectDevCompanyByComCode(uniqueCode);
    }

    @Override
    public DevCompany selectDevCompanyByComName(String comName) {
        return devCompanyMapper.selectDevCompanyByComName(comName);
    }

    /**
     * 校验公司名称是否存在
     *
     * @param company 公司信息
     * @return 结果
     */
    @Override
    public String checkComNameUnique(DevCompany company) {
        if (StringUtils.isEmpty(company.getComName())) {
            return CompanyConstants.COM_NAME_NOT_UNIQUE;
        }
        DevCompany companyInfo = devCompanyMapper.selectDevCompanyByComName(company.getComName());
        if (!StringUtils.isNull(companyInfo) && !companyInfo.getCompanyId().equals(company.getCompanyId()) ) {
            return CompanyConstants.COM_NAME_NOT_UNIQUE;
        }
        return CompanyConstants.COM_NAME_UNIQUE;
    }

    /**
     * 查询公司轮播图片
     *
     * @param uid 用户id
     * @return 结果
     */
    @Override
    public DevCompany appSelectComPicList(Integer uid) {
        User user = userMapper.selectUserInfoById(uid);
        if (user != null && user.getCompanyId() > 0) {
            DevCompany company = devCompanyMapper.selectDevCompanyById(user.getCompanyId());
            if (StringUtils.isNotNull(company)) {
                return company;
            }
        }
        return null;
    }

    /**
     * 查询所以注册公司
     * @return
     */
    @Override
    public List<DevCompany> selectComType0Company() {
        return devCompanyMapper.selectComType0Company();
    }

    /**
     * 升级为VIP
     * @param id 公司id
     * @return
     */
    @Override
    public int toVip(int id) {
        DevCompany company = devCompanyMapper.selectDevCompanyById(id);
        if(company == null)
        return 0;
        if (company.getSign() == 0) {
            company.setSign(1);
        } else {
            company.setSign(0);
        }
        devCompanyMapper.updateDevCompany(company);
        return 1;
    }
}
