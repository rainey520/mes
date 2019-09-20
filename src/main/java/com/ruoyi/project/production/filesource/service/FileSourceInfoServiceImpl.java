package com.ruoyi.project.production.filesource.service;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.production.filesource.domain.FileSourceInfo;
import com.ruoyi.project.production.filesource.mapper.FileSourceInfoMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 文件素材管理 服务层实现
 *
 * @author zqm
 * @date 2019-05-09
 */
@Service
public class FileSourceInfoServiceImpl implements IFileSourceInfoService {

    @Autowired
    private FileSourceInfoMapper fileSourceInfoMapper;

    @Autowired
    private DevCompanyMapper companyMapper;

    @Value("${file.iso}")
    private String isoFileUrl;


    /**
     * 查看文件列表
     *
     * @param fileSourceInfo 文件信息
     * @return 结果
     */
    @Override
    public List<FileSourceInfo> selectFileSourceInfoList(FileSourceInfo fileSourceInfo) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        fileSourceInfo.setCompanyId(user.getCompanyId());
        return fileSourceInfoMapper.selectFileSourceInfoList(fileSourceInfo);
    }

    /**
     * 保存文件
     *
     * @param multipartFile     文件
     * @param saveType 保存类型
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveFileInfo(MultipartFile multipartFile, int saveType) throws IOException {
        User user = JwtUtil.getUser();
        if (user == null) {
            return 0;
        }
        String originalFilename = multipartFile.getOriginalFilename().toUpperCase();
        if (originalFilename.endsWith(".JPG") || originalFilename.endsWith(".PNG")) {
            //计数文件大小
            Long size = multipartFile.getSize();
            //查询对应公司是否存在
            DevCompany devCompany = companyMapper.selectDevCompanyById(user.getCompanyId());
            if (devCompany == null) {
                throw new BusinessException("公司不存在或被删除");
            }
            //VIP用户可用20G
            if (devCompany.getSign() == 1) {
                long fileTotalSize = devCompany.getFileSize() + size;
                long totalSize = 21474386480L;
                if (totalSize < fileTotalSize) {
                    throw new BusinessException("文件存储空间容量不足");
                }
            } else {
                //普通用户 2G
                long fileTotalSize = devCompany.getFileSize() + size;
                long totalSize = 2147438648L;
                if (totalSize < fileTotalSize) {
                    throw new BusinessException("文件存储空间容量不足,请升级");
                }
            }
            devCompany.setFileSize(devCompany.getFileSize() + size);
            companyMapper.updateDevCompany(devCompany);
            String filePath = RuoYiConfig.getProfile() + "mes" + user.getCompanyId();
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                //不存在创建对应文件夹
                fileDir.mkdir();
            }
            // 设置文件名
            String fileName = multipartFile.getOriginalFilename();
            FileSourceInfo uniqueFile = fileSourceInfoMapper.selectFileInfoByFileName(user.getCompanyId(),fileName);
            if (StringUtils.isNotNull(uniqueFile)) {
                throw new BusinessException("文件名称重复");
            }
            FileSourceInfo fileInfo = new FileSourceInfo();
            File desc = FileUploadUtils.getAbsoluteFile(filePath,filePath + "/" + fileName);
            multipartFile.transferTo(desc);
            fileInfo.setCompanyId(user.getCompanyId());
            fileInfo.setFileType(0);
            fileInfo.setSaveType(saveType);
            fileInfo.setfSize(size);
            fileInfo.setFileName(fileName);
            fileInfo.setFilePath(isoFileUrl + "mes" + user.getCompanyId() +"/" + fileName);
            fileInfo.setSavePath(filePath + "/" + fileName);
            fileInfo.setCreateTime(new Date());
            return fileSourceInfoMapper.insertFileInfo(fileInfo);
        } else {
            throw new BusinessException("轮播图片格式错误需PNG/JPG结尾");
        }
    }

    /**
     * 删除文件素材管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteFileInfoByIds(String ids)
    {
        User user = JwtUtil.getUser();
        if (user == null) {
            return 0;
        }
        DevCompany company = companyMapper.selectDevCompanyById(user.getCompanyId());
        if (company == null) {
            return 0;
        }
        long fileSize = company.getFileSize();
        Integer[] fileIds = Convert.toIntArray(ids);
        for (Integer fileId : fileIds) {
            FileSourceInfo info = fileSourceInfoMapper.selectFileInfoById(fileId);
            if(info != null && !StringUtils.isEmpty(info.getSavePath())){
                fileSize = fileSize - info.getfSize();
                //删除对应的文件
                File file = new File(info.getSavePath());
                file.delete();
            }
        }
        company.setFileSize(fileSize < 0 ? 0: fileSize);
        companyMapper.updateDevCompany(company);
        return fileSourceInfoMapper.deleteFileSourceInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询公司轮播图信息
     * @param companyId 公司id
     * @param saveType 保存类型
     * @return 结果
     */
    @Override
    public List<FileSourceInfo> selectFileByComPic(Integer companyId, Integer saveType) {
        return fileSourceInfoMapper.selectFileListBySaveType(companyId,saveType);
    }
}
