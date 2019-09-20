package com.ruoyi.project.device.devUserCompany.mapper;

import com.ruoyi.project.device.devUserCompany.domain.DevUserCompany;
import java.util.List;	

/**
 * 用户公司 数据层
 * 
 * @author ruoyi
 * @date 2019-02-02
 */
public interface DevUserCompanyMapper 
{
	/**
     * 查询用户公司信息
     * 
     * @param companyId 用户公司ID
     * @return 用户公司信息
     */
	public DevUserCompany selectDevUserCompanyById(Integer companyId);
	
	/**
     * 查询用户公司列表
     * 
     * @param devUserCompany 用户公司信息
     * @return 用户公司集合
     */
	public List<DevUserCompany> selectDevUserCompanyList(DevUserCompany devUserCompany);
	
	/**
     * 新增用户公司
     * 
     * @param devUserCompany 用户公司信息
     * @return 结果
     */
	public int insertDevUserCompany(DevUserCompany devUserCompany);
	
	/**
     * 修改用户公司
     * 
     * @param devUserCompany 用户公司信息
     * @return 结果
     */
	public int updateDevUserCompany(DevUserCompany devUserCompany);
	
	/**
     * 删除用户公司
     * 
     * @param companyId 用户公司ID
     * @return 结果
     */
	public int deleteDevUserCompanyById(Integer companyId);
	
	/**
     * 批量删除用户公司
     * 
     * @param companyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevUserCompanyByIds(String[] companyIds);
	
}