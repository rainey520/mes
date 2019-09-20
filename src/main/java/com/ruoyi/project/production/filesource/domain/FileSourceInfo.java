package com.ruoyi.project.production.filesource.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 文件素材管理表 tab_file_source_info
 * 
 * @author zqm
 * @date 2019-05-09
 */
public class FileSourceInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 所属公司 */
	private Integer companyId;
	/** 文件类型 0 图片，1、文件*/
	private Integer fileType;
	/** 保存类型 0 为公司轮播图 */
	private Integer saveType;
	/** 保存id */
	private Integer saveId;
	/**
	 * 文件保存路径
	 */
	private String savePath;
	/** 文件路径 */
	private String filePath;
	/** 文件名称 */
	private String fileName;
	/** 上传时间 */
	private Date createTime;
	/** 文件大小 */
	private long fSize;

	public long getfSize() {
		return fSize;
	}

	public void setfSize(long fSize) {
		this.fSize = fSize;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getSaveType() {
		return saveType;
	}

	public void setSaveType(Integer saveType) {
		this.saveType = saveType;
	}

	public Integer getSaveId() {
		return saveId;
	}

	public void setSaveId(Integer saveId) {
		this.saveId = saveId;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "FileSourceInfo{" +
				"id=" + id +
				", companyId=" + companyId +
				", fileType=" + fileType +
				", saveType=" + saveType +
				", saveId=" + saveId +
				", savePath='" + savePath + '\'' +
				", filePath='" + filePath + '\'' +
				", fileName='" + fileName + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
