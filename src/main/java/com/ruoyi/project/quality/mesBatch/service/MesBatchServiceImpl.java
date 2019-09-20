package com.ruoyi.project.quality.mesBatch.service;

import com.ruoyi.common.constant.MesConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.quality.mesBatch.domain.MesBatch;
import com.ruoyi.project.quality.mesBatch.domain.MesBatchDetail;
import com.ruoyi.project.quality.mesBatch.domain.MesData;
import com.ruoyi.project.quality.mesBatch.mapper.MesBatchDetailMapper;
import com.ruoyi.project.quality.mesBatch.mapper.MesBatchMapper;
import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRule;
import com.ruoyi.project.quality.mesBatchRule.mapper.MesBatchRuleMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * MES批准追踪 服务层实现
 *
 * @author sj
 * @date 2019-07-22
 */
@Service
public class MesBatchServiceImpl implements IMesBatchService {
    @Autowired
    private MesBatchMapper mesBatchMapper;

    @Autowired
    private MesBatchRuleMapper mesBatchRuleMapper;

    @Autowired
    private MesBatchDetailMapper mesBatchDetailMapper;

    @Autowired
    private DevWorkOrderMapper workOrderMapper;

    /**
     * 查询MES批准追踪信息
     *
     * @param id MES批准追踪ID
     * @return MES批准追踪信息
     */
    @Override
    public MesBatch selectMesBatchById(Integer id) {
        return mesBatchMapper.selectMesBatchById(id);
    }

