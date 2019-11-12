package com.ruoyi.project.quality.mesInput.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.quality.mesInput.mapper.MesInputMapper;
import com.ruoyi.project.quality.mesInput.domain.MesInput;
import com.ruoyi.project.quality.mesInput.service.IMesInputService;
import com.ruoyi.common.support.Convert;

/**
 * mes录入记录 服务层实现
 * 
 * @author sj
 * @date 2019-11-12
 */
@Service
public class MesInputServiceImpl implements IMesInputService 
{
	@Autowired
	private MesInputMapper mesInputMapper;

	/**
     * 查询mes录入记录信息
     * 
     * @param id mes录入记录ID
     * @return mes录入记录信息
     */
    @Override
	public MesInput selectMesInputById(Integer id)
	{
	    return mesInputMapper.selectMesInputById(id);
	}
	
	/**
     * 查询mes录入记录列表
     * 
     * @param mesInput mes录入记录信息
     * @return mes录入记录集合
     */
	@Override
	public List<MesInput> selectMesInputList(MesInput mesInput)
	{
	    return mesInputMapper.selectMesInputList(mesInput);
	}
	
    /**
     * 新增mes录入记录
     * 
     * @param mesInput mes录入记录信息
     * @return 结果
     */
	@Override
	public int insertMesInput(MesInput mesInput)
	{
	    return mesInputMapper.insertMesInput(mesInput);
	}
	
	/**
     * 修改mes录入记录
     * 
     * @param mesInput mes录入记录信息
     * @return 结果
     */
	@Override
	public int updateMesInput(MesInput mesInput)
	{
	    return mesInputMapper.updateMesInput(mesInput);
	}

	/**
     * 删除mes录入记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMesInputByIds(String ids)
	{
		return mesInputMapper.deleteMesInputByIds(Convert.toStrArray(ids));
	}
	
}
