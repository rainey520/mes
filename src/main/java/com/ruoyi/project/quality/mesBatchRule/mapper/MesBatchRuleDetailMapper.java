package com.ruoyi.project.quality.mesBatchRule.mapper;

import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRuleDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MES配置规则明细 数据层
 *
 * @author sj
 * @date 2019-08-09
 */
public interface MesBatchRuleDetailMapper {
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
     * 删除MES配置规则明细
     *
     * @param id MES配置规则明细ID
     * @return 结果
     */
    public int deleteMesBatchRuleDetailById(Integer id);

    /**
     * 批量删除MES配置规则明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMesBatchRuleDetailByIds(String[] ids);

    /**
     * 通过规则主id删除规则明细信息
     *
     * @param ruleId 规则id
     * @return 结果
     */
    int deleteMesBatchRuleDetailByRuleId(@Param("ruleId") Integer ruleId);

    /**
     * 通过规则主表id查询规则明细列表
     *
     * @param ruleId 规则id
     * @return 结果
     */
    List<MesBatchRuleDetail> selectMesBatchRuleDetailByRuleId(@Param("ruleId") Integer ruleId);

    /**
     * 更新MES规则明细配置
     * @param dCode 关联编码
     * @param dTag 半成品是否配置标记 0未配置，1已配置
     * @return 结果
     */
    int updateMesBatchRuleDetailTag(@Param("dCode") String dCode, @Param("dTag") Integer dTag);
}