package com.ruoyi.project.app.service.impl;

import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.app.domain.Index;
import com.ruoyi.project.app.domain.Init;
import com.ruoyi.project.app.domain.Product;
import com.ruoyi.project.app.service.IInitService;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.filesource.domain.FileSourceInfo;
import com.ruoyi.project.production.filesource.mapper.FileSourceInfoMapper;
import com.ruoyi.project.quality.afterService.domain.AfterService;
import com.ruoyi.project.quality.afterService.mapper.AfterServiceMapper;
import com.ruoyi.project.quality.mesInput.domain.MesInput;
import com.ruoyi.project.quality.mesInput.mapper.MesInputMapper;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.mapper.MenuMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.*;

@Service
public class InitServiceImpl implements IInitService {

    @Autowired
    private DevCompanyMapper devCompanyMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private FileSourceInfoMapper fileInfoMapper;

    @Autowired
    private DevWorkOrderMapper workOrderMapper;

    /**
     * 获取当天工单、菜单权限、公司信息
     *
     * @return
     */
    @Override
    public Init initIndex(Index index) {
        User user = JwtUtil.getUser();
        Init init = new Init();
        init.setWorkList(Collections.emptyList());
        // 查询轮播图信息
        List<FileSourceInfo> fileSourceInfos = fileInfoMapper.selectFileListBySaveType(user.getCompanyId(), 0);
        List<String> comPicList = new ArrayList<>();
        for (FileSourceInfo fileSourceInfo : fileSourceInfos) {
            if (fileSourceInfo != null && StringUtils.isNotEmpty(fileSourceInfo.getFilePath())) {
                comPicList.add(fileSourceInfo.getFilePath());
            }
        }
        init.setComPicList(comPicList);
        //查询公司
        init.setCompany(devCompanyMapper.selectDevCompanyById(user.getCompanyId()));
        //查询菜单信息
        init.setMenuList(menuMapper.selectMenuListByParentIdAndUserId(user.getUserId().intValue(), index.getParentId()));
        init.setUserPhoto(userService.selectUserById(user.getUserId()).getAvatar());
        return init;
    }

    /**
     * 获取菜单
     *
     * @param index
     * @return
     */
    @Override
    public List<Menu> initMenu(Index index) {
        User user = JwtUtil.getUser();
        return menuMapper.selectMenuListByParentIdAndUserId(user.getUserId().intValue(), index.getParentId());
    }

    /**
     * 获取工单号
     *
     * @return 结果
     */
    @Override
    public String getWorkCode() {
        String workCode = CodeUtils.getWorkOrderCode();
        DevWorkOrder workOrder = workOrderMapper.selectWorkOrderByCode(workCode);
        if (workOrder != null) {
            workCode = workCode + CodeUtils.getRandom();
        }
        return workCode;
    }


    /**
     * 录入mes信息
     *
     * @param index 上传信息
     * @return 结果
     */
    @Autowired
    private AfterServiceMapper afterServiceMapper;

    @Autowired
    private MesInputMapper mesInputMapper;

    /**
     * 录入mes信息
     *
     * @param index 上传信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveInput(Index index) {
        Map<String, Object> map = new HashMap<>(16);
        try {
            if (index == null || StringUtils.isEmpty(index.getMesInfo())) {
                map.put("code", 0);
                map.put("msg", "请输入产品批次信息");
                return map;
            }
            // 保存产品批次
            AfterService afterService = new AfterService();
            afterService.setCompanyId(-1);
            afterService.setInputBatchInfo(index.getMesInfo());
            afterService.setInputTime(new Date());
            afterServiceMapper.insertAfterService(afterService);
            // 更新工单退货
            MesInput mesInput = mesInputMapper.selectMesInputByMesInfo(index.getMesInfo());
            if (mesInput != null) {
                mesInput.setBackNum(mesInput.getBackNum() + 1);
                mesInputMapper.updateMesInput(mesInput);
            }
            map.put("code", 1);
            map.put("msg", "录入成功");
            return map;
        } catch (Exception e) {
            map.put("code", 2);
            map.put("msg", "系统异常");
            return map;
        }
    }

    /**
     * 获取工单相关mes退货数据
     *
     * @return 结果
     */
    @Override
    public Map<String, Object> getMesBatch() {
        Map<String, Object> map = new HashMap<>(16);
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result;
        List<MesInput> mesInputList = mesInputMapper.selectMesInputListAll();
        for (MesInput mesInput : mesInputList) {
            result = numberFormat.format((float) mesInput.getBackNum() / (float) mesInput.getWorkNum() * 100);
            mesInput.setRate(result);
        }
        map.put("code", 1);
        map.put("msg", "请求成功");
        map.put("data", mesInputList);
        return map;
    }

    /**
     * 获取产品相关mes退货数据
     *
     * @return 结果
     */
    @Override
    public Map<String, Object> getProBatch() {
        Map<String, Object> map = new HashMap<>(16);
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result;
        List<Product> productList = mesInputMapper.selectMesInputListByPro();
        for (Product product : productList) {
            result = numberFormat.format((float) product.getTotalBackNum() / (float) product.getTotalWorkNum() * 100);
            product.setRate(result);
        }
        map.put("code", 1);
        map.put("msg", "请求成功");
        map.put("data", productList);
        return map;
    }
}
