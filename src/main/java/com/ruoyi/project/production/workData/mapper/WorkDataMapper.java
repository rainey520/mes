package com.ruoyi.project.production.workData.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.production.workData.domain.WorkData;

import java.util.List;

/**
 * 工单数据 数据层
 *
 * @author zqm
 * @date 2019-04-16
 */
public interface WorkDataMapper {
    /**
     * 查询工单数据信息
     *
     * @param dataId 工单数据ID
     * @return 工单数据信息
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public WorkData selectWorkDataById(Integer dataId);

    /**
     * 查询工单数据列表
     *
     * @param workData 工单数据信息
     * @return 工单数据集合
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public List<WorkData> selectWorkDataList(WorkData workData);

    /**
     * 新增工单数据
     *
     * @param workData 工单数据信息
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int insertWorkData(WorkData workData);

    /**
     * 修改工单数据
     *
     * @param workData 工单数据信息
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int updateWorkData(WorkData workData);

    /**
     * 删除工单数据
     *
     * @param dataId 工单数据ID
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int deleteWorkDataById(Integer dataId);

    /**
     * 批量删除工单数据
     *
     * @param dataIds 需要删除的数据ID
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int deleteWorkDataByIds(String[] dataIds);

}