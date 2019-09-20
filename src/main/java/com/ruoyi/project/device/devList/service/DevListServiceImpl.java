package com.ruoyi.project.device.devList.service;

import com.ruoyi.common.constant.DevConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.DevId;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devList.domain.DevList;
import com.ruoyi.project.device.devList.mapper.DevListMapper;
import com.ruoyi.project.product.importConfig.mapper.ImportConfigMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 硬件 服务层实现
 *
 * @author ruoyi
 * @date 2019-04-08
 */
@Service("devList")
public class DevListServiceImpl implements IDevListService {
    @Autowired
    private DevListMapper devListMapper;

    @Autowired
    private ImportConfigMapper importConfigMapper;

    /**
     * 查询硬件信息
     *
     * @param id 硬件ID
     * @return 硬件信息
     */
    @Override
    public DevList selectDevListById(Integer id) {
        return devListMapper.selectDevListById(id);
    }

    /**
     * 查询硬件列表
     *
     * @param devList 硬件信息
     * @return 硬件集合
     */
    @Override
    public List<DevList> selectDevListList(DevList devList, HttpServletRequest request) {
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        if (user == null) {
            return Collections.emptyList();
        }
        devList.setCompanyId(user.getCompanyId());
        return devListMapper.selectDevListList(devList);
    }

    /**
     * 新增硬件
     *
     * @param
     * @return 结果
     */
    @Override
    public int insertDevList(int devModelId) {

//        DevModel devModel = devModelMapper.selectDevModelById(devModelId);
//        if (devModel == null) return 0;
//        DevList devList = null;
//        DevIo devIo = null;
//        int i = 1;
//        while (i <= 30) {
//
//        }
        return 1;
    }

    /**
     * 修改硬件
     *
     * @param devList 硬件信息
     * @return 结果
     */
    @Override
    public int updateDevList(DevList devList, HttpServletRequest request) {
        return devListMapper.updateDevList(devList);

    }

    /**
     * 用户添加硬件信息
     *
     * @param devList 硬件信息
     * @return
     */
    @Override
    public int addSave(DevList devList, HttpServletRequest request) {
        return devListMapper.insertDevList(devList);
    }

    /**
     * 删除硬件对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDevListByIds(String ids, HttpServletRequest request) {
        return 0;
    }

    /**
     * 查询对应的硬件信息和对应的IO数据
     *
     * @param id
     * @return
     */
    @Override
    public DevList selectDevListAndIoById(Integer id) {
        return devListMapper.selectDevListAndIoById(id);
    }

    /**
     * 获取前20个未配置的硬件编号
     *
     * @return
     */
    @Override
    public List<String> selectNoConfigDevice() {
        return devListMapper.selectNoConfigDevice();
    }

    @Override
    public List<DevList> selectAll(Cookie[] cookies) {
        User user = JwtUtil.getTokenCookie(cookies);
        if (user == null) {
            return Collections.emptyList();
        }
        return devListMapper.selectAll(user.getCompanyId());
    }

    /**
     * 根据硬件编号验证对应的硬件是否存在
     *
     * @param code 硬件编号
     * @return
     */
    @Override
    public int deviceValidate(String code, HttpServletRequest request) {
        return DevConstants.DEV_VALIDATE_TRUE;
    }

    /**
     * 成产硬件编码
     *
     * @return
     * @throws Exception
     */
    @Override
    public int createDevCode(){
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        //查询硬件编码总数
//        int num = devListMapper.countDevNum();
//        ImportConfig config = importConfigMapper.selectImportConfigByType(-1);
//        if(config == null || config.getCon1() == null){
//            throw new Exception("配置异常，请联系管理员");
//        }
//        if(num >= config.getCon1()){
//            throw new Exception("已经达到硬件编号最大数，需要升级才可以添加更多硬件编码");
//        }
        DevList devList = null;
        String devId = "KB" + DevId.getPageCode();
        DevList dev = devListMapper.selectDevListByDevId(devId);
        if (StringUtils.isNotNull(dev)) {
            throw new BusinessException("添加失败，请重新添加");
        }
        // 添加硬件
        devList = new DevList();
        devList.setDeviceId(devId);
        devList.setDeviceStatus(1);
        devList.setDefId(0);
        devList.setDeviceUploadTime(15);
        devList.setDevModelId(1);
        devList.setCreateDate(new Date());
        devList.setCompanyId(user.getCompanyId());
        return devListMapper.insertDevList(devList);

        // for (int i =0;i<10;i++){
        //      devId ="KB"+ DevId.getPageCode();
        //     if (StringUtils.isEmpty(devId)) {
        //         continue;
        //     }
        //     devList = new DevList();
        //     devList.setDeviceId(devId);
        //     devList.setDeviceStatus(1);
        //     devList.setDefId(0);
        //     devList.setDeviceUploadTime(15);
        //     devList.setDevModelId(1);
        //     devList.setCreateDate(new Date());
        //     devList.setCompanyId(user.getCompanyId());
        //     DevList dev = devListMapper.selectDevListByDevId(devId);
        //     if (dev == null) {
        //         devListMapper.insertDevList(devList);
        //     }
        // }
        // return 1;
    }

    /**
     * 主服务器修改硬件信息
     *
     * @param devList 硬件信息
     * @return 结果
     */
    @Override
    public int apiEdit(DevList devList) {
        return devListMapper.updateDevList(devList);
    }

    /**
     * 查询所以未配置的硬件
     *
     * @return
     */
    @Override
    public List<DevList> selectDevNotConfig() {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return devListMapper.selectDevNotConfig(user.getCompanyId());
    }

    /**
     * app端查询硬件信息
     *
     * @param devList 硬件信息
     * @return 结果
     */
    @Override
    public List<DevList> appSelectDevList(DevList devList) {
        User user = JwtUtil.getUser();
        devList.setCompanyId(user.getCompanyId());
        return devListMapper.selectDevListList(devList);
    }
}
