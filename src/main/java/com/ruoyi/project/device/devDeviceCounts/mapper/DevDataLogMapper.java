package com.ruoyi.project.device.devDeviceCounts.mapper;

import com.ruoyi.project.device.devDeviceCounts.domain.DataLogTask;
import com.ruoyi.project.device.devDeviceCounts.domain.DevDataLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 数据上报日志 数据层
 *
 * @author zqm
 * @date 2019-04-12
 */
public interface DevDataLogMapper {
    /**
     * 查询数据上报日志信息
     *
     * @param id 数据上报日志ID
     * @return 数据上报日志信息
     */
    public DevDataLog selectDevDataLogById(Integer id);

    /**
     * 查询数据上报日志列表
     *
     * @param devDataLog 数据上报日志信息
     * @return 数据上报日志集合
     */
    public List<DevDataLog> selectDevDataLogList(DevDataLog devDataLog);

    /**
     * 新增数据上报日志
     *
     * @param devDataLog 数据上报日志信息
     * @return 结果
     */
//    @DataSource(value = DataSourceType.SLAVE)
    public int insertDevDataLog(DevDataLog devDataLog);

    /**
     * 查询每天正在生产或者已经完成的工单每小时的IO口数据
     *
     * @param scType 车间或者流水线标记
     * @param devId          硬件id
     * @param ioId           工位id
     * @param workId         工单id
     * @param lineId          产线id
     * @param sysDateTimeOld 前一个小时时间
     * @param sysDateTime    当前时间
     * @return 结果包装类
     */
    DataLogTask selectDataLogBeInOrFinish(@Param("scType") Integer scType, @Param("devId") Integer devId, @Param("ioId") Integer ioId, @Param("workId") Integer workId,
                                          @Param("lineId") Integer lineId,
                                          @Param("sysDateTimeOld") Date sysDateTimeOld, @Param("sysDateTime") Date sysDateTime);


    /**
     * 查询数据上报列表
     *
     * @param workId 工单id主键
     * @param companyId 公司Id主键
     * @param scType 车间或者流水线标记
     * @return 数据上报日志信息
     */
    public List<DevDataLog> selectDevDataLogByWorkId(@Param("workId") Integer workId,
                                                     @Param("companyId") Integer companyId,
                                                     @Param("scType") Integer scType);

    /**
     * 查询对应产线工单硬件IO口上传回传的数据
     *
     * @param line_id 查询
     * @param work_id 工单
     * @param dev_id  硬件编号
     * @param io_id   IO口编号
     * @param scType 流水线或者车间标记
     * @return
     */
//    @DataSource(DataSourceType.SLAVE)
    DevDataLog selectLineWorkDevIo(@Param("line_id") int line_id, @Param("work_id") int work_id, @Param("dev_id") int dev_id,
                                   @Param("io_id") int io_id,@Param("scType") int scType);

    /**
     * 实时统计当前小时的工位产量
     * @param companyId 公司id
     * @param lineId 产线id
     * @param workId 工单id
     * @param devId 硬件id
     * @param wid 工位id
     * @param scType 车间或者流水线标记
     * @return 结果
     */
    int selectLineWorkSysTemData(@Param("companyId")int companyId,@Param("lineId")int lineId,@Param("workId")int workId,
                                 @Param("devId")int devId,@Param("wid")int wid,@Param("scType") Integer scType);
}