    /**
     * 查询MES批准追踪列表
     *
     * @param mesBatch MES批准追踪信息
     * @return MES批准追踪集合
     */
    @Override
    public List<MesBatch> selectMesBatchList(MesBatch mesBatch) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        mesBatch.setCompanyId(user.getCompanyId());
        if (StringUtils.isNotEmpty(mesBatch.getBatchCode())) {
            return mesBatchMapper.selectMesBatchList2(mesBatch);
        }
        return mesBatchMapper.selectMesBatchList(mesBatch);
    }

    /**
     * 新增MES批准追踪
     *
     * @param mesBatch MES批准追踪信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMesBatch(MesBatch mesBatch) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        String mesCode = mesBatch.getMesCode();
        // 查询mes是否存在
        MesBatch mesUnique = mesBatchMapper.selectMesBatchByMesCode(user.getCompanyId(), mesCode);
        if (StringUtils.isNotNull(mesUnique)) {
            if (mesUnique.getWorkCode().equals(mesBatch.getWorkCode())) {
                // 删除之前配置
                mesBatchDetailMapper.deleteMesBatchDetailByBId(mesUnique.getId());
                mesBatchMapper.deleteMesBatchById(mesUnique.getId());
            } else {
                throw new BusinessException("存在相同的MES主码");
            }
        }

        Integer ruleId = mesBatch.getRuleId();
        MesBatchRule mesBatchRule = mesBatchRuleMapper.selectMesBatchRuleById(ruleId);
        if (StringUtils.isNotNull(mesBatchRule)) {
            mesBatch.setRuleName(mesBatchRule.getRuleName());
            mesBatch.setRuleMateriel(mesBatchRule.getMateriels());
        }
        /**
         * 新增MES
         */
        // 初始化生产扫码标记字段
        StringBuilder mesSign = new StringBuilder(MesConstants.MES_PRO_SCAN_INIT);
        mesBatch.setCompanyId(user.getCompanyId());
        mesBatch.setcTime(new Date());
        mesBatchMapper.insertMesBatch(mesBatch);
        List<MesBatchDetail> mesBatchDetailList = mesBatch.getMesBatchDetailList();
        Integer ruleOrder = null;
        String batchCode = null;
        for (MesBatchDetail batchDetail : mesBatchDetailList) {
            batchCode = batchDetail.getBatchCode();
            ruleOrder = batchDetail.getRuleOrder();
            if (StringUtils.isEmpty(batchCode) && ruleOrder != null) {
                mesSign.replace(ruleOrder-1,ruleOrder,MesConstants.MES_PRO_SCAN_NOCONF);
            }
            batchDetail.setbId(mesBatch.getId());
            batchDetail.setcTime(new Date());
            mesBatchDetailMapper.insertMesBatchDetail(batchDetail);
        }
        mesBatch.setMesSign(mesSign.toString());
        return mesBatchMapper.updateMesBatch(mesBatch);
    }

    /**
     * 修改MES批准追踪
     *
     * @param mesBatch MES批准追踪信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMesBatch(MesBatch mesBatch) {
        StringBuffer mesSign = new StringBuffer(mesBatch.getMesSign());
        List<MesBatchDetail> mesBatchDetailList = mesBatch.getMesBatchDetailList();
        Integer ruleOrder = null;
        String proBatchCode = null;
        for (MesBatchDetail mesBatchDetail : mesBatchDetailList) {
            proBatchCode = mesBatchDetail.getProBatchCode();
            ruleOrder = mesBatchDetail.getRuleOrder();
            if (StringUtils.isNotEmpty(proBatchCode) && ruleOrder != null) {
               mesSign.replace(ruleOrder-1,ruleOrder,MesConstants.MES_PRO_SCAN_YES);
            }
            mesBatchDetailMapper.updateMesBatchDetail(mesBatchDetail);
        }
        mesBatch.setMesSign(mesSign.toString());
        return mesBatchMapper.updateMesBatch(mesBatch);
    }

    /**
     * 删除MES批准追踪对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMesBatchByIds(String ids) {
        return mesBatchMapper.deleteMesBatchByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过批次号查询MES追溯详情
     * @param batchCode 批次号
     * @return 结果
     */
    @Override
    public MesData selectMesDataByBatchCode(String batchCode) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return null;
        }
        MesData mesData = new MesData();
        MesBatch mesPro = mesBatchMapper.selectMesBatchByMesCode(user.getCompanyId(), batchCode);
        /**
         * mes主表不为空时，正向追溯
         */
        if (StringUtils.isNotNull(mesPro)) {
            mesData.setProMesCode(batchCode);
            mesData.setMesSign(MesConstants.MES_SIGN_GO);
            // 查询工单相关信息
            DevWorkOrder workProOrder = workOrderMapper.selectWorkOrderByCode(mesPro.getWorkCode());
            if (workProOrder != null) {
                mesData.setWorkCodePro(workProOrder.getWorkorderNumber());
                mesData.setLineNamePro(workProOrder.getLineName());
                mesData.setProductCodePro(workProOrder.getProductCode());
                mesData.setProductNamePro(workProOrder.getProductName());
                mesData.setStartTimePro(workProOrder.getStartTime());
                mesData.setEndTimePro(workProOrder.getEndTime());
                mesData.setWorkNumber(workProOrder.getProductNumber());
            }
            // 查询MES追溯追溯明细
            List<MesBatchDetail> mesProlList = mesBatchDetailMapper.selectMesBatchDetailByBId(mesPro.getId());
            mesData.setMesProList(mesProlList);
            for (MesBatchDetail mesBatchDetail : mesProlList) {
                // 如果MES一级追溯明细为半成品并且半成品已经配置了规则，查询半成品的追踪批次信息
                if (mesBatchDetail.getdType().equals(MesConstants.MES_TYPE_PARTS) && mesBatchDetail.getdTag().equals(MesConstants.MES_TAG_YES)) {
                    MesBatch mesParts = mesBatchMapper.selectMesBatchByMesCode(user.getCompanyId(), mesBatchDetail.getBatchCode());
                    if (StringUtils.isNotNull(mesParts)) {
                        DevWorkOrder workOrderPart = workOrderMapper.selectWorkOrderByCode(mesParts.getWorkCode());
                        if (workOrderPart != null) {
                            mesBatchDetail.setWorkCodePart(workOrderPart.getWorkorderNumber());
                            mesBatchDetail.setLineNamePart(workOrderPart.getLineName());
                            mesBatchDetail.setProductCodePart(workOrderPart.getProductCode());
                            mesBatchDetail.setProductNamePart(workOrderPart.getProductName());
                            mesBatchDetail.setStartTimePart(workOrderPart.getStartTime());
                            mesBatchDetail.setEndTimePart(workOrderPart.getEndTime());
                            mesBatchDetail.setWorkNumberPart(workOrderPart.getProductNumber());
                        }
                        // 查询MES二级追溯明细
                        List<MesBatchDetail> mesPartList = mesBatchDetailMapper.selectMesBatchDetailByBId(mesParts.getId());
                        if (StringUtils.isNotEmpty(mesPartList) && mesPartList.size() >0) {
                            mesBatchDetail.setMesPartList(mesPartList);
                        }
                    }
                }
            }
            return mesData;

        } else {
            mesData.setMesSign(MesConstants.MES_SIGN_BACK);
            // 查询批次明细表
            List<MesBatchDetail> mesBatchDetailList = mesBatchDetailMapper.selectMesBatchDetailByBatchCode(batchCode);
            /**
             * 存在对应批次明细记录，反向追溯
             */
            DevWorkOrder workBackOrder = null;
            List<MesData> mesDataList = new ArrayList<>();
            for (MesBatchDetail mesBatchDetail : mesBatchDetailList) {
                mesData = new MesData();
                // 查询用到该批次的工单信息
                MesBatch mesBack = mesBatchMapper.selectMesBatchById(mesBatchDetail.getbId());
                workBackOrder = workOrderMapper.selectWorkOrderByCode(mesBack.getWorkCode());
                if (workBackOrder != null) {
                    mesData.setProMesCode(mesBack.getMesCode());
                    mesData.setWorkCodePro(workBackOrder.getWorkorderNumber());
                    mesData.setLineNamePro(workBackOrder.getLineName());
                    mesData.setProductCodePro(workBackOrder.getProductCode());
                    mesData.setProductNamePro(workBackOrder.getProductName());
                    mesData.setStartTimePro(workBackOrder.getStartTime());
                    mesData.setEndTimePro(workBackOrder.getEndTime());
                    mesData.setWorkNumber(workBackOrder.getProductNumber());
                }
                // 查询MES追溯追溯明细
                List<MesBatchDetail> mesBackProlList = mesBatchDetailMapper.selectMesBatchDetailByBId(mesBack.getId());
                for (MesBatchDetail mesBackDetail : mesBackProlList) {
                    // 如果MES一级追溯明细为半成品并且半成品已经配置了规则，查询半成品的追踪批次信息
                    if (mesBackDetail.getdType().equals(MesConstants.MES_TYPE_PARTS) && mesBackDetail.getdTag().equals(MesConstants.MES_TAG_YES)) {
                        MesBatch mesBackParts = mesBatchMapper.selectMesBatchByMesCode(user.getCompanyId(), mesBackDetail.getBatchCode());
                        if (StringUtils.isNotNull(mesBackParts)) {
                            DevWorkOrder workOrderBackPart = workOrderMapper.selectWorkOrderByCode(mesBackParts.getWorkCode());
                            if (workOrderBackPart != null) {
                                mesBackDetail.setWorkCodePart(workOrderBackPart.getWorkorderNumber());
                                mesBackDetail.setLineNamePart(workOrderBackPart.getLineName());
                                mesBackDetail.setProductCodePart(workOrderBackPart.getProductCode());
                                mesBackDetail.setProductNamePart(workOrderBackPart.getProductName());
                                mesBackDetail.setStartTimePart(workOrderBackPart.getStartTime());
                                mesBackDetail.setEndTimePart(workOrderBackPart.getEndTime());
                                mesBackDetail.setWorkNumberPart(workOrderBackPart.getProductNumber());
                            }
                            // 查询MES二级追溯明细
                            List<MesBatchDetail> mesBackPartList = mesBatchDetailMapper.selectMesBatchDetailByBId(mesBackParts.getId());
                            if (StringUtils.isNotEmpty(mesBackPartList) && mesBackPartList.size() >0) {
                                mesBackDetail.setMesPartList(mesBackPartList);
                            }
                        }
                    }
                }
                mesData.setMesProList(mesBackProlList);
                mesDataList.add(mesData);
            }
            if (StringUtils.isNotEmpty(mesDataList) && mesDataList.size() > 0) {
                mesData = new MesData();
                mesData.setMesSign(MesConstants.MES_SIGN_BACK);
                mesData.setMesDataList(mesDataList);
                return mesData;
            }
        }
        return null;
    }

    @Override
    public List<MesData> selectMesDataByPage(MesBatchDetail batchDetail) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        // 查询批次明细表
        List<MesBatchDetail> mesBatchDetailList = mesBatchDetailMapper.selectMesBatchDetailByBatchCode(batchDetail.getBatchCode());
        /**
         * 存在对应批次明细记录，反向追溯
         */
        MesData mesData = null;
        DevWorkOrder workBackOrder = null;
        List<MesData> mesDataList = new ArrayList<>();
        for (MesBatchDetail mesBatchDetail : mesBatchDetailList) {
            mesData = new MesData();
            // 查询用到该批次的工单信息
            MesBatch mesBack = mesBatchMapper.selectMesBatchById(mesBatchDetail.getbId());
            workBackOrder = workOrderMapper.selectWorkOrderByCode(mesBack.getWorkCode());
            if (workBackOrder != null) {
                mesData.setProMesCode(mesBack.getMesCode());
                mesData.setWorkCodePro(workBackOrder.getWorkorderNumber());
                mesData.setLineNamePro(workBackOrder.getLineName());
                mesData.setProductCodePro(workBackOrder.getProductCode());
                mesData.setProductNamePro(workBackOrder.getProductName());
                mesData.setStartTimePro(workBackOrder.getStartTime());
                mesData.setEndTimePro(workBackOrder.getEndTime());
                mesData.setWorkNumber(workBackOrder.getProductNumber());
            }
            // 查询MES追溯追溯明细
            List<MesBatchDetail> mesBackProlList = mesBatchDetailMapper.selectMesBatchDetailByBId(mesBack.getId());
            for (MesBatchDetail mesBackDetail : mesBackProlList) {
                // 如果MES一级追溯明细为半成品并且半成品已经配置了规则，查询半成品的追踪批次信息
                if (mesBackDetail.getdType().equals(MesConstants.MES_TYPE_PARTS) && mesBackDetail.getdTag().equals(MesConstants.MES_TAG_YES)) {
                    MesBatch mesBackParts = mesBatchMapper.selectMesBatchByMesCode(user.getCompanyId(), mesBackDetail.getBatchCode());
                    if (StringUtils.isNotNull(mesBackParts)) {
                        DevWorkOrder workOrderBackPart = workOrderMapper.selectWorkOrderByCode(mesBackParts.getWorkCode());
                        if (workOrderBackPart != null) {
                            mesBackDetail.setWorkCodePart(workOrderBackPart.getWorkorderNumber());
                            mesBackDetail.setLineNamePart(workOrderBackPart.getLineName());
                            mesBackDetail.setProductCodePart(workOrderBackPart.getProductCode());
                            mesBackDetail.setProductNamePart(workOrderBackPart.getProductName());
                            mesBackDetail.setStartTimePart(workOrderBackPart.getStartTime());
                            mesBackDetail.setEndTimePart(workOrderBackPart.getEndTime());
                            mesBackDetail.setWorkNumberPart(workOrderBackPart.getProductNumber());
                        }
                        // 查询MES二级追溯明细
                        List<MesBatchDetail> mesBackPartList = mesBatchDetailMapper.selectMesBatchDetailByBId(mesBackParts.getId());
                        if (StringUtils.isNotEmpty(mesBackPartList) && mesBackPartList.size() >0) {
                            mesBackDetail.setMesPartList(mesBackPartList);
                        }
                    }
                }
            }
            mesData.setMesProList(mesBackProlList);
            mesDataList.add(mesData);
        }
        return mesDataList;
    }

    /**
     * 删除mes追溯
     * @param id mesId
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeMesData(int id) {
        // 删除明细
        mesBatchDetailMapper.deleteMesBatchDetailByBId(id);
        return mesBatchMapper.deleteMesBatchById(id);
    }

    /**
     * 通过工单号查询工单追溯信息
     * @param workCode 工单号
     * @return 结果
     */
    @Override
    public MesData selectMesDataByWorkCode(String workCode) {
        List<MesBatch> mesBatchList = mesBatchMapper.selectMesBatchListByWorkCode(workCode);
        if (StringUtils.isEmpty(mesBatchList)) {
            return null;
        }
        MesData mesWork = new MesData();
        // 查询工单基本信息
        DevWorkOrder workOrder = workOrderMapper.selectWorkOrderByCode(workCode);
        if (StringUtils.isNotNull(workOrder)) {
            mesWork.setWorkCodePro(workOrder.getWorkorderNumber());
            mesWork.setLineNamePro(workOrder.getLineName());
            mesWork.setProductCodePro(workOrder.getProductCode());
            mesWork.setProductNamePro(workOrder.getProductName());
            mesWork.setStartTimePro(workOrder.getStartTime());
            mesWork.setEndTimePro(workOrder.getEndTime());
            mesWork.setWorkNumber(workOrder.getProductNumber());
        }
        // 查询原材料总数
        List<MesBatchDetail> matTotalList = mesBatchDetailMapper.selectMesBatchTotalByWorkCode(workCode);
        if (StringUtils.isNotEmpty(matTotalList)) {
            mesWork.setMesProList(matTotalList);
        }
        // 查询MES追溯明细列表
        for (MesBatch mesBatch : mesBatchList) {
            List<MesBatchDetail> mesBatchDetailList = mesBatchDetailMapper.selectMesBatchDetailByBId(mesBatch.getId());
            if (StringUtils.isNotEmpty(mesBatchDetailList)) {
                mesBatch.setMesBatchDetailList(mesBatchDetailList);
            }
        }
        mesWork.setMesBatchList(mesBatchList);
        return mesWork;
    }
}
