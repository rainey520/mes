package com.ruoyi.project.device.devIo.mapper;

import com.ruoyi.project.device.devIo.domain.DevIo;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * 硬件IO口配置 数据层
 * 
 * @author ruoyi
 * @date 2019-04-09
 */
public interface DevIoMapper 
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
	public int updateDevIo(DevIo devIo);
	
	/**
     * 删除硬件IO口配置
     * 
     * @param id 硬件IO口配置ID
     * @return 结果
     */
	public int deleteDevIoById(Integer id);
	
	/**
     * 批量删除硬件IO口配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevIoByIds(String[] ids);


	/**
	 * 根据硬件id查询对应未配置的IO口数据
	 * @param id
	 * @return
	 */
	List<DevIo> selectDevIoByDevId(@Param("id")int id,@Param("line_id")int line_id);

	/**
	 * 根据硬件id查询对应的顺序的IO数据
	 * @param dev_id 硬件id
	 * @param order_id io 顺序
	 * @return
	 */
	DevIo selectDevIoByDevIdAndOrder(@Param("dev_id")int dev_id,@Param("order_id")int order_id);

	/**
	 * 查询各个产线已经配置的IO口数据
	 * @param line_id 产线编号
	 * @return
	 */
	List<DevIo> selectLineDevIO(@Param("line_id")int line_id);

	/**
	 * 查询对应的IO口数据
	 * @param io_id 编号
	 * @return
	 */
	DevIo selectDevListAndIOByIoId(@Param("io_id")int io_id);

	/**
	 * 查询对应查询的警戒标记IO口数据
	 * @param line_id
	 * @return
	 */
	DevIo selectLineIsSignDevIo(@Param("line_id") int line_id);

	/**
	 * 根据硬件id查询对应的警戒线io口的配置信息
	 * @return
	 */
	DevIo selectByDevId(@Param("dev_id")int dev_id);
}