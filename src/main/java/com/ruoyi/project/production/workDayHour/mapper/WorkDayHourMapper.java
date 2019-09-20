package com.ruoyi.project.production.workDayHour.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.production.workDayHour.domain.WorkDayHour;

import java.util.List;

/**
 * 工单各个IO口每天每小时数据 数据层
 *
 * @author zqm
 * @date 2019-04-16
 */
public interface WorkDayHourMapper {
    /**
     * 查询工单各个IO口每天每小时数据信息
     *
     * @param hourId 工单各个IO口每天每小时数据ID
     * @return 工单各个IO口每天每小时数据信息
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public WorkDayHour selectWorkDayHourById(Integer hourId);

    /**
     * 查询工单各个IO口每天每小时数据列表
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 工单各个IO口每天每小时数据集合
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public List<WorkDayHour> selectWorkDayHourList(WorkDayHour workDayHour);

    /**
     * 新增工单各个IO口每天每小时数据
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int insertWorkDayHour(WorkDayHour workDayHour);

    /**
     * 修改工单各个IO口每天每小时数据
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int updateWorkDayHour(WorkDayHour workDayHour);

    /**
     * 删除工单各个IO口每天每小时数据
     *
     * @param hourId 工单各个IO口每天每小时数据ID
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int deleteWorkDayHourById(Integer hourId);

    /**
     * 批量删除工单各个IO口每天每小时数据
     *
     * @param hourIds 需要删除的数据ID
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int deleteWorkDayHourByIds(String[] hourIds);

}