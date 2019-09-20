package com.ruoyi.project.device.devDeviceCounts.mapper;

import com.ruoyi.project.device.devDeviceCounts.domain.DevDeviceCounts;
import java.util.List;	

/**
 * IO上报数据 数据层
 * 
 * @author ruoyi
 * @date 2019-03-09
 */
public interface DevDeviceCountsMapper 
{
	/**
     * 查询IO上报数据信息
     * 
     * @param id IO上报数据ID
     * @return IO上报数据信息
     */
	public DevDeviceCounts selectDevDeviceCountsById(Long id);
	
	/**
     * 查询IO上报数据列表
     * 
     * @param devDeviceCounts IO上报数据信息
     * @return IO上报数据集合
     */
	public List<DevDeviceCounts> selectDevDeviceCountsList(DevDeviceCounts devDeviceCounts);
	
	/**
     * 新增IO上报数据
     * 
     * @param devDeviceCounts IO上报数据信息
     * @return 结果
     */
	public int insertDevDeviceCounts(DevDeviceCounts devDeviceCounts);
	
	/**
     * 修改IO上报数据
     * 
     * @param devDeviceCounts IO上报数据信息
     * @return 结果
     */
	public int updateDevDeviceCounts(DevDeviceCounts devDeviceCounts);
	
	/**
     * 删除IO上报数据
     * 
     * @param id IO上报数据ID
     * @return 结果
     */
	public int deleteDevDeviceCountsById(Long id);
	
	/**
     * 批量删除IO上报数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevDeviceCountsByIds(String[] ids);
	
}