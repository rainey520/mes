package com.ruoyi.project.quality.mesBatch.mapper;

import com.ruoyi.project.quality.mesBatch.domain.MesBatchDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MES批准追踪详情 数据层
 * 
 * @author sj
 * @date 2019-07-22
 */
public interface MesBatchDetailMapper 
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
     * 删除MES批准追踪详情
     * 
     * @param id MES批准追踪详情ID
     * @return 结果
     */
	public int deleteMesBatchDetailById(Integer id);
	
	/**
     * 批量删除MES批准追踪详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMesBatchDetailByIds(String[] ids);

	/**
	 * 通过mes主表id查询明细列表
	 * @param bId 主表id
	 * @return 结果
	 */
    List<MesBatchDetail> selectMesBatchDetailByBId(@Param("bId") int bId);

	/**
	 * 通过mes主表id删除mes追踪明细信息
	 * @param bId 主表id
	 * @return 结果
	 */
	int deleteMesBatchDetailByBId(@Param("bId") int bId);

	/**
	 * 通过编码批次查询对应批次信息
	 * @param batchCode 编码
	 * @return 结果
	 */
	List<MesBatchDetail> selectMesBatchDetailByBatchCode(@Param("batchCode") String batchCode);

	/**
	 * 查询批次总的记录数
	 * @param batchCode 批次信息
	 * @return 结果
	 */
    int selectMesBatchTotal(String batchCode);

	/**
	 * 排序方式查看mes明细
	 * @param bId 规则主键id
	 * @return 结果
	 */
	List<MesBatchDetail> selectMesBatchDetailByBIdOrder(@Param("bId") Integer bId);

	/**
	 * 通过工单查询工单材料用料总数
	 * @param workCode
	 * @return
	 */
    List<MesBatchDetail> selectMesBatchTotalByWorkCode(@Param("workCode") String workCode);
}