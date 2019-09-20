package com.ruoyi.project.quality.mesBatchRule.service;


import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRuleDetail;

import java.util.List;

/**
 * MES配置规则明细 服务层
 * 
 * @author sj
 * @date 2019-08-09
 */
public interface IMesBatchRuleDetailService 
{
	/**
     * 查询MES配置规则明细信息
     * 
     * @param id MES配置规则明细ID
     * @return MES配置规则明细信息
     */
	public MesBatchRuleDetail selectMesBatchRuleDetailById(Integer id);
	
	/**
     * 查询MES配置规则明细列表
     * 
     * @param mesBatchRuleDetail MES配置规则明细信息
     * @return MES配置规则明细集合
     */
	public List<MesBatchRuleDetail> selectMesBatchRuleDetailList(MesBatchRuleDetail mesBatchRuleDetail);
	
	/**
     * 新增MES配置规则明细
     * 
     * @param mesBatchRuleDetail MES配置规则明细信息
     * @return 结果
     */
	public int insertMesBatchRuleDetail(MesBatchRuleDetail mesBatchRuleDetail);
	
	/**
     * 修改MES配置规则明细
     * 
     * @param mesBatchRuleDetail MES配置规则明细信息
     * @return 结果
     */
	public int updateMesBatchRuleDetail(MesBatchRuleDetail mesBatchRuleDetail);
		
	/**
     * 删除MES配置规则明细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMesBatchRuleDetailByIds(String ids);

	/**
	 * 通过编码查询配置明细
	 * @param partCode 编码
	 * @return 结果
	 */
    List<MesBatchRuleDetail> selectMesBatchRuleDetailListByPcode(String partCode);

	/**
	 * 通过规则id查询规则明细
	 * @param ruleId 规则id
	 * @return 结果
	 */
	List<MesBatchRuleDetail> selectMesBatchRuleByRuleId(int ruleId);
}
