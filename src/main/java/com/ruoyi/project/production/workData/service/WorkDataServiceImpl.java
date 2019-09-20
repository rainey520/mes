package com.ruoyi.project.production.workData.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.production.workData.mapper.WorkDataMapper;
import com.ruoyi.project.production.workData.domain.WorkData;
import com.ruoyi.project.production.workData.service.IWorkDataService;
import com.ruoyi.common.support.Convert;

/**
 * 工单数据 服务层实现
 *
 * @author zqm
 * @date 2019-04-16
 */
@Service
public class WorkDataServiceImpl implements IWorkDataService {
    @Autowired
    private WorkDataMapper workDataMapper;

    /**
     * 查询工单数据信息
     *
     * @param dataId 工单数据ID
     * @return 工单数据信息
     */
    @Override
    public WorkData selectWorkDataById(Integer dataId) {
        return workDataMapper.selectWorkDataById(dataId);
    }

    /**
     * 查询工单数据列表
     *
     * @param workData 工单数据信息
     * @return 工单数据集合
     */
    @Override
    public List<WorkData> selectWorkDataList(WorkData workData) {
        return workDataMapper.selectWorkDataList(workData);
    }

    /**
     * 新增工单数据
     *
     * @param workData 工单数据信息
     * @return 结果
     */
    @Override
    public int insertWorkData(WorkData workData) {
        return workDataMapper.insertWorkData(workData);
    }

    /**
     * 修改工单数据
     *
     * @param workData 工单数据信息
     * @return 结果
     */
    @Override
    public int updateWorkData(WorkData workData) {
        return workDataMapper.updateWorkData(workData);
    }

    /**
     * 删除工单数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkDataByIds(String ids) {
        return workDataMapper.deleteWorkDataByIds(Convert.toStrArray(ids));
    }

}
