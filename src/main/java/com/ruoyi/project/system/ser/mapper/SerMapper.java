package com.ruoyi.project.system.ser.mapper;

import com.ruoyi.project.system.ser.domain.Ser;
import java.util.List;	

/**
 * 服务器管理 数据层
 * 
 * @author ruoyi
 * @date 2019-06-03
 */
public interface SerMapper 
{
	/**
     * 查询服务器管理信息
     * 
     * @param id 服务器管理ID
     * @return 服务器管理信息
     */
	public Ser selectSerById(Integer id);
	
	/**
     * 查询服务器管理列表
     * 
     * @param ser 服务器管理信息
     * @return 服务器管理集合
     */
	public List<Ser> selectSerList(Ser ser);
	
	/**
     * 新增服务器管理
     * 
     * @param ser 服务器管理信息
     * @return 结果
     */
	public int insertSer(Ser ser);
	
	/**
     * 修改服务器管理
     * 
     * @param ser 服务器管理信息
     * @return 结果
     */
	public int updateSer(Ser ser);
	
	/**
     * 删除服务器管理
     * 
     * @param id 服务器管理ID
     * @return 结果
     */
	public int deleteSerById(Integer id);
	
	/**
     * 批量删除服务器管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSerByIds(String[] ids);
	
}