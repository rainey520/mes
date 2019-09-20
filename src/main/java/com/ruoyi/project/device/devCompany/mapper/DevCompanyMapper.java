package com.ruoyi.project.device.devCompany.mapper;

import com.ruoyi.project.device.devCompany.domain.DevCompany;

import java.util.List;	

/**
 * 公司 数据层
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
public interface DevCompanyMapper 
{
	/**
     * 查询公司信息
     * 
     * @param id 公司ID
     * @return 公司信息
     */
	public DevCompany selectDevCompanyById(Integer id);
	
	/**
     * 查询公司列表
     * 
     * @param devCompany 公司信息
     * @return 公司集合
     */
	public List<DevCompany> selectDevCompanyList(DevCompany devCompany);

	/**
	 * 查询所有的公司
	 * @return
	 */
	List<DevCompany> selectDevCompanyAll();
	
	/**
     * 新增公司
     * 
     * @param devCompany 公司信息
     * @return 结果
     */
	public int insertDevCompany(DevCompany devCompany);
	
	/**
     * 修改公司
     * 
     * @param devCompany 公司信息
     * @return 结果
     */
	public int updateDevCompany(DevCompany devCompany);


	/**
     * 批量删除公司
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevCompanyByIds(String[] ids);

	public DevCompany selectDevCompanyByComCode(String uniqueCode);

	/**
	 * 通过公司名称查询公司信息
	 * @param comName
	 * @return
	 */
    DevCompany selectDevCompanyByComName(String comName);

	/**
	 * 查询所有注册公司
	 * @return
	 */
	List<DevCompany> selectComType0Company();
}