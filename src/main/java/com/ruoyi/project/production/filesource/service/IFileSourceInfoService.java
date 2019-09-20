package com.ruoyi.project.production.filesource.service;


import com.ruoyi.project.production.filesource.domain.FileSourceInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件素材管理 服务层
 * 
 * @author zqm
 * @date 2019-05-09
 */
public interface IFileSourceInfoService 
{
	/**
	 * 查看文件列表
	 * @param fileSourceInfo 文件信息
	 * @return 结果
	 */
	List<FileSourceInfo> selectFileSourceInfoList(FileSourceInfo fileSourceInfo);

	/**
	 * 保存文件
	 * @param file 文件
	 * @param saveType 保存类型
	 * @return 结果
	 */
	int saveFileInfo(MultipartFile file, int saveType) throws IOException;

	/**
	 * 删除文件
	 * @param ids id
	 * @return 结果
	 */
	int deleteFileInfoByIds(String ids);

	/**
	 * 查询公司轮播图信息
	 * @param companyId 公司id
	 * @param saveType 保存类型
	 * @return 结果
	 */
	List<FileSourceInfo> selectFileByComPic(Integer companyId, Integer saveType);
}
