package com.ruoyi.project.app.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.production.devWorkOrder.service.IDevWorkOrderService;
import com.ruoyi.project.quality.afterService.domain.AfterService;
import com.ruoyi.project.quality.afterService.domain.AfterServiceItem;
import com.ruoyi.project.quality.afterService.service.IAfterServiceService;
import com.ruoyi.project.quality.mesBatch.domain.MesBatch;
import com.ruoyi.project.quality.mesBatch.domain.MesBatchDetail;
import com.ruoyi.project.quality.mesBatch.service.IMesBatchDetailService;
import com.ruoyi.project.quality.mesBatch.service.IMesBatchService;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserApp;
import com.ruoyi.project.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app端MES交互
 *
 * @Author: Rainey
 * @Date: 2019/9/20 14:38
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/mes")
public class MesController {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MesController.class);

    @Autowired
    private IDevWorkOrderService devWorkOrderService;

    @Autowired
    private IMesBatchService mesBatchService;

    @Autowired
    private IMesBatchDetailService mesBatchDetailService;

    @Autowired
    private IAfterServiceService afterServiceService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    /**
     * 仓库配置MES数据拉取
     */
    @RequestMapping("/appConfig")
    public AjaxResult appConfig(@RequestBody MesBatch mesBatch) {
        if (mesBatch != null && mesBatch.getWorkId() != null) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("mesCode", CodeUtils.getMesCode());
            map.put("mesData", devWorkOrderService.selectWorkOrderMesByWId(mesBatch.getWorkId()));
            return AjaxResult.success(map);
        }
        return AjaxResult.error();
    }

    /**
     * 仓库配置MES数据保存
     */
    @RequestMapping("/appConfigSave")
    public AjaxResult appConfigSave(@RequestBody MesBatch mesBatch) {
        try {
            int i = mesBatchService.insertMesBatch(mesBatch);
            return i > 0 ? AjaxResult.success() : AjaxResult.error();
        } catch (BusinessException e) {
            LOGGER.error("app端仓库配置MES出现异常：" + e.getMessage());
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("app端仓库配置MES出现异常：" + e.getMessage());
            return AjaxResult.error();
        }
    }

    /**
     * 仓库删除配置
     */
    @RequestMapping("/appConfigRemove")
    public AjaxResult appConfigRemove(@RequestBody MesBatchDetail mesBatchDetail) {
        try {
            if (mesBatchDetail != null && mesBatchDetail.getId() != null) {
                int i = mesBatchDetailService.removeDetailById(mesBatchDetail.getId());
                return i > 0 ? AjaxResult.success() : AjaxResult.error();
            }
        } catch (Exception e) {
            LOGGER.error("app端仓库删除mes出现异常：" + e.getMessage());
            return AjaxResult.error();
        }
        return AjaxResult.error();
    }

    /**
     * 生产配置MES数据拉取
     */
    @RequestMapping("/appMesProduce")
    public AjaxResult appMesProduce(@RequestBody MesBatch mesBatch) {
        if (mesBatch != null && mesBatch.getWorkId() != null) {
            return AjaxResult.success(devWorkOrderService.selectWorkOrderMesByWId(mesBatch.getWorkId()));
        }
        return AjaxResult.error();
    }

    /**
     * 生产配置MES数据保存
     */
    @RequestMapping("/appMesProduceSave")
    public AjaxResult appMesProduceSave(@RequestBody MesBatch mesBatch) {
        try {
            int i = mesBatchService.updateMesBatch(mesBatch);
            return i > 0 ? AjaxResult.success() : AjaxResult.error();
        } catch (BusinessException e) {
            LOGGER.error("app端生产配置MES出现异常：" + e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 拉取售后录入的列表
     */
    @RequestMapping("/appAfterList")
    public Map<String,Object> appAfterList(@RequestBody AfterService afterService) {
        User user = JwtUtil.getUser();
        Map<String,Object> map = new HashMap<>(16);
        if (user == null) {
            map.put("code",1);
            map.put("msg","用户未登录");
            return map;
        }
        TableDataInfo rspData = new TableDataInfo();
        if (afterService != null) {
            afterService.appStartPage();
            List<AfterService> list = afterServiceService.selectAfterServiceList(afterService);
            rspData.setCode(0);
            rspData.setRows(list);
            rspData.setSize(list.size());
            rspData.setTotal(new PageInfo(list).getTotal());
            map.put("data",rspData);
            map.put("code",0);
            if (afterService.getMenuId() != null) {
                map.put("menuList",menuService.selectMenuListByParentIdAndUserId(user.getUserId().intValue(), afterService.getMenuId()));
            }
            if (StringUtils.isNotEmpty(afterService.getSearchItems())) {
                List<AfterServiceItem> serviceItemList = afterServiceService.selectListBySearchInfo(afterService);
                map.put("searchList",serviceItemList);
            }
            return map;
        }
        map.put("code",1);
        map.put("msg","请求失败");
        return map;
    }

    /**
     * 拉取用户信息
     */
    @RequestMapping("/userList")
    public AjaxResult appSelectUserList(@RequestBody UserApp userApp){
        try {
            return AjaxResult.success(userService.appSelectUserList(userApp));
        } catch (Exception e) {
            LOGGER.error("app拉取用户接口出现异常:" + e.getMessage());
            return AjaxResult.error();
        }
    }
    /**
     * 售后录入
     */
    @RequestMapping("/appAfterInput")
    public AjaxResult appAfterInput(@RequestBody AfterService afterService) {
        if (afterService != null) {
            int i = afterServiceService.insertAfterService(afterService);
            return i > 0 ? AjaxResult.success() : AjaxResult.error();
        }
        return AjaxResult.error();
    }

    /**
     * 售后删除
     */
    @RequestMapping("/appAfterRemove")
    public AjaxResult appAfterRemove(@RequestBody AfterService afterService){
        if (afterService != null && afterService.getId() != null) {
            int i = afterServiceService.deleteAfterServiceById(afterService.getId());
            return i > 0 ? AjaxResult.success() : AjaxResult.error();
        }
        return AjaxResult.error();
    }
}

