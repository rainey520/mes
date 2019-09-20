package com.ruoyi.project.production.workDayHour.service;

import com.ruoyi.project.production.workDayHour.domain.WorkDayHour;

import java.util.List;

/**
 * 工单各个IO口每天每小时数据 服务层
 *
 * @author zqm
 * @date 2019-04-16
 */
public interface IWorkDayHourService {
    /**
     * 查询工单各个IO口每天每小时数据信息
     *
     * @param hourId 工单各个IO口每天每小时数据ID
     * @return 工单各个IO口每天每小时数据信息
     */
    public WorkDayHour selectWorkDayHourById(Integer hourId);

    /**
     * 查询工单各个IO口每天每小时数据列表
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 工单各个IO口每天每小时数据集合
     */
    public List<WorkDayHour> selectWorkDayHourList(WorkDayHour workDayHour);

    /**
     * 新增工单各个IO口每天每小时数据
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
    public int insertWorkDayHour(WorkDayHour workDayHour);

    /**
     * 修改工单各个IO口每天每小时数据
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
    public int updateWorkDayHour(WorkDayHour workDayHour);

    /**
     * 删除工单各个IO口每天每小时数据信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkDayHourByIds(String ids);

}
