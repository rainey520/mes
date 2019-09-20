package com.ruoyi.project.device.devDevice.service;

import com.ruoyi.project.device.devDevice.domain.DevDevice;

import java.util.List;

/**
 * 设备 服务层
 * 
 * @author ruoyi
 * @date 2019-02-26
 */
public interface IDevDeviceService 
{
	/**
     * 查询设备信息
     * 
     * @param id 设备ID
     * @return 设备信息
     */
	public DevDevice selectDevDeviceById(Integer id);
	
	/**
     * 查询设备列表
     * 
     * @param devDevice 设备信息
     * @return 设备集合
     */
	public List<DevDevice> selectDevDeviceList(DevDevice devDevice);
	
	/**
     * 新增设备
     * 
     * @param devDevice 设备信息
     * @return 结果
     */
	public int insertDevDevice(DevDevice devDevice);
	
	/**
     * 修改设备
     * 
     * @param devDevice 设备信息
     * @return 结果
     */
	public int updateDevDevice(DevDevice devDevice);
		
	/**
     * 删除设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevDeviceByIds(String ids);

	public DevDevice selectDevDeviceByIemi(String iemi);
	
}
