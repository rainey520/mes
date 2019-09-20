package com.ruoyi.project.production.workstation.mapper;

import com.ruoyi.project.production.workstation.domain.Workstation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 工位配置 数据层
 * 
 * @author sj
 * @date 2019-06-13
 */
public interface WorkstationMapper 
{
	/**
     * 查询工位配置信息
     * 
     * @param id 工位配置ID
     * @return 工位配置信息
     */
	public Workstation selectWorkstationById(Integer id);

	/**
	 * 根据产线id和公司id查询对应的查询是否存在对应的工位配置
	 * @param lineId 产线id
	 * @param companyId 公司id
	 * @return
	 */
	public Workstation selectWorkstationByLineId(@Param("lineId") Integer lineId, @Param("companyId") Integer companyId);

	/**
	 * 根据公司id 产线id 查询对应数据标识工位
	 * @param lineId
	 * @param companyId
	 * @return
	 */
	public Workstation selectWorkstationSignByLineId(@Param("lineId") int lineId,@Param("companyId") int companyId);

	/**
     * 查询工位配置列表
     * 
     * @param workstation 工位配置信息
     * @return 工位配置集合
     */
	public List<Workstation> selectWorkstationList(Workstation workstation);
	
	/**
     * 新增工位配置
     * 
     * @param workstation 工位配置信息
     * @return 结果
     */
	public int insertWorkstation(Workstation workstation);
	
	/**
     * 修改工位配置
     * 
     * @param workstation 工位配置信息
     * @return 结果
     */
	public int updateWorkstation(Workstation workstation);
	
	/**
     * 删除工位配置
     * 
     * @param id 工位配置ID
     * @return 结果
     */
	public int deleteWorkstationById(Integer id);
	
	/**
     * 批量删除工位配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWorkstationByIds(String[] ids);

    /**
     * 根据对应产线、公司修改工位的数据标识
     * @param lineId 产线id
     * @param companyId 公司id
     * @param sign 数据标识
     * @return
     */
	int editWorkstationSign(@Param("lineId") Integer lineId,@Param("companyId") Integer companyId,@Param("sign") int sign);

	/**
	 * 将第一个工位修改为数据唯一标识
	 * @param lineId 产线
	 * @param companyId 公司
	 * @return
	 */
	int editFirstWorkstionSign(@Param("lineId") Integer lineId,@Param("companyId") Integer companyId);

	/**
	 * 根据产线id查询对应工位信息
	 * @param lineId
	 * @return
	 */
	List<Workstation> selectAllByLineId(@Param("lineId")int lineId);

	/**
	 * 根据硬件id查询对应工位是否存在
	 * @param devId 计数器硬件
	 * @param cid 看板硬件
	 * @param eid EMS 硬件
	 * @return
	 */
	Workstation selectInfoByDevice(@Param("devId")int devId,@Param("cid")int cid,@Param("eid")int eid);

	/**
	 * 根据硬件编码查询对应的工位信息
	 * @param code 硬件编码
	 * @return 结果
	 */
	Workstation selectByDevCode(@Param("companyId") Integer companyId,@Param("code")String code);

	/**
	 * 通过产线查询工位列表信息
	 * @param companyId 公司id
	 * @param lineId 产线id
	 * @return 结果
	 */
    List<Workstation> selectWorkstationListByLineId(@Param("companyId") Integer companyId, @Param("lineId") Integer lineId);

	/**
	 * 根据公司和产线id查询对应产线已经配置的SOP看板硬件编码
	 * @param companyId 公司id
	 * @param lineId 产线id
	 * @return
	 */
	List<String> countLineKBCode(@Param("companyId")Integer companyId,@Param("lineId")Integer lineId);

	/**
	 * 更新对应公司对应查询所以工位SOP 更新反馈标识
	 * @param companyId 公司id
	 * @param lineId  产线id
	 * @param resSign 反馈标识
	 * @return
	 */
	@Update("update  tab_workstation set res_sign = #{resSign} where company_id = #{companyId} and  line_id = #{lineId} and c_id != 0")
	int updateAllResSignByCompanyIdAndLineId(@Param("companyId")int companyId,@Param("lineId")int lineId,@Param("resSign")int resSign);

	/**
	 * 根据id修改工位SOP更新反馈结果
	 * @param id id
	 * @param resSign 反馈结果
	 * @return
	 */
	@Update("update tab_workstation set res_sign = #{resSign} where id = #{id}")
	int updateAllResSignById(@Param("id")int id,@Param("resSign")int resSign);

	/**
	 * 根据公司id和产线id查询对应的工位是否都更新
	 * @param companyId
	 * @param lineId
	 * @return
	 */
	@Select("select count(*) from tab_workstation where company_id = #{companyId} and line_id = #{lineId} and c_id != 0 and res_sign = 0")
	int countAllResSignByCompanyIdAndLineId(@Param("companyId")int companyId,@Param("lineId")int lineId);

	/**
	 * 根据产线id查询对应的产线SOP看板已经配置硬件编号的工位信息
	 * @param companyId 公司id
	 * @param lineId 产线id
	 * @return
	 */
	List<Workstation> selectAllCodeNotNullByLineId(@Param("companyId")int companyId,@Param("lineId")int lineId);
}