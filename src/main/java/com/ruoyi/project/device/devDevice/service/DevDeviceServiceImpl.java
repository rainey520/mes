package com.ruoyi.project.device.devDevice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.device.devDevice.mapper.DevDeviceMapper;
import com.ruoyi.project.device.devDevice.domain.DevDevice;
import com.ruoyi.project.device.devDevice.service.IDevDeviceService;
import com.ruoyi.common.support.Convert;

/**
 * 设备 服务层实现
 * 
 * @author ruoyi
 * @date 2019-02-26
 */
@Service
public class DevDeviceServiceImpl implements IDevDeviceService 
{
	@Autowired
	private DevDeviceMapper devDeviceMapper;

	/**
     * 查询设备信息
     * 
     * @param id 设备ID
     * @return 设备信息
     */
    @Override
	public DevDevice selectDevDeviceById(Integer id)
	{
	    return devDeviceMapper.selectDevDeviceById(id);
	}
	
	/**
     * 查询设备列表
     * 
     * @param devDevice 设备信息
     * @return 设备集合
     */
	@Override
	public List<DevDevice> selectDevDeviceList(DevDevice devDevice)
	{
	    return devDeviceMapper.selectDevDeviceList(devDevice);
	}
	
    /**
     * 新增设备
     * 
     * @param devDevice 设备信息
     * @return 结果
     */
	@Override
	public int insertDevDevice(DevDevice devDevice)
	{
	    return devDeviceMapper.insertDevDevice(devDevice);
	}
	
	/**
     * 修改设备
     * 
     * @param devDevice 设备信息
     * @return 结果
     */
	@Override
	public int updateDevDevice(DevDevice devDevice)
	{
	    return devDeviceMapper.updateDevDevice(devDevice);
	}

	/**
     * 删除设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevDeviceByIds(String ids)
	{
		return devDeviceMapper.deleteDevDeviceByIds(Convert.toStrArray(ids));
	}

	@Override
	public DevDevice selectDevDeviceByIemi(String iemi) {
		return devDeviceMapper.selectDevDeviceByIemi(iemi);
	}
	
}
