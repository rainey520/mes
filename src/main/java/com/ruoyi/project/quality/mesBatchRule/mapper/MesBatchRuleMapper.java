package com.ruoyi.project.quality.mesBatchRule.mapper;

import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;	

/**
 * MES批准追踪规则 数据层
 * 
 * @author sj
 * @date 2019-07-22
 */
public interface MesBatchRuleMapper 
{
	/**
     * 查询MES批准追踪规则信息
     * 
     * @param id MES批准追踪规则ID
     * @return MES批准追踪规则信息
     */
	public MesBatchRule selectMesBatchRuleById(Integer id);
	
	/**
     * 查询MES批准追踪规则列表
     * 
     * @param mesBatchRule MES批准追踪规则信息
     * @return MES批准追踪规则集合
     */
	public List<MesBatchRule> selectMesBatchRuleList(MesBatchRule mesBatchRule);
	
	/**
     * 新增MES批准追踪规则
     * 
     * @param mesBatchRule MES批准追踪规则信息
     * @return 结果
     */
	public int insertMesBatchRule(MesBatchRule mesBatchRule);
	
	/**
     * 修改MES批准追踪规则
     * 
     * @param mesBatchRule MES批准追踪规则信息
     * @return 结果
     */
	public int updateMesBatchRule(MesBatchRule mesBatchRule);
	
	/**
     * 删除MES批准追踪规则
     * 
     * @param id MES批准追踪规则ID
     * @return 结果
     */
	public int deleteMesBatchRuleById(Integer id);
	
	/**
     * 批量删除MES批准追踪规则
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMesBatchRuleByIds(String[] ids);

	/**
	 * 通过规格名称查询MES追踪规格信息
	 * @param companyId 公司id
	 * @param ruleName 规格名称
	 * @return 结果
	 */
	MesBatchRule selectMesBatchRuleByName(@Param("companyId") Integer companyId, @Param("ruleName") String ruleName);

	/**
	 * 根据规则类型查询对应的规则列表
	 * @param type  规则类型 0、产品 1、半成品
	 * @param companyId 公司id
	 * @return 结果
	 */
	List<MesBatchRule> selectMesRuleByType(@Param("type") int type,@Param("companyId") Integer companyId);

	/**
	 * 查询所有的MES规则
	 * @return 结果
	 */
    List<MesBatchRule> selectAllMesRule();
}