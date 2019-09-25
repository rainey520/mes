package com.ruoyi.project.quality.mesBatch.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.project.quality.mesBatch.domain.MesBatchDetail;
import com.ruoyi.project.quality.mesBatch.mapper.MesBatchDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MES批准追踪详情 服务层实现
 * 
 * @author sj
 * @date 2019-07-22
 */
@Service
public class MesBatchDetailServiceImpl implements IMesBatchDetailService 
{
	@Autowired
	private MesBatchDetailMapper mesBatchDetailMapper;

	/**
     * 查询MES批准追踪详情信息
     * 
     * @param id MES批准追踪详情ID
     * @return MES批准追踪详情信息
     */
    @Override
	public MesBatchDetail selectMesBatchDetailById(Integer id)
	{
	    return mesBatchDetailMapper.selectMesBatchDetailById(id);
	}

	/**
     * 查询MES批准追踪详情列表
     * 
     * @param mesBatchDetail MES批准追踪详情信息
     * @return MES批准追踪详情集合
     */
	@Override
	public List<MesBatchDetail> selectMesBatchDetailList(MesBatchDetail mesBatchDetail)
	{
	    return mesBatchDetailMapper.selectMesBatchDetailList(mesBatchDetail);
	}
	
    /**
     * 新增MES批准追踪详情
     * 
     * @param mesBatchDetail MES批准追踪详情信息
     * @return 结果
     */
	@Override
	public int insertMesBatchDetail(MesBatchDetail mesBatchDetail)
	{
	    return mesBatchDetailMapper.insertMesBatchDetail(mesBatchDetail);
	}
	
	/**
     * 修改MES批准追踪详情
     * 
     * @param mesBatchDetail MES批准追踪详情信息
     * @return 结果
     */
	@Override
	public int updateMesBatchDetail(MesBatchDetail mesBatchDetail)
	{
	    return mesBatchDetailMapper.updateMesBatchDetail(mesBatchDetail);
	}

	/**
     * 删除MES批准追踪详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMesBatchDetailByIds(String ids)
	{
		return mesBatchDetailMapper.deleteMesBatchDetailByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询MES明细
	 * @param bId MES主表id
	 * @return 结果
	 */
	@Override
	public List<MesBatchDetail> selectMesBatchDetailListByBId(int bId) {
		return mesBatchDetailMapper.selectMesBatchDetailByBId(bId);
	}

	/**
	 * 查询批次总的记录数
	 * @param batchCode 批次号
	 * @return 结果
	 */
	@Override
	public int selectMesBatchTotal(String batchCode) {
		return mesBatchDetailMapper.selectMesBatchTotal(batchCode);
	}

	/**
	 * 清空指定mes的数据
	 * @param id 明细id
	 * @return 结果
	 */
	@Override
	public int removeDetailById(Integer id) {
		return mesBatchDetailMapper.removeDetailById(id);
	}
}
