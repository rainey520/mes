package com.ruoyi.project.production.singleWork.service;


import com.ruoyi.project.production.singleWork.domain.SingleWorkOrder;

import java.util.List;

/**
 * 单工位与工单进行配置 服务层
 * 
 * @author sj
 * @date 2019-07-09
 */
public interface ISingleWorkOrderService 
{
	/**
     * 查询单工位与工单进行配置信息
     * 
     * @param id 单工位与工单进行配置ID
     * @return 单工位与工单进行配置信息
     */
	public SingleWorkOrder selectSingleWorkOrderById(Integer id);
	
	/**
     * 查询单工位与工单进行配置列表
     * 
     * @param singleWorkOrder 单工位与工单进行配置信息
     * @return 单工位与工单进行配置集合
     */
	public List<SingleWorkOrder> selectSingleWorkOrderList(SingleWorkOrder singleWorkOrder);
	
	/**
     * 新增单工位与工单进行配置
     * 
     * @param singleWorkOrder 单工位与工单进行配置信息
     * @return 结果
     */
	public int insertSingleWorkOrder(SingleWorkOrder singleWorkOrder) throws Exception;
	
	/**
     * 修改单工位与工单进行配置
     * 
     * @param singleWorkOrder 单工位与工单进行配置信息
     * @return 结果
     */
	public int updateSingleWorkOrder(SingleWorkOrder singleWorkOrder);
		
	/**
     * 删除单工位与工单进行配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSingleWorkOrderByIds(String ids);
	
}
