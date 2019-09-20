package com.ruoyi.project.quality.mesBatchRule.service;

import com.ruoyi.common.constant.MesConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import com.ruoyi.project.product.materiel.mapper.MaterielMapper;
import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRule;
import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRuleDetail;
import com.ruoyi.project.quality.mesBatchRule.mapper.MesBatchRuleDetailMapper;
import com.ruoyi.project.quality.mesBatchRule.mapper.MesBatchRuleMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * MES批准追踪规则 服务层实现
 *
 * @author sj
 * @date 2019-07-22
 */
@Service("mesRule")
public class MesBatchRuleServiceImpl implements IMesBatchRuleService {
    @Autowired
    private MesBatchRuleMapper mesBatchRuleMapper;

    @Autowired
    private MaterielMapper materielMapper;

    @Autowired
    private DevProductListMapper productListMapper;

    @Autowired
    private MesBatchRuleDetailMapper batchRuleDetailMapper;

    /**
     * 查询MES批准追踪规则信息
     *
     * @param id MES批准追踪规则ID
     * @return MES批准追踪规则信息
     */
    @Override
    public MesBatchRule selectMesBatchRuleById(Integer id) {
        MesBatchRule mesBatchRule = mesBatchRuleMapper.selectMesBatchRuleById(id);
        // MES已配置的规则明细列表
        List<MesBatchRuleDetail> ruleDetailList = batchRuleDetailMapper.selectMesBatchRuleDetailByRuleId(mesBatchRule.getId());
        if (StringUtils.isNotEmpty(ruleDetailList)) {
            mesBatchRule.setTotalNum(ruleDetailList.size());
            mesBatchRule.setMesBatchRuleDetails(ruleDetailList);
        } else {
            mesBatchRule.setTotalNum(0);
        }
        mesBatchRule.setMesBatchRuleDetails(ruleDetailList);
        return mesBatchRule;
    }

