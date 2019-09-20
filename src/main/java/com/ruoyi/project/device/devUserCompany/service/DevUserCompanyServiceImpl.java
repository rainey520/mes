package com.ruoyi.project.device.devUserCompany.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.device.devUserCompany.mapper.DevUserCompanyMapper;
import com.ruoyi.project.device.devUserCompany.domain.DevUserCompany;
import com.ruoyi.project.device.devUserCompany.service.IDevUserCompanyService;
import com.ruoyi.common.support.Convert;

/**
 * 用户公司 服务层实现
 * 
 * @author ruoyi
 * @date 2019-02-02
 */
@Service
public class DevUserCompanyServiceImpl implements IDevUserCompanyService 
{
	@Autowired
	private DevUserCompanyMapper devUserCompanyMapper;

	/**
     * 查询用户公司信息
     * 
     * @param companyId 用户公司ID
     * @return 用户公司信息
     */
    @Override
	public DevUserCompany selectDevUserCompanyById(Integer companyId)
	{
	    return devUserCompanyMapper.selectDevUserCompanyById(companyId);
	}
	
	/**
     * 查询用户公司列表
     * 
     * @param devUserCompany 用户公司信息
     * @return 用户公司集合
     */
	@Override
	public List<DevUserCompany> selectDevUserCompanyList(DevUserCompany devUserCompany)
	{
	    return devUserCompanyMapper.selectDevUserCompanyList(devUserCompany);
	}
	
    /**
     * 新增用户公司
     * 
     * @param devUserCompany 用户公司信息
     * @return 结果
     */
	@Override
	public int insertDevUserCompany(DevUserCompany devUserCompany)
	{
	    return devUserCompanyMapper.insertDevUserCompany(devUserCompany);
	}
	
	/**
     * 修改用户公司
     * 
     * @param devUserCompany 用户公司信息
     * @return 结果
     */
	@Override
	public int updateDevUserCompany(DevUserCompany devUserCompany)
	{
	    return devUserCompanyMapper.updateDevUserCompany(devUserCompany);
	}

	/**
     * 删除用户公司对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevUserCompanyByIds(String ids)
	{
		return devUserCompanyMapper.deleteDevUserCompanyByIds(Convert.toStrArray(ids));
	}
	
}
