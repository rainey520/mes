package com.ruoyi.project.quality.mesBatchRule.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRuleDetail;
import com.ruoyi.project.quality.mesBatchRule.mapper.MesBatchRuleDetailMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * MES配置规则明细 服务层实现
 * 
 * @author sj
 * @date 2019-08-09
 */
@Service
public class MesBatchRuleDetailServiceImpl implements IMesBatchRuleDetailService 
{
	@Autowired
	private MesBatchRuleDetailMapper mesBatchRuleDetailMapper;

	@Autowired
	private DevProductListMapper productMapper;

	/**
     * 查询MES配置规则明细信息
     * 
     * @param id MES配置规则明细ID
     * @return MES配置规则明细信息
     */
    @Override
	public MesBatchRuleDetail selectMesBatchRuleDetailById(Integer id)
	{
	    return mesBatchRuleDetailMapper.selectMesBatchRuleDetailById(id);
	}
	
	/**
     * 查询MES配置规则明细列表
     * 
     * @param mesBatchRuleDetail MES配置规则明细信息
     * @return MES配置规则明细集合
     */
	@Override
	public List<MesBatchRuleDetail> selectMesBatchRuleDetailList(MesBatchRuleDetail mesBatchRuleDetail)
	{
	    return mesBatchRuleDetailMapper.selectMesBatchRuleDetailList(mesBatchRuleDetail);
	}
	
    /**
     * 新增MES配置规则明细
     * 
     * @param mesBatchRuleDetail MES配置规则明细信息
     * @return 结果
     */
	@Override
	public int insertMesBatchRuleDetail(MesBatchRuleDetail mesBatchRuleDetail)
	{
	    return mesBatchRuleDetailMapper.insertMesBatchRuleDetail(mesBatchRuleDetail);
	}
	
	/**
     * 修改MES配置规则明细
     * 
     * @param mesBatchRuleDetail MES配置规则明细信息
     * @return 结果
     */
	@Override
	public int updateMesBatchRuleDetail(MesBatchRuleDetail mesBatchRuleDetail)
	{
	    return mesBatchRuleDetailMapper.updateMesBatchRuleDetail(mesBatchRuleDetail);
	}

	/**
     * 删除MES配置规则明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMesBatchRuleDetailByIds(String ids)
	{
		return mesBatchRuleDetailMapper.deleteMesBatchRuleDetailByIds(Convert.toStrArray(ids));
	}

	/**
	 * 通过编码查询MES配置明细
	 * @param partCode 编码
	 * @return 结果
	 */
	@Override
	public List<MesBatchRuleDetail> selectMesBatchRuleDetailListByPcode(String partCode) {
		User user = JwtUtil.getUser();
		if (user == null) {
		    return Collections.emptyList();
		}
		DevProductList product = productMapper.selectDevProductByCode(user.getCompanyId(), partCode);
		if (StringUtils.isNotNull(product) && product.getRuleId() > 0) {
			return mesBatchRuleDetailMapper.selectMesBatchRuleDetailByRuleId(product.getRuleId());
		}
		return Collections.emptyList();
	}

	/**
	 * 通过MES规则id查询规则明细
	 * @param ruleId 规则id
	 * @return 结果
	 */
	@Override
	public List<MesBatchRuleDetail> selectMesBatchRuleByRuleId(int ruleId) {
		return mesBatchRuleDetailMapper.selectMesBatchRuleDetailByRuleId(ruleId);
	}
}
