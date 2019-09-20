package com.ruoyi.project.device.devIo.service;

import com.ruoyi.project.device.devIo.domain.DevIo;
import java.util.List;

/**
 * 硬件IO口配置 服务层
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
public interface IDevIoService 
{
	/**
     * 查询硬件IO口配置信息
     * 
     * @param id 硬件IO口配置ID
     * @return 硬件IO口配置信息
     */
	public DevIo selectDevIoById(Integer id);
	
	/**
     * 查询硬件IO口配置列表
     * 
     * @param devIo 硬件IO口配置信息
     * @return 硬件IO口配置集合
     */
	public List<DevIo> selectDevIoList(DevIo devIo);
	
	/**
     * 新增硬件IO口配置
     * 
     * @param devIo 硬件IO口配置信息
     * @return 结果
     */
	public int insertDevIo(DevIo devIo);
	
	/**
     * 修改硬件IO口配置
     * 
     * @param devIo 硬件IO口配置信息
     * @return 结果
     */
	public int updateDevIo(List<DevIo> devIo);
		
	/**
     * 删除硬件IO口配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevIoByIds(String ids);

	/**
	 * 根据硬件id查询对应的为配置的IO口
	 * @param id
	 * @return
	 */
	List<DevIo> selectDevIoByDevId(int id,int line_id);

	/**
	 * 查询对应产线的IO配置信息
	 * @param line_id 产线编号
	 * @return
	 */
	List<DevIo> selectLineDevIO(int line_id);
	
}
