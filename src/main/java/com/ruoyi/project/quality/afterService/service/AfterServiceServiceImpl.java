package com.ruoyi.project.quality.afterService.service;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.quality.afterService.domain.AfterService;
import com.ruoyi.project.quality.afterService.domain.AfterServiceItem;
import com.ruoyi.project.quality.afterService.mapper.AfterServiceMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

	@Autowired
	private DevCompanyMapper companyMapper;

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
		setTimeValid(afterService);
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
		User user = JwtUtil.getUser();
		if (user == null) {
			return Collections.emptyList();
		}
		afterService.setCompanyId(user.getCompanyId());
		setTimeValid(afterService);
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
				serviceItem.setUserNames(afterServiceMapper.selectListBySearchInfoUserName(user.getCompanyId(),searchItem,afterService.getParams()));
				afterService.setInputBatchInfo(searchItem);
				// 查询总共的记录数
				item = afterServiceMapper.selectListByBatchInfo(afterService);
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

	/**
	 * 删除录入信息
	 * @param id 录入id
	 * @return 结果
	 */
	@Override
	public int deleteAfterServiceById(Integer id) {
		return afterServiceMapper.deleteAfterServiceById(id);
	}

	/**
	 * 设置检索有效期
	 * @param afterService
	 */
	private void setTimeValid(AfterService afterService) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 检索开始时间
		String searchBeginTime = (String) afterService.getParams().get("beginTime");
		Date bTime = DateUtils.parseDate(searchBeginTime);

		// 检索结束时间
		String searchEndTime = (String) afterService.getParams().get("endTime");
		Date eTime = DateUtils.parseDate(searchEndTime);

		// 查询公司是否为会员
		DevCompany devCompany = companyMapper.selectDevCompanyById(JwtUtil.getUser().getCompanyId());
		if (devCompany != null && devCompany.getSign() != 1) {
			// 有效时间，非会员检索时间为13个月，会员为永久
			Date validDate = DateUtils.stepMonth(new Date(),-13);
			String validDateStr = format.format(validDate);
			if (bTime == null) {
				searchBeginTime = validDateStr;
			}
			if (bTime != null) {
				int i = bTime.compareTo(validDate);
				if (i == -1) {
					searchBeginTime = validDateStr;
					throw new BusinessException("超过有效搜索时间");
				}
			}

			if (eTime != null) {
				int i = eTime.compareTo(validDate);
				if (i == -1) {
					searchEndTime = validDateStr;
				}
			}
		}
		afterService.setSearchBeginTime(searchBeginTime);
		afterService.setSearchEndTime(searchEndTime);
	}
}
