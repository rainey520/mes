package com.ruoyi.project.product.importConfig.service;


import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.product.importConfig.mapper.ImportConfigMapper;
import com.ruoyi.project.product.importConfig.domain.ImportConfig;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Target;
import java.util.Date;


/**
 * 导入配置 服务层实现
 * 
 * @author sj
 * @date 2019-07-03
 */
@Service
public class ImportConfigServiceImpl implements IImportConfigService 
{
	@Autowired
	private ImportConfigMapper importConfigMapper;

	/**
	 * 查询导入配置信息
	 * @param cType 导入配置ID
	 * @return
	 */
	@Override
	public ImportConfig selectImportConfigByType(Integer cType) {
		User user = JwtUtil.getUser();
		if (user == null) {
		    return null;
		}
		return importConfigMapper.selectImportConfigByType(user.getCompanyId(),cType);
	}

	/**
	 * 新增导入配置
	 * @param importConfig 导入配置信息
	 * @return
	 */
	@Override
	@Transactional
	public int insertImportConfig(ImportConfig importConfig) {
		User user = JwtUtil.getUser();
		if (user == null) {
		    return 0;
		}
		//删除之前产品导入的配置
		importConfigMapper.deleteImportConfigByType(user.getCompanyId(),importConfig.getcType());
		importConfig.setCompanyId(user.getCompanyId());
		importConfig.setcTime(new Date());
		return importConfigMapper.insertImportConfig(importConfig);
	}

	/**
	 * 删除导入配置信息
	 * @param cType 需要删除的数据cType
	 * @return
	 */
	@Override
	public int deleteImportConfigByType(Integer cType) {
		User user = JwtUtil.getUser();
		if (user == null) {
		    return 0;
		}
		return importConfigMapper.deleteImportConfigByType(user.getCompanyId(),cType);
	}
}
