package com.ruoyi.project.device.devDeviceIo.mapper;

import com.ruoyi.project.device.devDeviceIo.domain.DevDeviceIo;

import java.util.List;	
import java.util.Map;

/**
 * 设备IO 数据层
 * 
 * @author ruoyi
 * @date 2019-02-26
 */
public interface DevDeviceIoMapper 
{
	/**
     * 查询设备IO信息
     * 
     * @param id 设备IOID
     * @return 设备IO信息
     */
	public DevDeviceIo selectDevDeviceIoById(Integer id);
	
	/**
     * 查询设备IO列表
     * 
     * @param devDeviceIo 设备IO信息
     * @return 设备IO集合
     */
	public List<DevDeviceIo> selectDevDeviceIoList(DevDeviceIo devDeviceIo);
	
	/**
     * 新增设备IO
     * 
     * @param devDeviceIo 设备IO信息
     * @return 结果
     */
	public int insertDevDeviceIo(DevDeviceIo devDeviceIo);
	
	/**
     * 修改设备IO
     * 
     * @param devDeviceIo 设备IO信息
     * @return 结果
     */
	public int updateDevDeviceIo(DevDeviceIo devDeviceIo);
	
	/**
     * 删除设备IO
     * 
     * @param id 设备IOID
     * @return 结果
     */
	public int deleteDevDeviceIoById(Integer id);
	
	/**
     * 批量删除设备IO
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevDeviceIoByIds(String[] ids);

	public List<Map<String, Object>> selectDeviceIoByDevId(Integer id);
	
}