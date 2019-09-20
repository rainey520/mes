package com.ruoyi.project.production.productionLine.mapper;

import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 生产线 数据层
 * 
 * @author ruoyi
 * @date 2019-04-11
 */
public interface ProductionLineMapper 
{
	/**
     * 查询生产线信息
     * 
     * @param id 生产线ID
     * @return 生产线信息
     */
//	@DataSource(value = DataSourceType.SLAVE)
	public ProductionLine selectProductionLineById(Integer id);
	
	/**
     * 查询生产线列表
     * 
     * @param productionLine 生产线信息
     * @return 生产线集合
     */
//	@DataSource(DataSourceType.SLAVE)
	public List<ProductionLine> selectProductionLineList(ProductionLine productionLine);
	
	/**
     * 新增生产线
     * 
     * @param productionLine 生产线信息
     * @return 结果
     */
	public int insertProductionLine(ProductionLine productionLine);
	
	/**
     * 修改生产线
     * 
     * @param productionLine 生产线信息
     * @return 结果
     */
//	@DataSource(DataSourceType.SLAVE)
	public int updateProductionLine(ProductionLine productionLine);
	
	/**
     * 删除生产线
     * 
     * @param id 生产线ID
     * @return 结果
     */
	public int deleteProductionLineById(Integer id);
	
	/**
     * 批量删除生产线
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
//	@DataSource(value = DataSourceType.SLAVE)
	public int deleteProductionLineByIds(String[] ids);

	/**
	 * 查询对应产线已经配置的硬件信息
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> selectLineDev(@Param("id")int id);

	/**
	 *将其产线IO配置清空
	 * @param line_id
	 * @return
	 */
	int updateLineConfigClear(@Param("line_id")int line_id);

	/**
	 * 将相关的IO口配置在对应的产线下线
	 * @param id
	 * @param line_id
	 * @return
	 */
	int updateLineConfig(@Param("id")int id,@Param("line_id")int line_id);

	/**
	 * 配置相关产线的警戒线
	 * @param id
	 * @return
	 */
	int updateIoSignLine(@Param("id")int id);

	/**
	 * 查询对应公司所以未删除的产线
	 * @param com_id 公司编号
	 * @return
	 */
//	@DataSource(DataSourceType.SLAVE)
	List<ProductionLine> selectAllDef0(@Param("com_id")int com_id);

	/**
	 * 查询对应公司的所有生产线
	 * @return
	 */
//	@DataSource(DataSourceType.SLAVE)
    List<ProductionLine> selectAllProductionLineByCompanyId(@Param("companyId") Integer companyId);

	/**
	 * 通过作业指导书id查询未配置的产线信息
	 * @param isoId 作业指导书id
	 * @param companyId 公司id
	 * @return 结果
	 */
	List<ProductionLine> selectLineNotConfigByIsoId(@Param("isoId") Integer isoId,@Param("companyId") Integer companyId);

	/**
	 * 根据页面id查询对应的配置产线信息
	 * @param pid 页面id
	 * @return
	 */
	List<ProductionLine> selectLineByPageId(@Param("pid")int pid);

	/**
	 * 查询产线详情
	 * @param pid 页面id
	 * @return
	 */
	ProductionLine selectLineDetailByPageId(@Param("pid")int pid);

	/**
	 * 检验产线名称的唯一性
	 * @param companyId 公司id
	 * @param lineName 产线名成
	 * @return 结果
	 */
    ProductionLine selectProductionLineByName(@Param("companyId") Integer companyId, @Param("lineName") String lineName);
}