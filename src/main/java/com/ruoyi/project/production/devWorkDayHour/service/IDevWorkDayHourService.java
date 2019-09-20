package com.ruoyi.project.production.devWorkDayHour.service;

import com.ruoyi.project.production.devWorkDayHour.domain.DevWorkDayHour;

import java.util.List;

/**
 * 工单各个IO口每天每小时数据 服务层
 *
 * @author zqm
 * @date 2019-04-16
 */
public interface IDevWorkDayHourService {
    /**
     * 查询工单各个IO口每天每小时数据信息
     *
     * @param hourId 工单各个IO口每天每小时数据ID
     * @return 工单各个IO口每天每小时数据信息
     */
    public DevWorkDayHour selectDevWorkDayHourById(Integer hourId);

    /**
     * 查询工单各个IO口每天每小时数据列表
     *
     * @param devWorkDayHour 工单各个IO口每天每小时数据信息
     * @return 工单各个IO口每天每小时数据集合
     */
    public List<DevWorkDayHour> selectDevWorkDayHourList(DevWorkDayHour devWorkDayHour);

    /**
     * 新增工单各个IO口每天每小时数据
     *
     * @param devWorkDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
    public int insertDevWorkDayHour(DevWorkDayHour devWorkDayHour);

    /**
     * 修改工单各个IO口每天每小时数据
     *
     * @param devWorkDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
    public int updateDevWorkDayHour(DevWorkDayHour devWorkDayHour);

    /**
     * 删除工单各个IO口每天每小时数据信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDevWorkDayHourByIds(String ids);

    /**
     * 每小时定时任务，计算工单数据日志记录
     */
    public void selectCalcDataLog();
}
