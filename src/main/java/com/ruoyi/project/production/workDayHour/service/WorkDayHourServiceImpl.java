package com.ruoyi.project.production.workDayHour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.production.workDayHour.mapper.WorkDayHourMapper;
import com.ruoyi.project.production.workDayHour.domain.WorkDayHour;
import com.ruoyi.project.production.workDayHour.service.IWorkDayHourService;
import com.ruoyi.common.support.Convert;

/**
 * 工单各个IO口每天每小时数据 服务层实现
 *
 * @author zqm
 * @date 2019-04-16
 */
@Service
public class WorkDayHourServiceImpl implements IWorkDayHourService {
    @Autowired
    private WorkDayHourMapper workDayHourMapper;

    /**
     * 查询工单各个IO口每天每小时数据信息
     *
     * @param hourId 工单各个IO口每天每小时数据ID
     * @return 工单各个IO口每天每小时数据信息
     */
    @Override
    public WorkDayHour selectWorkDayHourById(Integer hourId) {
        return workDayHourMapper.selectWorkDayHourById(hourId);
    }

    /**
     * 查询工单各个IO口每天每小时数据列表
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 工单各个IO口每天每小时数据集合
     */
    @Override
    public List<WorkDayHour> selectWorkDayHourList(WorkDayHour workDayHour) {
        return workDayHourMapper.selectWorkDayHourList(workDayHour);
    }

    /**
     * 新增工单各个IO口每天每小时数据
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
    @Override
    public int insertWorkDayHour(WorkDayHour workDayHour) {
        return workDayHourMapper.insertWorkDayHour(workDayHour);
    }

    /**
     * 修改工单各个IO口每天每小时数据
     *
     * @param workDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
    @Override
    public int updateWorkDayHour(WorkDayHour workDayHour) {
        return workDayHourMapper.updateWorkDayHour(workDayHour);
    }

    /**
     * 删除工单各个IO口每天每小时数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkDayHourByIds(String ids) {
        return workDayHourMapper.deleteWorkDayHourByIds(Convert.toStrArray(ids));
    }

}
