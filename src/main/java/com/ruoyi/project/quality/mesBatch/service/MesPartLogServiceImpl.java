package com.ruoyi.project.quality.mesBatch.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.project.quality.mesBatch.domain.MesPartLog;
import com.ruoyi.project.quality.mesBatch.mapper.MesPartLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 半成品mes批次追踪记录 服务层实现
 * 
 * @author sj
 * @date 2019-08-10
 */
@Service
public class MesPartLogServiceImpl implements IMesPartLogService 
{
	@Autowired
	private MesPartLogMapper mesPartLogMapper;

	/**
     * 查询半成品mes批次追踪记录信息
     * 
     * @param id 半成品mes批次追踪记录ID
     * @return 半成品mes批次追踪记录信息
     */
    @Override
	public MesPartLog selectMesPartLogById(Integer id)
	{
	    return mesPartLogMapper.selectMesPartLogById(id);
	}
	
	/**
     * 查询半成品mes批次追踪记录列表
     * 
     * @param mesPartLog 半成品mes批次追踪记录信息
     * @return 半成品mes批次追踪记录集合
     */
	@Override
	public List<MesPartLog> selectMesPartLogList(MesPartLog mesPartLog)
	{
	    return mesPartLogMapper.selectMesPartLogList(mesPartLog);
	}
	
    /**
     * 新增半成品mes批次追踪记录
     * 
     * @param mesPartLog 半成品mes批次追踪记录信息
     * @return 结果
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertMesPartLog(MesPartLog mesPartLog)
	{
		try {
			List<MesPartLog> mesPartLogList = mesPartLog.getMesPartLogList();
			// 先删除之前记录
			mesPartLogMapper.deleteMesPartLogByCodes(mesPartLog.getWorkCode(),mesPartLog.getMesCode(),mesPartLog.getPartCode());
			for (MesPartLog partLog : mesPartLogList) {
				partLog.setMesCode(mesPartLog.getMesCode());
				partLog.setWorkCode(mesPartLog.getWorkCode());
				partLog.setcTime(new Date());
				mesPartLogMapper.insertMesPartLog(partLog);
			}
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
     * 修改半成品mes批次追踪记录
     * 
     * @param mesPartLog 半成品mes批次追踪记录信息
     * @return 结果
     */
	@Override
	public int updateMesPartLog(MesPartLog mesPartLog)
	{
	    return mesPartLogMapper.updateMesPartLog(mesPartLog);
	}

	/**
     * 删除半成品mes批次追踪记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMesPartLogByIds(String ids)
	{
		return mesPartLogMapper.deleteMesPartLogByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询半成品MES批次追踪信息
	 * @param workCode 工单号
	 * @param mesCode 主码信息
	 * @param partCode 半成品编码
	 * @return 结果
	 */
	@Override
	public List<MesPartLog> selectMesPartLogListByCode(String workCode, String mesCode, String partCode) {
		return mesPartLogMapper.selectMesPartLogListByCode(workCode,mesCode,partCode);
	}
}
