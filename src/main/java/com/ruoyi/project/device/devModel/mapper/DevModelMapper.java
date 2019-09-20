package com.ruoyi.project.device.devModel.mapper;

import com.ruoyi.project.device.devModel.domain.DevModel;
import java.util.List;	

/**
 * 硬件型号 数据层
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
public interface DevModelMapper 
{
	/**
     * 查询硬件型号信息
     * 
     * @param id 硬件型号ID
     * @return 硬件型号信息
     */
	public DevModel selectDevModelById(Integer id);
	
	/**
     * 查询硬件型号列表
     * 
     * @param devModel 硬件型号信息
     * @return 硬件型号集合
     */
	public List<DevModel> selectDevModelList(DevModel devModel);
	
	/**
     * 新增硬件型号
     * 
     * @param devModel 硬件型号信息
     * @return 结果
     */
	public int insertDevModel(DevModel devModel);
	
	/**
     * 修改硬件型号
     * 
     * @param devModel 硬件型号信息
     * @return 结果
     */
	public int updateDevModel(DevModel devModel);
	
	/**
     * 删除硬件型号
     * 
     * @param id 硬件型号ID
     * @return 结果
     */
	public int deleteDevModelById(Integer id);
	
	/**
     * 批量删除硬件型号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevModelByIds(String[] ids);
	
}