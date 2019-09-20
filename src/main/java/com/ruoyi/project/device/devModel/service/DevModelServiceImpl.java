package com.ruoyi.project.device.devModel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.device.devModel.mapper.DevModelMapper;
import com.ruoyi.project.device.devModel.domain.DevModel;
import com.ruoyi.project.device.devModel.service.IDevModelService;
import com.ruoyi.common.support.Convert;

/**
 * 硬件型号 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
@Service("devModel")
public class DevModelServiceImpl implements IDevModelService 
{
	@Autowired
	private DevModelMapper devModelMapper;

	/**
     * 查询硬件型号信息
     * 
     * @param id 硬件型号ID
     * @return 硬件型号信息
     */
    @Override
	public DevModel selectDevModelById(Integer id)
	{
	    return devModelMapper.selectDevModelById(id);
	}
	
	/**
     * 查询硬件型号列表
     * 
     * @param devModel 硬件型号信息
     * @return 硬件型号集合
     */
	@Override
	public List<DevModel> selectDevModelList(DevModel devModel)
	{
	    return devModelMapper.selectDevModelList(devModel);
	}
	
    /**
     * 新增硬件型号
     * 
     * @param devModel 硬件型号信息
     * @return 结果
     */
	@Override
	public int insertDevModel(DevModel devModel)
	{
	    return devModelMapper.insertDevModel(devModel);
	}
	
	/**
     * 修改硬件型号
     * 
     * @param devModel 硬件型号信息
     * @return 结果
     */
	@Override
	public int updateDevModel(DevModel devModel)
	{
	    return devModelMapper.updateDevModel(devModel);
	}

	/**
     * 删除硬件型号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevModelByIds(String ids)
	{
		return devModelMapper.deleteDevModelByIds(Convert.toStrArray(ids));
	}
	
}
