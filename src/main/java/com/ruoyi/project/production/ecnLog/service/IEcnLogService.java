package com.ruoyi.project.production.ecnLog.service;

import com.ruoyi.project.production.ecnLog.domain.EcnLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ECN 变更记录 服务层
 * 
 * @author zqm
 * @date 2019-05-16
 */
public interface IEcnLogService 
{
	/**
	 * 查询ECN 变更记录信息
	 *
	 * @param id ECN 变更记录ID
	 * @return ECN 变更记录信息
	 */
	public EcnLog selectEcnLogById(Integer id);
	/**
     * 查询ECN 变更记录列表
     * 
     * @param ecnLog ECN 变更记录信息
     * @return ECN 变更记录集合
     */
	public List<EcnLog> selectEcnLogList(EcnLog ecnLog, HttpServletRequest request);
	
	/**
     * 新增ECN 变更记录
     * 
     * @param ecnLog ECN 变更记录信息
     * @return 结果
     */
	public int insertEcnLog(EcnLog ecnLog);

	/**
	 * 关闭ECN 状态变更记录
	 *
	 * @param  type 变更记录信息
	 * @return 结果
	 */
	public int updateEcnLog(int type,int save_id);

	/**
	 * 查询对应ECN记录信息
	 * @param ecnType ecn保存类型
 	 * @param saveId 保存id
	 * @return 结果
	 */
	EcnLog selectEcnLogBySaveId(int ecnType, int saveId);

	/**
	 * 取消ECN
	 * @param id ecn主键
	 * @return 结果
	 */
	int cancelEcn(int id,int ecnType,int saveId);

	/**
	 * 更新ECN状态
	 * @param id ecn主键
	 * @param ecnStatus ecn状态
	 * @return 结果
	 */
	int updateEcnStatus(int id, int ecnStatus);
}
