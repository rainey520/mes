package com.ruoyi.project.production.devWorkData.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.production.devWorkData.mapper.DevWorkDataMapper;
import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import com.ruoyi.project.production.devWorkData.service.IDevWorkDataService;
import com.ruoyi.common.support.Convert;

/**
 * 工单数据 服务层实现
 * 
 * @author zqm
 * @date 2019-04-15
 */
@Service
public class DevWorkDataServiceImpl implements IDevWorkDataService 
{
	@Autowired
	private DevWorkDataMapper devWorkDataMapper;

	/**
     * 查询工单数据信息
     * 
     * @param dataId 工单数据ID
     * @return 工单数据信息
     */
    @Override
	public DevWorkData selectDevWorkDataById(Integer dataId)
	{
	    return devWorkDataMapper.selectDevWorkDataById(dataId);
	}
	
	/**
     * 查询工单数据列表
     * 
     * @param devWorkData 工单数据信息
     * @return 工单数据集合
     */
	@Override
	public List<DevWorkData> selectDevWorkDataList(DevWorkData devWorkData)
	{
	    return devWorkDataMapper.selectDevWorkDataList(devWorkData);
	}
	
    /**
     * 新增工单数据
     * 
     * @param devWorkData 工单数据信息
     * @return 结果
     */
	@Override
	public int insertDevWorkData(DevWorkData devWorkData)
	{
	    return devWorkDataMapper.insertDevWorkData(devWorkData);
	}
	
	/**
     * 修改工单数据
     * 
     * @param devWorkData 工单数据信息
     * @return 结果
     */
	@Override
	public int updateDevWorkData(DevWorkData devWorkData)
	{
	    return devWorkDataMapper.updateDevWorkData(devWorkData);
	}

	/**
     * 删除工单数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevWorkDataByIds(String ids)
	{
		return devWorkDataMapper.deleteDevWorkDataByIds(Convert.toStrArray(ids));
	}
	
}
