package com.ruoyi.project.product.importConfig.service;

import com.ruoyi.project.product.importConfig.domain.ImportConfig;


/**
 * 导入配置 服务层
 * 
 * @author sj
 * @date 2019-07-03
 */
public interface IImportConfigService 
{
	/**
     * 查询导入配置信息
     * 
     * @param cType 导入配置ID
     * @return 导入配置信息
     */
	public ImportConfig selectImportConfigByType(Integer cType);
	

	
	/**
     * 新增导入配置
     * 
     * @param importConfig 导入配置信息
     * @return 结果
     */
	public int insertImportConfig(ImportConfig importConfig);

		
	/**
     * 删除导入配置信息
     * 
     * @param cType 需要删除的数据cType
     * @return 结果
     */
	public int deleteImportConfigByType(Integer cType);
	
}
