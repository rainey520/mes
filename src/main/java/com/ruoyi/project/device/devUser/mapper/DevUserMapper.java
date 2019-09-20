package com.ruoyi.project.device.devUser.mapper;

import com.ruoyi.project.device.devUser.domain.DevUser;

import java.util.List;	
import java.util.Map;

/**
 * 用户 数据层
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
public interface DevUserMapper 
{
	/**
     * 查询用户信息
     * 
     * @param id 用户ID
     * @return 用户信息
     */
	public DevUser selectDevUserById(Integer id);
	
	/**
     * 查询用户列表
     * 
     * @param devUser 用户信息
     * @return 用户集合
     */
	public List<DevUser> selectDevUserList(DevUser devUser);
	
	/**
     * 新增用户
     * 
     * @param devUser 用户信息
     * @return 结果
     */
	public int insertDevUser(DevUser devUser);
	
	/**
     * 修改用户
     * 
     * @param devUser 用户信息
     * @return 结果
     */
	public int updateDevUser(DevUser devUser);
	
	/**
     * 删除用户
     * 
     * @param id 用户ID
     * @return 结果
     */
	public int deleteDevUserById(Integer id);
	
	/**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevUserByIds(String[] ids);

	public DevUser selectLogin(Map<String, Object> map);

	public DevUser selectByMobile(Map<String, Object> paramMap);
	
}