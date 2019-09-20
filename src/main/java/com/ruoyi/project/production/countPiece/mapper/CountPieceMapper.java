package com.ruoyi.project.production.countPiece.mapper;

import com.ruoyi.project.production.countPiece.domain.CountPiece;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 计件管理数据 数据层
 * 
 * @author sj
 * @date 2019-07-04
 */
public interface CountPieceMapper 
{
	/**
     * 查询计件管理数据信息
     * 
     * @param cpId 计件管理数据ID
     * @return 计件管理数据信息
     */
	public CountPiece selectCountPieceById(Integer cpId);
	
	/**
     * 查询计件管理数据列表
     * 
     * @param countPiece 计件管理数据信息
     * @return 计件管理数据集合
     */
	public List<CountPiece> selectCountPieceList(CountPiece countPiece);
	
	/**
     * 新增计件管理数据
     * 
     * @param countPiece 计件管理数据信息
     * @return 结果
     */
	public int insertCountPiece(CountPiece countPiece);
	
	/**
     * 修改计件管理数据
     * 
     * @param countPiece 计件管理数据信息
     * @return 结果
     */
	public int updateCountPiece(CountPiece countPiece);
	
	/**
     * 删除计件管理数据
     * 
     * @param cpId 计件管理数据ID
     * @return 结果
     */
	public int deleteCountPieceById(Integer cpId);
	
	/**
     * 批量删除计件管理数据
     * 
     * @param cpIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCountPieceByIds(String[] cpIds);

	/**
	 * 查询计件明细列表
	 * @param countPiece 计件对象
	 * @return 结果
	 */
	List<CountPiece> selectCountPieceListDetail(CountPiece countPiece);

	/**
	 * 查看指定日起的计件明细
	 * @param countPiece 计件对象
	 * @return  结果
	 */
	List<CountPiece> selectCountPieceListByDate(CountPiece countPiece);

	/**
	 * 通过工单id查询个人计件信息
	 * @param workId 工单id
	 * @param companyId 公司id
	 * @param cpUserId 归属人id
	 * @param cpDate 计件日期
	 * @return 结果
	 */
    CountPiece selectPieceByWorkIdAndUid(@Param("workId") Integer workId, @Param("companyId") Integer companyId,
										 @Param("cpUserId") Integer cpUserId, @Param("cpDate") Date cpDate);

	/**
	 * 根据工单id查询对应的计件数量
	 * @param workid 工单id
	 * @return
	 */
	CountPiece selectPieceByWorkId(@Param("workid")int workid,@Param("cpUserId") Integer cpUserId);
}