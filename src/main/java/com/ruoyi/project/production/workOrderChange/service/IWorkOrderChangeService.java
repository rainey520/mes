package com.ruoyi.project.production.workOrderChange.service;

import com.ruoyi.project.production.workOrderChange.domain.WorkOrderChange;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工单变更 服务层
 * 
 * @author zqm
 * @date 2019-05-16
 */
public interface IWorkOrderChangeService 
{
	/**
     * 查询工单变更列表
     * 
     * @param workOrderChange 工单变更信息
     * @return 工单变更集合
     */
	public List<WorkOrderChange> selectWorkOrderChangeList(WorkOrderChange workOrderChange, HttpServletRequest request);

	
}
