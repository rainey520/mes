package com.ruoyi.project.production.filesource.controller;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.production.filesource.domain.FileSourceInfo;
import com.ruoyi.project.production.filesource.service.IFileSourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件素材管理
 *
 * @author zqm
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/iso/fileSource")
public class FileSourceInfoController extends BaseController {

    @Autowired
    private IFileSourceInfoService fileSourceService;

    /**
     * 查看文件列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FileSourceInfo fileSourceInfo) {
        startPage();
        List<FileSourceInfo> list = fileSourceService.selectFileSourceInfoList(fileSourceInfo);
        return getDataTable(list);
    }

    /**
     * 新增文件管理
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("avatarfile") MultipartFile file,
                              @RequestParam("saveType") int saveType) {
        try {
            return toAjax(fileSourceService.saveFileInfo(file,saveType));
        } catch (BusinessException e) {
            return error(e.getMessage());
        } catch (Exception e){
            return error("上传失败");
        }
    }

    /**
     * 删除文件
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(fileSourceService.deleteFileInfoByIds(ids));
    }

}
