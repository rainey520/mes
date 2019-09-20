package com.ruoyi.project.device.devUserCompany.service;

import com.ruoyi.project.device.devUserCompany.domain.DevUserCompany;
import java.util.List;

/**
 * 用户公司 服务层
 * 
 * @author ruoyi
 * @date 2019-02-02
 */
public interface IDevUserCompanyService 
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
     * 删除用户公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevUserCompanyByIds(String ids);
	
}
