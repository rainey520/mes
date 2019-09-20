package com.ruoyi.project.device.devDeviceCounts.service;

import com.ruoyi.common.constant.WorkConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.project.device.devDeviceCounts.domain.DevDataLog;
import com.ruoyi.project.device.devDeviceCounts.domain.DevDeviceCounts;
import com.ruoyi.project.device.devDeviceCounts.mapper.DevDataLogMapper;
import com.ruoyi.project.device.devDeviceCounts.mapper.DevDeviceCountsMapper;
import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.device.devIo.mapper.DevIoMapper;
import com.ruoyi.project.device.devList.domain.DevList;
import com.ruoyi.project.device.devList.mapper.DevListMapper;
import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import com.ruoyi.project.production.devWorkData.mapper.DevWorkDataMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.mapper.ProductionLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * IO上报数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-03-09
 */
@Service
public class DevDeviceCountsServiceImpl implements IDevDeviceCountsService 
{
	@Autowired
	private DevDeviceCountsMapper devDeviceCountsMapper;

	@Autowired
	private DevListMapper devListMapper;

	@Autowired
	private DevIoMapper devIoMapper;

	@Autowired
	private ProductionLineMapper productionLineMapper;

	@Autowired
	private DevDataLogMapper devDataLogMapper;

	@Autowired
	private DevWorkOrderMapper devWorkOrderMapper;

	@Autowired
	private DevWorkDataMapper devWorkDataMapper;

	/**
     * 查询IO上报数据信息
     * 
     * @param id IO上报数据ID
     * @return IO上报数据信息
     */
    @Override
	public DevDeviceCounts selectDevDeviceCountsById(Long id)
	{
	    return devDeviceCountsMapper.selectDevDeviceCountsById(id);
	}
	
	/**
     * 查询IO上报数据列表
     * 
     * @param devDeviceCounts IO上报数据信息
     * @return IO上报数据集合
     */
	@Override
	public List<DevDeviceCounts> selectDevDeviceCountsList(DevDeviceCounts devDeviceCounts)
	{
	    return devDeviceCountsMapper.selectDevDeviceCountsList(devDeviceCounts);
	}
	
    /**
     * 新增IO上报数据
     * 
     * @param devDeviceCounts IO上报数据信息
     * @return 结果
     */
	@Override
	public int insertDevDeviceCounts(DevDeviceCounts devDeviceCounts)
	{
	    return devDeviceCountsMapper.insertDevDeviceCounts(devDeviceCounts);
	}
	
	/**
     * 修改IO上报数据
     * 
     * @param devDeviceCounts IO上报数据信息
     * @return 结果
     */
	@Override
	public int updateDevDeviceCounts(DevDeviceCounts devDeviceCounts)
	{
	    return devDeviceCountsMapper.updateDevDeviceCounts(devDeviceCounts);
	}

	/**
     * 删除IO上报数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevDeviceCountsByIds(String ids)
	{
		return devDeviceCountsMapper.deleteDevDeviceCountsByIds(Convert.toStrArray(ids));
	}

	@Override
	public void insertDevDeviceCounts(String id, Integer[] arr) {
		//1、产线对应的硬件编码是否存在
		DevList devList = devListMapper.selectDevListByDevId(id);
		//不存在 或者硬件已经禁用不记录数据
		if(devList == null || devList.getDeviceStatus() ==0)return;
		int j = 1;
		DevDataLog devDataLog = null;
		for (Integer val : arr) {
			try {
				devDataLog = new DevDataLog();
				devDataLog.setCompanyId(devList.getCompanyId());
				devDataLog.setDataTotal(val);
				devDataLog.setDevId(devList.getId());
				devDataLog.setDelData(0);
				//查询对应的顺序的IO口
				DevIo io = devIoMapper.selectDevIoByDevIdAndOrder(devList.getId(),j);
				devDataLog.setIoOrder(j);
				if(io != null){
					devDataLog.setIoId(io.getId());
					if(io.getLineId() >0){
						//查询对应的产线是否存在
						ProductionLine line = productionLineMapper.selectProductionLineById(io.getLineId());
						if(line != null)devDataLog.setLineId(line.getId());
						//查询对应产线正在工作的工单
						DevWorkOrder workOrder = devWorkOrderMapper.selectWorkByCompandAndLine(devList.getCompanyId(),io.getLineId(),WorkConstants.SING_LINE);
						if(workOrder != null && workOrder.getOperationStatus() == WorkConstants.OPERATION_STATUS_STARTING){
							devDataLog.setWorkId(workOrder.getId());
							//对相关数据进行记录
							DevWorkData workData = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(devList.getCompanyId(),io.getLineId(),workOrder.getId(),
									devList.getId(),io.getId(),WorkConstants.SING_LINE);
							if(workData != null){
								if(io.getIsSign() == 1){
									workData.setIoSign(1);//标记数据 为报表数据
								}else{
									workData.setIoSign(0);
								}
								if(workData.getDataSign() == 1){
									if(val >= workData.getCumulativeNum() ){
										val = val - workData.getCumulativeNum();
									}
									//进行数据初始
									devWorkDataMapper.initWorkData(workData.getDataId(),val>0?val-1:val,workData.getIoSign());
									devDataLog.setDataTotal(val>0?val-1:val);
								}else{
									//记录累计产量
									int total = val - workData.getInitialData();
									devWorkDataMapper.saveTotalWorkData(workData.getDataId(),total,workData.getIoSign());
									workOrder.setCumulativeNumber(val);
									devWorkOrderMapper.updateDevWorkOrder(workOrder);
								}
							}else{
								DevWorkData data = new DevWorkData();
								data.setCompanyId(devList.getCompanyId());
								data.setCreateTime(new Date());
								data.setLineId(io.getLineId());
								data.setDevId(devList.getId());
								data.setDevName(devList.getDeviceName());
								data.setIoId(io.getId());
								data.setIoName(io.getIoName());
								data.setWorkId(workOrder.getId());
								data.setDataSign(0);
								data.setInitialData(val>0?val-1:val);
								data.setIoOrder(j);
								devWorkDataMapper.insertDevWorkData(data);
							}

							//添加日志
							if(workData != null  && workOrder.getOperationStatus() == WorkConstants.OPERATION_STATUS_STARTING && devDataLog.getLineId() != null && devDataLog.getWorkId() != null){
								//查询对应日志上传数据数据
								DevDataLog log = devDataLogMapper.selectLineWorkDevIo(devDataLog.getLineId(),devDataLog.getWorkId(),devDataLog.getDevId(),devDataLog.getIoId(),WorkConstants.SING_LINE);
								if(log != null){
									devDataLog.setDelData(devDataLog.getDataTotal() - log.getDataTotal());
								}
							}
						}
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			devDataLog.setCreateDate(new Date());
			devDataLog.setCreateTime(new Date());
			devDataLogMapper.insertDevDataLog(devDataLog);
			j++;
		}
	}
	
}
