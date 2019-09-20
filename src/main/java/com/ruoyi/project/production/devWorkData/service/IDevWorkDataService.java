package com.ruoyi.project.production.devWorkData.service;

import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import java.util.List;

/**
 * 工单数据 服务层
 * 
 * @author zqm
 * @date 2019-04-15
 */
public interface IDevWorkDataService 
{
	/**
     * 查询工单数据信息
     * 
     * @param dataId 工单数据ID
     * @return 工单数据信息
     */
	public DevWorkData selectDevWorkDataById(Integer dataId);
	
	/**
     * 查询工单数据列表
     * 
     * @param devWorkData 工单数据信息
     * @return 工单数据集合
     */
	public List<DevWorkData> selectDevWorkDataList(DevWorkData devWorkData);
	
	/**
     * 新增工单数据
     * 
     * @param devWorkData 工单数据信息
     * @return 结果
     */
	public int insertDevWorkData(DevWorkData devWorkData);
	
	/**
     * 修改工单数据
     * 
     * @param devWorkData 工单数据信息
     * @return 结果
     */
	public int updateDevWorkData(DevWorkData devWorkData);
		
	/**
     * 删除工单数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevWorkDataByIds(String ids);
	
}
