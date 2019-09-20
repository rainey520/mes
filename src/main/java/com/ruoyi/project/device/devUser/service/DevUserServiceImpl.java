package com.ruoyi.project.device.devUser.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.project.device.devUser.domain.DevUser;
import com.ruoyi.project.device.devUser.mapper.DevUserMapper;

/**
 * 用户 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Service
public class DevUserServiceImpl implements IDevUserService 
{
	@Autowired
	private DevUserMapper devUserMapper;

	/**
     * 查询用户信息
     * 
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
	public DevUser selectDevUserById(Integer id)
	{
	    return devUserMapper.selectDevUserById(id);
	}
	
	/**
     * 查询用户列表
     * 
     * @param devUser 用户信息
     * @return 用户集合
     */
	@Override
	public List<DevUser> selectDevUserList(DevUser devUser)
	{
	    return devUserMapper.selectDevUserList(devUser);
	}
	
    /**
     * 新增用户
     * 
     * @param devUser 用户信息
     * @return 结果
     */
	@Override
	public int insertDevUser(DevUser devUser)
	{
	    return devUserMapper.insertDevUser(devUser);
	}
	
	/**
     * 修改用户
     * 
     * @param devUser 用户信息
     * @return 结果
     */
	@Override
	public int updateDevUser(DevUser devUser)
	{
	    return devUserMapper.updateDevUser(devUser);
	}

	/**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevUserByIds(String ids)
	{
		return devUserMapper.deleteDevUserByIds(Convert.toStrArray(ids));
	}

	@Override
	public DevUser selectLogin(Map<String,Object> map) {
		return devUserMapper.selectLogin(map);
	}

	@Override
	public DevUser selectByMobile(Map<String, Object> paramMap) {
		return devUserMapper.selectByMobile(paramMap);
	}
	
}
