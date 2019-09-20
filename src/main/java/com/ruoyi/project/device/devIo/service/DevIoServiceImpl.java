package com.ruoyi.project.device.devIo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.device.devIo.mapper.DevIoMapper;
import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.device.devIo.service.IDevIoService;
import com.ruoyi.common.support.Convert;

/**
 * 硬件IO口配置 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
@Service("devIo")
public class DevIoServiceImpl implements IDevIoService 
{
	@Autowired
	private DevIoMapper devIoMapper;

	/**
     * 查询硬件IO口配置信息
     * 
     * @param id 硬件IO口配置ID
     * @return 硬件IO口配置信息
     */
    @Override
	public DevIo selectDevIoById(Integer id)
	{
	    return devIoMapper.selectDevIoById(id);
	}
	
	/**
     * 查询硬件IO口配置列表
     * 
     * @param devIo 硬件IO口配置信息
     * @return 硬件IO口配置集合
     */
	@Override
	public List<DevIo> selectDevIoList(DevIo devIo)
	{
	    return devIoMapper.selectDevIoList(devIo);
	}
	
    /**
     * 新增硬件IO口配置
     * 
     * @param devIo 硬件IO口配置信息
     * @return 结果
     */
	@Override
	public int insertDevIo(DevIo devIo)
	{
	    return devIoMapper.insertDevIo(devIo);
	}
	
	/**
     * 修改硬件IO口配置
     * 
     * @param devIo 硬件IO口配置信息
     * @return 结果
     */
	@Override
	public int updateDevIo(List<DevIo> devIo)
	{
		for (DevIo io : devIo) {
			devIoMapper.updateDevIo(io);
		}
		return 1;
	}

	/**
     * 删除硬件IO口配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevIoByIds(String ids)
	{
		return devIoMapper.deleteDevIoByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据硬件id查询未配置的IO口信息
	 * @param id
	 * @return
	 */
	@Override
	public List<DevIo> selectDevIoByDevId(int id,int line_id) {
		return devIoMapper.selectDevIoByDevId(id,line_id);
	}

	/**
	 * 查询对应产线的IO配置信息
	 * @param line_id 产线编号
	 * @return
	 */
	@Override
	public List<DevIo> selectLineDevIO(int line_id) {
		return devIoMapper.selectLineDevIO(line_id);
	}
}
