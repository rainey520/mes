package com.ruoyi.project.production.nectWorkSingle.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.production.nectWorkSingle.mapper.NectWorkSingleMapper;
import com.ruoyi.project.production.nectWorkSingle.domain.NectWorkSingle;
import com.ruoyi.project.production.nectWorkSingle.service.INectWorkSingleService;
import com.ruoyi.common.support.Convert;

/**
 * 工单单工位关联 服务层实现
 * 
 * @author sj
 * @date 2019-07-03
 */
@Service
public class NectWorkSingleServiceImpl implements INectWorkSingleService 
{
	@Autowired
	private NectWorkSingleMapper nectWorkSingleMapper;

	/**
     * 查询工单单工位关联信息
     * 
     * @param id 工单单工位关联ID
     * @return 工单单工位关联信息
     */
    @Override
	public NectWorkSingle selectNectWorkSingleById(Integer id)
	{
	    return nectWorkSingleMapper.selectNectWorkSingleById(id);
	}
	
	/**
     * 查询工单单工位关联列表
     * 
     * @param nectWorkSingle 工单单工位关联信息
     * @return 工单单工位关联集合
     */
	@Override
	public List<NectWorkSingle> selectNectWorkSingleList(NectWorkSingle nectWorkSingle)
	{
	    return nectWorkSingleMapper.selectNectWorkSingleList(nectWorkSingle);
	}
	
    /**
     * 新增工单单工位关联
     * 
     * @param nectWorkSingle 工单单工位关联信息
     * @return 结果
     */
	@Override
	public int insertNectWorkSingle(NectWorkSingle nectWorkSingle)
	{
	    return nectWorkSingleMapper.insertNectWorkSingle(nectWorkSingle);
	}
	
	/**
     * 修改工单单工位关联
     * 
     * @param nectWorkSingle 工单单工位关联信息
     * @return 结果
     */
	@Override
	public int updateNectWorkSingle(NectWorkSingle nectWorkSingle)
	{
	    return nectWorkSingleMapper.updateNectWorkSingle(nectWorkSingle);
	}

	/**
     * 删除工单单工位关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteNectWorkSingleByIds(String ids)
	{
		return nectWorkSingleMapper.deleteNectWorkSingleByIds(Convert.toStrArray(ids));
	}
	
}
