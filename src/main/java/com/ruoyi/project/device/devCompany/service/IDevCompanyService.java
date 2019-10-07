package com.ruoyi.project.device.devCompany.service;

import com.ruoyi.project.device.devCompany.domain.DevCompany;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司 服务层
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
public interface IDevCompanyService 
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
	 * 查询所以的公司信息
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
	public int updateDevCompany(DevCompany devCompany, HttpServletRequest request);
		
	/**
     * 删除公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevCompanyByIds(String ids);

	public DevCompany selectDevCompanyByComCode(String uniqueCode);

	/**
	 * 通过公司名称查询公司信息
	 * @param comName
	 * @return
	 */
	public DevCompany selectDevCompanyByComName(String comName);

	/**
	 * 校验公司名称是否唯一
	 * @param company 公司信息
	 * @return 结果
	 */
	String checkComNameUnique(DevCompany company);

	/**
	 * 查询公司轮播图片
	 * @param uid 用户id
	 * @return 结果
	 */
    DevCompany appSelectComPicList(Integer uid);

	/**
	 * 查询所以注册公司
	 * @return
	 */
	List<DevCompany> selectComType0Company();

	/**
	 * 升级为VIP
	 * @param id 公司id
	 * @return
	 */
	int toVip(int id);
}
