package com.ruoyi.project.production.countPiece.service;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.production.countPiece.domain.CountPiece;
import com.ruoyi.project.production.countPiece.mapper.CountPieceMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 计件管理数据 服务层实现
 *
 * @author sj
 * @date 2019-07-04
 */
@Service
public class CountPieceServiceImpl implements ICountPieceService {
    @Autowired
    private CountPieceMapper countPieceMapper;

    /**
     * 查询计件管理数据信息
     *
     * @param cpId 计件管理数据ID
     * @return 计件管理数据信息
     */
    @Override
    public CountPiece selectCountPieceById(Integer cpId) {
        return countPieceMapper.selectCountPieceById(cpId);
    }

    /**
     * 查询计件管理数据列表
     *
     * @param countPiece 计件管理数据信息
     * @return 计件管理数据集合
     */
    @Override
    public List<CountPiece> selectCountPieceList(CountPiece countPiece) {
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        if (user == null) {
            return Collections.emptyList();
        }
        countPiece.setCompanyId(user.getCompanyId());
        return countPieceMapper.selectCountPieceList(countPiece);
    }

    /**
     * 新增计件管理数据
     *
     * @param countPiece 计件管理数据信息
     * @return 结果
     */
    @Override
    public int insertCountPiece(CountPiece countPiece) {
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        CountPiece piece = countPieceMapper.selectPieceByWorkIdAndUid(countPiece.getWorkId(), user.getCompanyId(), countPiece.getCpUserId(), countPiece.getCpDate());
        if (StringUtils.isNotNull(piece)) {
            piece.setCpNumber(piece.getCpNumber() + countPiece.getCpNumber());
            piece.setCpBadNumber(piece.getCpBadNumber() + countPiece.getCpBadNumber());
            piece.setTotalPrice(countPiece.getWorkPrice() * (piece.getCpNumber() - piece.getCpBadNumber()));
            return countPieceMapper.updateCountPiece(piece);
        } else {
            countPiece.setCompanyId(user.getCompanyId());
            float totalPrice = (countPiece.getCpNumber() - countPiece.getCpBadNumber()) * countPiece.getWorkPrice();
            countPiece.setTotalPrice(totalPrice);
            return countPieceMapper.insertCountPiece(countPiece);
        }
    }

    /**
     * 修改计件管理数据
     *
     * @param countPiece 计件管理数据信息
     * @return 结果
     */
    @Override
    public int updateCountPiece(CountPiece countPiece) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        CountPiece pieceById = countPieceMapper.selectCountPieceById(countPiece.getCpId());
        if (!pieceById.getCpBadNumber().equals(countPiece.getCpBadNumber())) {
            Integer cpBadNumber = countPiece.getCpBadNumber() < 0 ? 0 : countPiece.getCpBadNumber();
            countPiece.setTotalPrice((countPiece.getCpNumber() - cpBadNumber) * countPiece.getWorkPrice());
            countPiece.setCpLastUpdate(new Date());
            countPiece.setCpLastId(user.getUserId().intValue());
        }
        if (countPiece.getCpBadNumber() != null) {
            countPiece.setTotalPrice(countPiece.getWorkPrice() * (countPiece.getCpNumber() - countPiece.getCpBadNumber()));
        }
        return countPieceMapper.updateCountPiece(countPiece);
    }

    /**
     * 删除计件管理数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCountPieceByIds(String ids) {
        return countPieceMapper.deleteCountPieceByIds(Convert.toStrArray(ids));
    }

    /**
     * 查看计件明细列表
     *
     * @param countPiece 计件对象
     * @return 结果
     */
    @Override
    public List<CountPiece> selectCountPieceListDetail(CountPiece countPiece) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        countPiece.setCompanyId(user.getCompanyId());
        return countPieceMapper.selectCountPieceListDetail(countPiece);
    }

    /**
     * 查看指定日期的计件明细
     *
     * @param countPiece 计件对象
     * @return 结果
     */
    @Override
    public List<CountPiece> selectCountPieceListByDate(CountPiece countPiece) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        countPiece.setCompanyId(user.getCompanyId());
        return countPieceMapper.selectCountPieceListByDate(countPiece);
    }

    /**
     * app端查询计件明细信息
     * @param countPiece 计件对象
     * @return 结果
     */
    @Override
    public List<CountPiece> appSelectDetailList(CountPiece countPiece) {
        return countPieceMapper.selectCountPieceListDetail(countPiece);
    }
}
