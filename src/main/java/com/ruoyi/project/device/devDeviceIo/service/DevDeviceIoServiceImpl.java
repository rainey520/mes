package com.ruoyi.project.device.devDeviceIo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.device.devDeviceIo.mapper.DevDeviceIoMapper;
import com.ruoyi.project.device.devDeviceIo.domain.DevDeviceIo;
import com.ruoyi.project.device.devDeviceIo.service.IDevDeviceIoService;
import com.ruoyi.common.support.Convert;

/**
 * 设备IO 服务层实现
 * 
 * @author ruoyi
 * @date 2019-02-26
 */
@Service
public class DevDeviceIoServiceImpl implements IDevDeviceIoService 
{
	@Autowired
	private DevDeviceIoMapper devDeviceIoMapper;

	/**
     * 查询设备IO信息
     * 
     * @param id 设备IOID
     * @return 设备IO信息
     */
    @Override
	public DevDeviceIo selectDevDeviceIoById(Integer id)
	{
	    return devDeviceIoMapper.selectDevDeviceIoById(id);
	}
	
	/**
     * 查询设备IO列表
     * 
     * @param devDeviceIo 设备IO信息
     * @return 设备IO集合
     */
	@Override
	public List<DevDeviceIo> selectDevDeviceIoList(DevDeviceIo devDeviceIo)
	{
	    return devDeviceIoMapper.selectDevDeviceIoList(devDeviceIo);
	}
	
    /**
     * 新增设备IO
     * 
     * @param devDeviceIo 设备IO信息
     * @return 结果
     */
	@Override
	public int insertDevDeviceIo(DevDeviceIo devDeviceIo)
	{
	    return devDeviceIoMapper.insertDevDeviceIo(devDeviceIo);
	}
	
	/**
     * 修改设备IO
     * 
     * @param devDeviceIo 设备IO信息
     * @return 结果
     */
	@Override
	public int updateDevDeviceIo(DevDeviceIo devDeviceIo)
	{
	    return devDeviceIoMapper.updateDevDeviceIo(devDeviceIo);
	}

	/**
     * 删除设备IO对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevDeviceIoByIds(String ids)
	{
		return devDeviceIoMapper.deleteDevDeviceIoByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Map<String, Object>> selectDeviceIoByDevId(Integer id) {
		return devDeviceIoMapper.selectDeviceIoByDevId(id);
	}
	
}
