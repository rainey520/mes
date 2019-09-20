package com.ruoyi.project.production.ecnLog.controller;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import com.ruoyi.project.production.ecnLog.service.IEcnLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ECN 变更记录 信息操作处理
 *
 * @author zqm
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/production/ecnLog")
public class EcnLogController extends BaseController {
    private String prefix = "production/ecnLog";

    @Autowired
    private IEcnLogService ecnLogService;

    @RequiresPermissions("production:ecnLog:view")
    @GetMapping()
    public String ecnLog(int ecn_type, int save_id, int ecn_status, ModelMap mmap) {
        mmap.put("ecn_type", ecn_type);
        mmap.put("save_id", save_id);
        mmap.put("ecnLog", ecnLogService.selectEcnLogBySaveId(ecn_type,save_id));
        mmap.put("ecn_status", ecn_status);
        return prefix + "/ecnLog";
    }

    /**
     * 查询ECN 变更记录列表
     */
    @RequiresPermissions("production:ecnLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EcnLog ecnLog, HttpServletRequest request) {
        startPage();
        List<EcnLog> list = ecnLogService.selectEcnLogList(ecnLog,request);
        return getDataTable(list);
    }

    /**
     * 添加变更信息
     * @param type 变更类型
     * @param save_id 保存id
     * @param mmap 缓存数据
     * @return
     */
    @GetMapping("/add")
    public String add(int type,int save_id,ModelMap mmap){
        mmap.put("type",type);
        mmap.put("save_id",save_id);
        return prefix+"/add";
    }

    /**
     * 新增保存变更记录
     */
    @RequiresPermissions("production:ecnLog:add")
    @Log(title = "新增变更记录", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
    public AjaxResult addSave(EcnLog ecnLog){
        try {
            return toAjax(ecnLogService.insertEcnLog(ecnLog));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }


    /**
     * 修改保存ECN 变更记录
     */
    @RequiresPermissions("production:ecnLog:edit")
    @Log(title = "关闭ECN状态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(int type,int save_id) {
        return toAjax(ecnLogService.updateEcnLog(type,save_id));
    }

    /**
     * 取消ECN
     */
    @PostMapping("/cancelEcn")
    @ResponseBody
    public AjaxResult cancelEcn(int id,int ecnType,int saveId){
        return toAjax(ecnLogService.cancelEcn(id,ecnType,saveId));
    }

    /**
     * 提交ECN
     */
    @PostMapping("/submitEcn")
    @ResponseBody
    public AjaxResult submitEcn(int id,int ecnStatus){
        try {
            return toAjax(ecnLogService.updateEcnStatus(id,ecnStatus));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 审核ECN
     */
    @RequiresPermissions("production:ecnLog:examEcn")
    @PostMapping("/examEcn")
    @ResponseBody
    public AjaxResult examEcn(int id,int ecnStatus){
        try {
            return toAjax(ecnLogService.updateEcnStatus(id,ecnStatus));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

}
