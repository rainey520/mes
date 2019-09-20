package com.ruoyi.project.production.ecnLog.service;

import com.ruoyi.common.constant.EcnConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import com.ruoyi.project.production.ecnLog.mapper.EcnLogMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * ECN 变更记录 服务层实现
 *
 * @author zqm
 * @date 2019-05-16
 */
@Service
public class EcnLogServiceImpl implements IEcnLogService {
    @Autowired
    private EcnLogMapper ecnLogMapper;

    @Autowired
    private DevProductListMapper productListMapper;

    @Autowired
    private DevWorkOrderMapper devWorkOrderMapper;

    /**
     * 查询ECN 变更记录信息
     *
     * @param id ECN 变更记录ID
     * @return ECN 变更记录信息
     */
    @Override
    public EcnLog selectEcnLogById(Integer id) {
        return ecnLogMapper.selectEcnLogById(id);
    }

    /**
     * 查询ECN 变更记录列表
     *
     * @param ecnLog ECN 变更记录信息
     * @return ECN 变更记录集合
     */
    @Override
//	@DataSource(DataSourceType.SLAVE)
    public List<EcnLog> selectEcnLogList(EcnLog ecnLog, HttpServletRequest request) {
        User u = JwtUtil.getTokenUser(request);
        if (u == null) return Collections.emptyList();
        ecnLog.setCompanyId(u.getCompanyId());
        return ecnLogMapper.selectEcnLogList(ecnLog);
    }

    /**
     * 新增ECN 变更记录
     *
     * @param ecnLog ECN 变更记录信息
     * @return 结果
     */
    @Override
    public int insertEcnLog(EcnLog ecnLog) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        // 产品ECN
        if (ecnLog.getEcnType().equals(EcnConstants.SAVE_TYPE_PRO) || ecnLog.getEcnType().equals(EcnConstants.SAVE_TYPE_PRO)) {
            DevProductList product = productListMapper.selectDevProductListById(ecnLog.getSaveId());
            if (product == null) {
                return 0;
            }
            ecnLog.setSaveCode(product.getProductCode());
            // 工单ECN
        } else if (ecnLog.getEcnType().equals(EcnConstants.SAVE_TYPE_WORK)) {
            DevWorkOrder workOrder =  devWorkOrderMapper.selectDevWorkOrderById(ecnLog.getSaveId());
            if (workOrder == null) {
                return 0;
            }
            ecnLog.setSaveCode(workOrder.getWorkorderNumber());
        } else {
            return 0;
        }
        ecnLog.setCompanyId(user.getCompanyId());
        ecnLog.setCreateTime(new Date());
        ecnLog.setCreateId(user.getUserId().intValue());
        ecnLog.setCreatePeople(user.getUserName());
        return ecnLogMapper.insertEcnLog(ecnLog);
    }

    /**
     * 关闭ECN 状态变更记录
     *
     * @param type ECN 变更记录信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEcnLog(int type, int save_id) {
        if (type == 1 || type == 3) {
            //产品或者半产品
            DevProductList productList = productListMapper.selectDevProductListById(save_id);
            if (productList == null) return 0;
            productList.setEcnStatus(0);
            productList.setEcnText("");
            productListMapper.updateDevProductList(productList);
            return 1;
        } else if (type == 2) {
            //半成品
        }
        return 0;
    }

    /**
     * 查询对应类型的ecn记录信息
     *
     * @param ecnType ecn保存类型
     * @param saveId  保存id
     * @return 结果
     */
    @Override
    public EcnLog selectEcnLogBySaveId(int ecnType, int saveId) {
        return ecnLogMapper.selectEcnLogBySaveId(ecnType, saveId);
    }

    /**
     * 取消ECN
     *
     * @param id ecn主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelEcn(int id, int ecnType, int saveId) {
        if (ecnType == 1 || ecnType == 3) {
            //产品或者半产品
            DevProductList productList = productListMapper.selectDevProductListById(saveId);
            if (productList == null) return 0;
            productList.setEcnStatus(0);
            productList.setEcnText("");
            productListMapper.updateDevProductList(productList);
        } else if (ecnType == 2) {
            //半成品
        }
        return ecnLogMapper.cancelEcn(id);
    }

    /**
     * 更新Ecn状态
     *
     * @param id        ecn主键
     * @param ecnStatus ecn状态
     * @return 结果
     */
    @Override
    public int updateEcnStatus(int id, int ecnStatus) {
        // ecn审核通过ECN状态更新
        if (EcnConstants.STATUS_NOTZX_.equals(ecnStatus)) {
            EcnLog ecnLog = ecnLogMapper.selectEcnLogById(id);
            if (ecnLog == null) {
                return 0;
            }
            // 产品
            if (EcnConstants.SAVE_TYPE_PRO.equals(ecnLog.getEcnType()) || EcnConstants.SAVE_TYPE_PARTS.equals(ecnLog.getEcnType())) {
                DevProductList product = productListMapper.selectDevProductListById(ecnLog.getSaveId());
                if (product == null) {
                    return 0;
                }
                // 更新工单ECN信息
                devWorkOrderMapper.editCompanyProductWorkOrderEcn(product.getCompanyId(),product.getProductCode());
                product.setEcnStatus(1);
                product.setEcnText(ecnLog.getEcnText());
                productListMapper.updateDevProductList(product);
                // 工单
            } else if (EcnConstants.SAVE_TYPE_WORK.equals(ecnLog.getEcnType())) {

            }
        }
        return ecnLogMapper.updateEcnStatus(id, ecnStatus);
    }
}
