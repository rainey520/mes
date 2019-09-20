package com.ruoyi.project.device.devList.mapper;

import com.ruoyi.project.device.devList.domain.DevList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 硬件 数据层
 *
 * @author ruoyi
 * @date 2019-04-08
 */
public interface DevListMapper
{
	/**
     * 查询硬件信息
     *
     * @param id 硬件ID
     * @return 硬件信息
     */
	public DevList selectDevListById(Integer id);

	/**
	 * 根据硬件编号查询
	 * @param device_id
	 * @return
	 */
	DevList selectDevListByDevId(@Param("device_id") String device_id);

	/**
     * 查询硬件列表
     *
     * @param devList 硬件信息
     * @return 硬件集合
     */
	public List<DevList> selectDevListList(DevList devList);

	/**
     * 新增硬件
     *
     * @param devList 硬件信息
     * @return 结果
     */
	public int insertDevList(DevList devList);

	/**
     * 修改硬件
     *
     * @param devList 硬件信息
     * @return 结果
     */
	public int updateDevList(DevList devList);

	/**
	 * 修改硬件配置标记
	 * @param devList
	 * @return
	 */
	int updateDevSign(DevList devList);

	/**
	 * 用户添加硬件信息
	 * @param devList 硬件信息
	 * @return
	 */
	int addSave(DevList devList);

	/**
     * 删除硬件
     *
     * @param id 硬件ID
     * @return 结果
     */
	public int deleteDevListById(Integer id);

	/**
     * 批量删除硬件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevListByIds(String[] ids);

	/**
	 * 查询对应的硬件信息和对应的IO数据
	 * @param id
	 * @return
	 */
	DevList selectDevListAndIoById(@Param("id")int id);
	/**
	 * 获取前20个未配置的硬件编号
	 * @return
	 */
	List<String> selectNoConfigDevice();

	/**
	 * 查询所以的硬件信息
	 * @return
	 */
	List<DevList> selectAll(@Param("company_id")int company_id);

	/**
	 * 根据硬件编号查询对应的硬件是否存在
	 * @param code 硬件编号
	 * @return
	 */
	DevList selectDevListByCode(@Param("code")String code);

	/**
	 * 查询公司所以未配置
	 * @return
	 */
	List<DevList> selectDevNotConfig(@Param("companyId") Integer companyId);

	/**
	 * 通过硬件id更新是否被配置标记状态
	 * @param companyId 公司id
	 * @param id 硬件id
	 * @param sign 是否被配置标记
	 * @param devType 硬件配置对象标记车间或者流水线
	 * @return 结果
	 */
	int updateDevSignAndType(@Param("companyId") Integer companyId,@Param("id") Integer id,
							 @Param("sign") int sign,@Param("devType") Integer devType);

    /**
     * 通过硬件id更新是否被配置标记状态
     * @param companyId 公司id
     * @param id 硬件id
     * @param sign 是否被配置标记
     * @param devType 硬件配置对象标记车间或者流水线
     * @param deviceName 硬件名称
     * @return 结果
     */
    int updateDevSignAndType1(@Param("companyId") Integer companyId,
                              @Param("id") Integer id,
                              @Param("sign") int sign,
                              @Param("devType") Integer devType,
                              @Param("deviceName")String deviceName);

	/**
	 * 查询硬件编码总数
	 * @return
	 */
	int countDevNum();

	/**
	 * 通过编码查询硬件信息
	 * @param companyId 公司id
	 * @param code  看板编码
	 * @return 结果
	 */
	DevList selectDevListByComCode(@Param("companyId") Integer companyId, @Param("code") String code);
}