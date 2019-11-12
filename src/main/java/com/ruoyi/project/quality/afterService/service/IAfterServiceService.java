package com.ruoyi.project.quality.afterService.service;

import com.ruoyi.project.quality.afterService.domain.AfterService;
import com.ruoyi.project.quality.afterService.domain.AfterServiceItem;

import java.util.List;

/**
 * 售后服务 服务层
 * 
 * @author sj
 * @date 2019-08-20
 */
public interface IAfterServiceService 
{
	/**
     * 查询售后服务信息
     * 
     * @param id 售后服务ID
     * @return 售后服务信息
     */
	public AfterService selectAfterServiceById(Integer id);
	
	/**
     * 查询售后服务列表
     * 
     * @param afterService 售后服务信息
     * @return 售后服务集合
     */
	public List<AfterService> selectAfterServiceList(AfterService afterService);
	
	/**
     * 新增售后服务
     * 
     * @param afterService 售后服务信息
     * @return 结果
     */
	public int insertAfterService(AfterService afterService);
	
	/**
     * 修改售后服务
     * 
     * @param afterService 售后服务信息
     * @return 结果
     */
	public int updateAfterService(AfterService afterService);
		
	/**
     * 删除售后服务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAfterServiceByIds(String ids);

	/**
	 * 分记录搜索售后服务列表
	 * @param afterService 售后服务信息
	 * @return 结果
	 */
	List<AfterServiceItem> selectListBySearchInfo(AfterService afterService);

	/**
	 * 删除售后录入
	 * @param id 录入id
	 * @return 结果
	 */
	int deleteAfterServiceById(Integer id);

}
