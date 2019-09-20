package com.ruoyi.project.device.devCompany.controller;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 公司 信息操作处理
 *
 * @author ruoyi
 * @date 2019-01-31
 */
@Controller
@RequestMapping("/device/devCompany")
public class DevCompanyController extends BaseController {
    /**
     * 公司控制层日志
     */
    private static final Logger log = LoggerFactory.getLogger(DevCompanyController.class);

    private String prefix = "device/devCompany";

    @Value("${file.iso}")
    private String imgUrl;

    @Autowired
    private IDevCompanyService devCompanyService;

    @RequiresPermissions("device:devCompany:view")
    @GetMapping()
    public String devCompany() {
        return prefix + "/devCompany";
    }

    /**
     * 查询公司列表
     */
    @RequiresPermissions("device:devCompany:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DevCompany devCompany) {
        startPage();
        List<DevCompany> list = devCompanyService.selectDevCompanyList(devCompany);
        return getDataTable(list);
    }


    /**
     * 导出公司列表
     */
    @RequiresPermissions("device:devCompany:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevCompany devCompany) {
        List<DevCompany> list = devCompanyService.selectDevCompanyList(devCompany);
        ExcelUtil<DevCompany> util = new ExcelUtil<DevCompany>(DevCompany.class);
        return util.exportExcel(list, "devCompany");
    }

    /**
     * 新增公司
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存公司
     */
    @RequiresPermissions("device:devCompany:add")
    @Log(title = "公司", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DevCompany devCompany) {
        devCompany.setCreateTime(new Date());
        devCompany.setComType(1);
        return toAjax(devCompanyService.insertDevCompany(devCompany));
    }

    /**
     * 修改公司
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        DevCompany devCompany = devCompanyService.selectDevCompanyById(id);
        mmap.put("devCompany", devCompany);
        return prefix + "/edit";
    }

    /**
     * 修改保存公司
     */
    @Log(title = "公司", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DevCompany devCompany, HttpServletRequest request) {
        try {
            devCompany.setSign(-1);
            devCompany.setFileSize(-1);
            return toAjax(devCompanyService.updateDevCompany(devCompany, request));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error();
    }

    /**
     * 删除公司
     */
    @RequiresPermissions("device:devCompany:remove")
    @Log(title = "公司", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(devCompanyService.deleteDevCompanyByIds(ids));
    }

    /**
     * 修改公司LOGO
     *
     * @param mmap
     * @return
     */
    @GetMapping("/comLogo")
    public String comLogo(ModelMap mmap, HttpServletRequest request) {
        User user = JwtUtil.getTokenUser(request);
        DevCompany devCompany = devCompanyService.selectDevCompanyById(user.getCompanyId());
        mmap.put("company", devCompany);
        return prefix + "/comLogo";
    }

    @Autowired
    private IUserService userService;

    /**
     * 跳转修改公司轮播图片
     */
    @GetMapping("/comPicture")
    public String comPicture(ModelMap map) {
        map.put("saveType",0);
        return prefix + "/comPicture1";
    }

    /**
     * 公司轮播图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadComPicture")
    @ResponseBody
    public AjaxResult uploadComPicture(MultipartFile file) {
        try {
            User u = JwtUtil.getTokenUser(ServletUtils.getRequest());
            String path = RuoYiConfig.getProfile() + "sop" + u.getCompanyId();
            String comPic = FileUploadUtils.upload(path + "/", file);
            // 返回路径
            return success(imgUrl + "sop" + u.getCompanyId() + "/" + comPic);
        } catch (Exception e) {
            return error("图片上传失败");
        }
    }

    /**
     * 公司轮播图片保存（保存地址N）
     *
     * @param comPicture
     * @return
     */
    @PostMapping("/updateComPicture")
    @ResponseBody
    public AjaxResult updateComPicture(String comPicture, HttpServletRequest request) {
        DevCompany company = devCompanyService.selectDevCompanyById(JwtUtil.getTokenUser(request).getCompanyId());
        company.setComPicture(comPicture);
        return toAjax(devCompanyService.updateDevCompany(company, request));
    }

    /**
     * 校验公司名称是否唯一
     *
     * @param comName
     * @return
     */
    @PostMapping("/checkComNameUnique")
    @ResponseBody
    public String checkComNameUnique(String comName) {
        return devCompanyService.checkComNameUnique(comName);
    }

    /**
     * 升级为VIP
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/vip/{id}")
    public AjaxResult toVip(@PathVariable("id") int id) {
        try {
            return toAjax(devCompanyService.toVip(id));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
