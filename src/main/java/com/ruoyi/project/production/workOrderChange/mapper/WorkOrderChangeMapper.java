package com.ruoyi.project.production.workOrderChange.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.production.workOrderChange.domain.WorkOrderChange;
import java.util.List;	

/**
 * 工单变更 数据层
 * 
 * @author zqm
 * @date 2019-05-16
 */
public interface WorkOrderChangeMapper 
{

	/**
     * 查询工单变更列表
     * 
     * @param workOrderChange 工单变更信息
     * @return 工单变更集合
     */
	public List<WorkOrderChange> selectWorkOrderChangeList(WorkOrderChange workOrderChange);
	
	/**
     * 新增工单变更
     * 
     * @param workOrderChange 工单变更信息
     * @return 结果
     */
//	@DataSource(DataSourceType.SLAVE)
	public int insertWorkOrderChange(WorkOrderChange workOrderChange);

	
}