    /**
     * 查询MES批准追踪规则列表
     *
     * @param mesBatchRule MES批准追踪规则信息
     * @return MES批准追踪规则集合
     */
    @Override
    public List<MesBatchRule> selectMesBatchRuleList(MesBatchRule mesBatchRule) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        mesBatchRule.setCompanyId(user.getCompanyId());
        return mesBatchRuleMapper.selectMesBatchRuleList(mesBatchRule);
    }

    /**
     * 新增MES批准追踪规则
     *
     * @param mesBatchRule MES批准追踪规则信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMesBatchRule(MesBatchRule mesBatchRule) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        mesBatchRule.setCompanyId(user.getCompanyId());
        mesBatchRule.setcTime(new Date());
        mesBatchRuleMapper.insertMesBatchRule(mesBatchRule);
        List<MesBatchRuleDetail> mesBatchRuleDetails = mesBatchRule.getMesBatchRuleDetails();
        // 编码
        String dCode = null;
        // 半成品或者物料编码
        Integer dType = null;
        DevProductList product = null;
        StringBuilder materiles = new StringBuilder();
        for (MesBatchRuleDetail ruleDetail : mesBatchRuleDetails) {
            dCode = ruleDetail.getdCode();
            materiles.append(dCode).append(",");
            dType = ruleDetail.getdType();
            // 判断类型 1为半成品，2为物料
            if (dType != null && dType.equals(MesConstants.MES_TYPE_PARTS)) {
                product = productListMapper.selectDevProductByCode(user.getCompanyId(), dCode);
                if (StringUtils.isNotNull(product) && product.getRuleId() > 0) {
                    // 设置已配置
                    ruleDetail.setdTag(1);
                }
            }
            ruleDetail.setRuleId(mesBatchRule.getId());
            batchRuleDetailMapper.insertMesBatchRuleDetail(ruleDetail);
        }
        StringBuilder replace = materiles.replace(materiles.lastIndexOf(","), materiles.length(), "");
        mesBatchRule.setMateriels(replace.toString());
        return mesBatchRuleMapper.updateMesBatchRule(mesBatchRule);
    }

    /**
     * 修改MES批准追踪规则
     *
     * @param mesBatchRule MES批准追踪规则信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMesBatchRule(MesBatchRule mesBatchRule) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return 0;
        }
        // MES规则id
        Integer ruleId = mesBatchRule.getId();
        // 查询配置过该规则的产品半成品信息
        List<DevProductList> productList = productListMapper.selectDevProductByRuleId(user.getCompanyId(), ruleId);
        // 删除原始记录
        batchRuleDetailMapper.deleteMesBatchRuleDetailByRuleId(ruleId);
        mesBatchRuleMapper.deleteMesBatchRuleById(ruleId);
        /**
         * 新增记录
         */
        mesBatchRule.setCompanyId(user.getCompanyId());
        mesBatchRule.setcTime(new Date());
        mesBatchRuleMapper.insertMesBatchRule(mesBatchRule);
        List<MesBatchRuleDetail> mesBatchRuleDetails = mesBatchRule.getMesBatchRuleDetails();
        // 编码
        String dCode = null;
        // 半成品或者物料编码
        Integer dType = null;
        DevProductList product = null;
        StringBuilder materiles = new StringBuilder();
        for (MesBatchRuleDetail ruleDetail : mesBatchRuleDetails) {
            dCode = ruleDetail.getdCode();
            materiles.append(dCode).append(",");
            dType = ruleDetail.getdType();
            // 判断类型 1为半成品，2为物料
            if (dType != null && dType.equals(MesConstants.MES_TYPE_PARTS)) {
                product = productListMapper.selectDevProductByCode(user.getCompanyId(), dCode);
                if (StringUtils.isNotNull(product) && product.getRuleId() > 0) {
                    // 设置已配置
                    ruleDetail.setdTag(1);
                }
            }
            ruleDetail.setRuleId(mesBatchRule.getId());
            batchRuleDetailMapper.insertMesBatchRuleDetail(ruleDetail);
        }
        // 更新之前产品或者半成品配置
        for (DevProductList p : productList) {
            p.setRuleId(mesBatchRule.getId());
            productListMapper.updateDevProductList(p);
        }
        StringBuilder replace = materiles.replace(materiles.lastIndexOf(","), materiles.length(), "");
        mesBatchRule.setMateriels(replace.toString());
        return mesBatchRuleMapper.updateMesBatchRule(mesBatchRule);
    }

    /**
     * 删除MES批准追踪规则对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMesBatchRuleByIds(String ids) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        Integer[] ruleIds = Convert.toIntArray(ids);
        for (Integer ruleId : ruleIds) {
            List<DevProductList> productList = productListMapper.selectDevProductByRuleId(user.getCompanyId(), ruleId);
            if (StringUtils.isNotEmpty(productList) && productList.size() > 0) {
                throw new BusinessException("该追踪规则配置了产品，请先删除其关联信息");
            }
            // 删除关联配置信息
            batchRuleDetailMapper.deleteMesBatchRuleDetailByRuleId(ruleId);
        }
        return mesBatchRuleMapper.deleteMesBatchRuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 校验追踪规格的唯一性
     *
     * @param mesBatchRule 追踪规格
     * @return 结果
     */
    @Override
    public String checkMesRuleNameUnique(MesBatchRule mesBatchRule) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return MesConstants.MES_RULENAME_NOT_UNIQUE;
        }
        MesBatchRule mesBatchRuleUnique = mesBatchRuleMapper.selectMesBatchRuleByName(user.getCompanyId(), mesBatchRule.getRuleName());
        if (StringUtils.isNotNull(mesBatchRuleUnique) && !mesBatchRuleUnique.getId().equals(mesBatchRule.getId())) {
            return MesConstants.MES_RULENAME_NOT_UNIQUE;
        }
        return MesConstants.MES_RULENAME_UNIQUE;
    }

    /**
     * 根据规则类型查询对应的规则列表
     *
     * @param type 规则类型 0、产品 1、半成品
     * @return
     */
    @Override
    public List<MesBatchRule> selectMesRuleByType(int type) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return mesBatchRuleMapper.selectMesRuleByType(type,user.getCompanyId());
    }

    /**
     * 查询所有的MES规则信息
     *
     * @return 结果
     */
    @Override
    public List<MesBatchRule> selectAllMesRule() {
        return mesBatchRuleMapper.selectAllMesRule();
    }

}
