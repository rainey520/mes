package com.ruoyi.project.quality.mesBatch.service;


import com.ruoyi.project.quality.mesBatch.domain.MesBatchDetail;

import java.util.List;

/**
 * MES批准追踪详情 服务层
 * 
 * @author sj
 * @date 2019-07-22
 */
public interface IMesBatchDetailService 
{
	/**
     * 查询MES批准追踪详情信息
     * 
     * @param id MES批准追踪详情ID
     * @return MES批准追踪详情信息
     */
	public MesBatchDetail selectMesBatchDetailById(Integer id);
	
	/**
     * 查询MES批准追踪详情列表
     * 
     * @param mesBatchDetail MES批准追踪详情信息
     * @return MES批准追踪详情集合
     */
	public List<MesBatchDetail> selectMesBatchDetailList(MesBatchDetail mesBatchDetail);
	
	/**
     * 新增MES批准追踪详情
     * 
     * @param mesBatchDetail MES批准追踪详情信息
     * @return 结果
     */
	public int insertMesBatchDetail(MesBatchDetail mesBatchDetail);
	
	/**
     * 修改MES批准追踪详情
     * 
     * @param mesBatchDetail MES批准追踪详情信息
     * @return 结果
     */
	public int updateMesBatchDetail(MesBatchDetail mesBatchDetail);
		
	/**
     * 删除MES批准追踪详情信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMesBatchDetailByIds(String ids);

	/**
	 * 通过主表id查询明细信息
	 * @param bId MES主表id
	 * @return 结果
	 */
	public List<MesBatchDetail> selectMesBatchDetailListByBId(int bId);

	/**
	 * 查询搜索的MES批次总的记录数
	 * @param batchCode 批次号
	 */
    int selectMesBatchTotal(String batchCode);
}
