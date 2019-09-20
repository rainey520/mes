package com.ruoyi.project.quality.mesBatch.mapper;


import com.ruoyi.project.quality.mesBatch.domain.MesPartLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 半成品mes批次追踪记录 数据层
 * 
 * @author sj
 * @date 2019-08-10
 */
public interface MesPartLogMapper 
{
	/**
     * 查询半成品mes批次追踪记录信息
     * 
     * @param id 半成品mes批次追踪记录ID
     * @return 半成品mes批次追踪记录信息
     */
	public MesPartLog selectMesPartLogById(Integer id);
	
	/**
     * 查询半成品mes批次追踪记录列表
     * 
     * @param mesPartLog 半成品mes批次追踪记录信息
     * @return 半成品mes批次追踪记录集合
     */
	public List<MesPartLog> selectMesPartLogList(MesPartLog mesPartLog);
	
	/**
     * 新增半成品mes批次追踪记录
     * 
     * @param mesPartLog 半成品mes批次追踪记录信息
     * @return 结果
     */
	public int insertMesPartLog(MesPartLog mesPartLog);
	
	/**
     * 修改半成品mes批次追踪记录
     * 
     * @param mesPartLog 半成品mes批次追踪记录信息
     * @return 结果
     */
	public int updateMesPartLog(MesPartLog mesPartLog);
	
	/**
     * 删除半成品mes批次追踪记录
     * 
     * @param id 半成品mes批次追踪记录ID
     * @return 结果
     */
	public int deleteMesPartLogById(Integer id);
	
	/**
     * 批量删除半成品mes批次追踪记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMesPartLogByIds(String[] ids);

	/**
	 * 查询半成品编码批次追踪列表
	 * @param workCode 工单号
	 * @param mesCode 主码信息
	 * @param partCode 半成品编码
	 * @return 结果
	 */
	List<MesPartLog> selectMesPartLogListByCode(@Param("workCode") String workCode, @Param("mesCode") String mesCode, @Param("partCode") String partCode);

	/**
	 * 删除半成品ME历史记录
	 * @param workCode 工单号
	 * @param mesCode mes主码
	 * @param partCode 半成品编码
	 * @return 结果
	 */
	int deleteMesPartLogByCodes(@Param("workCode") String workCode, @Param("mesCode") String mesCode, @Param("partCode") String partCode);
}