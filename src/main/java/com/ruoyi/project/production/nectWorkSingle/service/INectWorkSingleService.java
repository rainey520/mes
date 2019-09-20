package com.ruoyi.project.production.nectWorkSingle.service;

import com.ruoyi.project.production.nectWorkSingle.domain.NectWorkSingle;
import java.util.List;

/**
 * 工单单工位关联 服务层
 * 
 * @author sj
 * @date 2019-07-03
 */
public interface INectWorkSingleService 
{
	/**
     * 查询工单单工位关联信息
     * 
     * @param id 工单单工位关联ID
     * @return 工单单工位关联信息
     */
	public NectWorkSingle selectNectWorkSingleById(Integer id);
	
	/**
     * 查询工单单工位关联列表
     * 
     * @param nectWorkSingle 工单单工位关联信息
     * @return 工单单工位关联集合
     */
	public List<NectWorkSingle> selectNectWorkSingleList(NectWorkSingle nectWorkSingle);
	
	/**
     * 新增工单单工位关联
     * 
     * @param nectWorkSingle 工单单工位关联信息
     * @return 结果
     */
	public int insertNectWorkSingle(NectWorkSingle nectWorkSingle);
	
	/**
     * 修改工单单工位关联
     * 
     * @param nectWorkSingle 工单单工位关联信息
     * @return 结果
     */
	public int updateNectWorkSingle(NectWorkSingle nectWorkSingle);
		
	/**
     * 删除工单单工位关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNectWorkSingleByIds(String ids);
	
}
