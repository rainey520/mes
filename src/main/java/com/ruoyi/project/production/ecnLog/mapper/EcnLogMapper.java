package com.ruoyi.project.production.ecnLog.mapper;

import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ECN 变更记录 数据层
 * 
 * @author zqm
 * @date 2019-05-16
 */
public interface EcnLogMapper 
{
	/**
	 * 查询ECN 变更记录信息
	 *
	 * @param id ECN 变更记录ID
	 * @return ECN 变更记录信息
	 */
//	@DataSource(value = DataSourceType.SLAVE)
	public EcnLog selectEcnLogById(Integer id);

	/**
     * 查询ECN 变更记录列表
     * 
     * @param ecnLog ECN 变更记录信息
     * @return ECN 变更记录集合
     */
	public List<EcnLog> selectEcnLogList(EcnLog ecnLog);
	
	/**
     * 新增ECN 变更记录
     * 
     * @param ecnLog ECN 变更记录信息
     * @return 结果
     */
	public int insertEcnLog(EcnLog ecnLog);

	/**
	 * 根据产品id查询对公司下产品最新的enc记录
	 * @param companyId 公司id
	 * @param productId 产品id
	 * @return
	 */
//	@DataSource(DataSourceType.SLAVE)
	EcnLog findByCompanyIdAndProductId(@Param("companyId")int companyId,
									   @Param("productId")int productId);

	/**
	 * 修改ECN 变更记录
	 *
	 * @param ecnLog ECN 变更记录信息
	 * @return 结果
	 */
//	@DataSource(value = DataSourceType.SLAVE)
	public int updateEcnLog(EcnLog ecnLog);

	/**
	 * 删除ECN 变更记录
	 *
	 * @param id ECN 变更记录ID
	 * @return 结果
	 */
//	@DataSource(value = DataSourceType.SLAVE)
	public int deleteEcnLogById(Integer id);

	/**
	 * 批量删除ECN 变更记录
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
//	@DataSource(value = DataSourceType.SLAVE)
	public int deleteEcnLogByIds(String[] ids);

	/**
	 * 查询对应ecn有效的变更记录信息
	 * @param ecnType 保存类型
	 * @param saveId 对应类型的id
	 * @return 结果
	 */
    EcnLog selectEcnLogBySaveId(@Param("ecnType") int ecnType,@Param("saveId") int saveId);

	/**
	 * 取消ECN
	 * @param id ecn主键
	 * @return 结果
	 */
	int cancelEcn(@Param("id") int id);

	/**
	 * 更新ECN状态
	 * @param id ecn主键
	 * @param ecnStatus ecn状态
	 * @return 结果
	 */
	int updateEcnStatus(@Param("id") int id, @Param("ecnStatus") int ecnStatus);
}