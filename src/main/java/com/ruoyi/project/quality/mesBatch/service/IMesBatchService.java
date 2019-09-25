package com.ruoyi.project.quality.mesBatch.service;


import com.ruoyi.project.quality.mesBatch.domain.MesBatch;
import com.ruoyi.project.quality.mesBatch.domain.MesBatchDetail;
import com.ruoyi.project.quality.mesBatch.domain.MesData;

import java.util.List;

/**
 * MES批准追踪 服务层
 * 
 * @author sj
 * @date 2019-07-22
 */
public interface IMesBatchService 
{
	/**
     * 查询MES批准追踪信息
     * 
     * @param id MES批准追踪ID
     * @return MES批准追踪信息
     */
	public MesBatch selectMesBatchById(Integer id);
	
	/**
     * 查询MES批准追踪列表
     * 
     * @param mesBatch MES批准追踪信息
     * @return MES批准追踪集合
     */
	public List<MesBatch> selectMesBatchList(MesBatch mesBatch);
	
	/**
     * 新增MES批准追踪
     * 
     * @param mesBatch MES批准追踪信息
     * @return 结果
     */
	public int insertMesBatch(MesBatch mesBatch);
	
	/**
     * 修改MES批准追踪
     * 
     * @param mesBatch MES批准追踪信息
     * @return 结果
     */
	public int updateMesBatch(MesBatch mesBatch);
		
	/**
     * 删除MES批准追踪信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMesBatchByIds(String ids);

	/**
	 * 通过批次号查询MES追溯详情
	 * @param batchCode 批次号
	 * @return 结果
	 */
    MesData selectMesDataByBatchCode(String batchCode);

	/**
	 * 分页查询MES数据
	 * @param mesData 结果
	 * @return 结果
	 */
	List<MesData> selectMesDataByPage(MesBatchDetail mesData);

	/**
	 * 删除mes追溯
	 * @param id mesId
	 * @return 结果
	 */
    int removeMesData(int id);

	/**
	 * 通过工单搜索查询工单追溯信息
	 * @param workCode 工单号
	 * @return 结果
	 */
	MesData selectMesDataByWorkCode(String workCode);

	/**
	 * app 端查看mes数据
	 * @param mesBatch mes数据
	 * @return 结果
	 */
    List<MesBatch> appSelectMesBatchList(MesBatch mesBatch);
}
