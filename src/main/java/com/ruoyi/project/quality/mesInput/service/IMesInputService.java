package com.ruoyi.project.quality.mesInput.service;

import com.ruoyi.project.quality.mesInput.domain.MesInput;
import java.util.List;

/**
 * mes录入记录 服务层
 * 
 * @author sj
 * @date 2019-11-12
 */
public interface IMesInputService 
{
	/**
     * 查询mes录入记录信息
     * 
     * @param id mes录入记录ID
     * @return mes录入记录信息
     */
	public MesInput selectMesInputById(Integer id);
	
	/**
     * 查询mes录入记录列表
     * 
     * @param mesInput mes录入记录信息
     * @return mes录入记录集合
     */
	public List<MesInput> selectMesInputList(MesInput mesInput);
	
	/**
     * 新增mes录入记录
     * 
     * @param mesInput mes录入记录信息
     * @return 结果
     */
	public int insertMesInput(MesInput mesInput);
	
	/**
     * 修改mes录入记录
     * 
     * @param mesInput mes录入记录信息
     * @return 结果
     */
	public int updateMesInput(MesInput mesInput);
		
	/**
     * 删除mes录入记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMesInputByIds(String ids);
	
}
