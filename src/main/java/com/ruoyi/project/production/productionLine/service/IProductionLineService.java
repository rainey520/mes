package com.ruoyi.project.production.productionLine.service;

import com.ruoyi.project.production.productionLine.domain.ProductionLine;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 生产线 服务层
 * 
 * @author ruoyi
 * @date 2019-04-11
 */
public interface IProductionLineService 
{
	/**
     * 查询生产线信息
     * 
     * @param id 生产线ID
     * @return 生产线信息
     */
	public ProductionLine selectProductionLineById(Integer id);
	
	/**
     * 查询生产线列表
     * 
     * @param productionLine 生产线信息
     * @return 生产线集合
     */
	public List<ProductionLine> selectProductionLineList(ProductionLine productionLine, HttpServletRequest request);
	
	/**
     * 新增生产线
     * 
     * @param productionLine 生产线信息
     * @return 结果
     */
	public int insertProductionLine(ProductionLine productionLine,HttpServletRequest request);
	
	/**
     * 修改生产线
     * 
     * @param productionLine 生产线信息
     * @return 结果
     */
	public int updateProductionLine(ProductionLine productionLine,HttpServletRequest request);

		
	/**
     * 删除生产线信息
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductionLineById(Integer id,HttpServletRequest request);

	/**
	 * 查询对应产线已经配置的硬件信息
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> selectLineDev(int id);

	/**
	 * 保存相关产线IO口配置
	 * @param line
	 * @return
	 */
	int updateLineConfigClear(ProductionLine line,HttpServletRequest request);

	/**
	 * 查询对应公司的所有生产线
	 * @return
	 */
	List<ProductionLine> selectAllProductionLineByCompanyId();

	/**
	 * 通过生产线id查询责任人名称
	 * @param lineId
	 * @return
	 */
	Map findDeviceLiableByLineId(Integer lineId);

	/**
	 * 通过作业指导书id查询未配置的产线信息
	 * @param isoId 作业指导书id
	 * @param companyId 公司id
	 * @return 结果
	 */
	List<ProductionLine> selectLineNotConfigByIsoId(Integer isoId,Integer companyId);

	/**
	 * 校验产线名称的唯一性
	 * @param productionLine 产线对象
	 * @return 结果
	 */
    String checkLineNameUnique(ProductionLine productionLine);

	/**
	 * app端查询流水线列表
	 * @param productionLine 流水线对象
	 * @return 结果
	 */
	List<ProductionLine> appSelectLineList(ProductionLine productionLine);
}
