package com.ruoyi.project.system.config.mapper;

import com.ruoyi.project.system.config.domain.JpushInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 极光推送记录数据层
 * @Author: Rainey
 * @Date: 2019/10/15 15:00
 * @Version: 1.0
 **/
public interface JpushInfoMapper {

    /**
     * 查看对应账号对应时间戳记录是否存在
     * @param companyNumber 公司看板账号
     * @param firstTime 首次登陆时间
     * @return 结果
     */
    JpushInfo selectJPushInfoByFirstTime(@Param("companyNumber") String companyNumber, @Param("firstTime") String firstTime);

    /**
     * 新增极光推送记录
     * @param jpushInfo 推送记录信息
     * @return 结果
     */
    int insertJPushInfo(JpushInfo jpushInfo);

    /**
     * 通过极光推送id查询极光推送信息
     * @param id 推送id
     * @return 结果
     */
    JpushInfo selectJPushInfoById(@Param("id") Integer id);

    /**
     * 更新极光推送
     * @param jpushInfo 推送信息
     * @return  结果
     */
    int updateJPushInfo(JpushInfo jpushInfo);

    /**
     * 通过公司账号查询极光推送列表
     * @param companyNumber 公司看板账号
     * @return 结果
     */
    List<String> selectJPushInfoList(@Param("companyNumber") String companyNumber);

    /**
     *  删除两个月都未登录的极光推送列表
     * @param invalidDate 时间
     * @return 结果
     */
    int deleteInvalidTime(@Param("invalidDate") Date invalidDate);
}
