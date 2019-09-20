package com.ruoyi.project.production.workOrderChange.service;

import java.util.Collections;
import java.util.List;

import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.production.workOrderChange.mapper.WorkOrderChangeMapper;
import com.ruoyi.project.production.workOrderChange.domain.WorkOrderChange;

import javax.servlet.http.HttpServletRequest;


/**
 * 工单变更 服务层实现
 * 
 * @author zqm
 * @date 2019-05-16
 */
@Service
public class WorkOrderChangeServiceImpl implements IWorkOrderChangeService 
{
	@Autowired
	private WorkOrderChangeMapper workOrderChangeMapper;

	/**
     * 查询工单变更列表
     * 
     * @param workOrderChange 工单变更信息
     * @return 工单变更集合
     */
	@Override
//	@DataSource(DataSourceType.SLAVE)
	public List<WorkOrderChange> selectWorkOrderChangeList(WorkOrderChange workOrderChange, HttpServletRequest request)
	{
		User u = JwtUtil.getTokenUser(request);
		if(u == null)return Collections.emptyList();
		workOrderChange.setCompanyId(u.getCompanyId());
	    return workOrderChangeMapper.selectWorkOrderChangeList(workOrderChange);
	}

}
