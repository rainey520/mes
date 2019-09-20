package com.ruoyi.project.quality.afterService.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.quality.afterService.domain.AfterService;
import com.ruoyi.project.quality.afterService.domain.AfterServiceItem;
import com.ruoyi.project.quality.afterService.mapper.AfterServiceMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 售后服务 服务层实现
 * 
 * @author sj
 * @date 2019-08-20
 */
@Service
public class AfterServiceServiceImpl implements IAfterServiceService 
{
	@Autowired
	private AfterServiceMapper afterServiceMapper;

	/**
     * 查询售后服务信息
     * 
     * @param id 售后服务ID
     * @return 售后服务信息
     */
    @Override
	public AfterService selectAfterServiceById(Integer id)
	{
	    return afterServiceMapper.selectAfterServiceById(id);
	}
	
	/**
     * 查询售后服务列表
     * 
     * @param afterService 售后服务信息
     * @return 售后服务集合
     */
	@Override
	public List<AfterService> selectAfterServiceList(AfterService afterService)
	{
		User user = JwtUtil.getUser();
		if (user == null) {
		    return Collections.emptyList();
		}
		afterService.setCompanyId(user.getCompanyId());
		return afterServiceMapper.selectAfterServiceList(afterService);
	}

	/**
	 * 通过搜索条件分记录查询售后服务列表
	 *
	 * @param afterService 售后服务信息
	 * @return 售后服务集合
	 */
	@Override
	public List<AfterServiceItem> selectListBySearchInfo(AfterService afterService)
	{
		List<AfterServiceItem> serviceItemList = new ArrayList<>();
		if (StringUtils.isNotEmpty(afterService.getSearchItems())) {
			String[] strings = Convert.toStrArray(afterService.getSearchItems());
			AfterServiceItem serviceItem = null;
			AfterServiceItem item = null;
			for (String searchItem : strings) {
				serviceItem = new AfterServiceItem();
				// 搜索条件
				serviceItem.setSearchItem(searchItem);
				// 查询录入该条件的所有人的信息
				serviceItem.setUserNames(afterServiceMapper.selectListBySearchInfoUserName(searchItem,afterService.getParams()));
				// 查询总共的记录数
				item = afterServiceMapper.selectListByBatchInfo(searchItem,afterService.getParams());
				if (StringUtils.isNotNull(item)) {
					serviceItem.setTotalNum(item.getTotalNum());
					serviceItem.setsTime(item.getsTime());
					serviceItem.seteTime(item.geteTime());
				}
				serviceItemList.add(serviceItem);
			}
		}
		return serviceItemList;
	}
	
    /**
     * 新增售后服务
     * 
     * @param afterService 售后服务信息
     * @return 结果
     */
	@Override
	public int insertAfterService(AfterService afterService)
	{
		User user = JwtUtil.getUser();
		if (user == null) {
		    return 0;
		}
		afterService.setCompanyId(user.getCompanyId());
		afterService.setInputUserId(user.getUserId().intValue());
		afterService.setInputTime(new Date());
	    return afterServiceMapper.insertAfterService(afterService);
	}
	
	/**
     * 修改售后服务
     * 
     * @param afterService 售后服务信息
     * @return 结果
     */
	@Override
	public int updateAfterService(AfterService afterService)
	{
	    return afterServiceMapper.updateAfterService(afterService);
	}

	/**
     * 删除售后服务对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAfterServiceByIds(String ids)
	{
		return afterServiceMapper.deleteAfterServiceByIds(Convert.toStrArray(ids));
	}
}
