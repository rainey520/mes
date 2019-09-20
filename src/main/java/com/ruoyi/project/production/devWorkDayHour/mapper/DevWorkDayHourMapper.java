package com.ruoyi.project.production.devWorkDayHour.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.production.devWorkDayHour.domain.DevWorkDayHour;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 工单各个IO口每天每小时数据 数据层
 *
 * @author zqm
 * @date 2019-04-16
 */
public interface DevWorkDayHourMapper {
    /**
     * 查询工单各个IO口每天每小时数据信息
     *
     * @param hourId 工单各个IO口每天每小时数据ID
     * @return 工单各个IO口每天每小时数据信息
     */
//    @DataSource(DataSourceType.SLAVE)
    public DevWorkDayHour selectDevWorkDayHourById(Integer hourId);

    /**
     * 查询工单各个IO口每天每小时数据列表
     *
     * @param devWorkDayHour 工单各个IO口每天每小时数据信息
     * @return 工单各个IO口每天每小时数据集合
     */
//    @DataSource(DataSourceType.SLAVE)
    public List<DevWorkDayHour> selectDevWorkDayHourList(DevWorkDayHour devWorkDayHour);

    /**
     * 新增工单各个IO口每天每小时数据
     *
     * @param devWorkDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
//    @DataSource(DataSourceType.SLAVE)
    public int insertDevWorkDayHour(DevWorkDayHour devWorkDayHour);

    /**
     * 修改工单各个IO口每天每小时数据
     *
     * @param devWorkDayHour 工单各个IO口每天每小时数据信息
     * @return 结果
     */
//    @DataSource(DataSourceType.SLAVE)
    public int updateDevWorkDayHour(DevWorkDayHour devWorkDayHour);

    /**
     * 更新指定时间的硬件IO口24小时数据
     * @param hour 指定小时
     * @param data 每小时记录
     * @param workId 工单id
     * @param devId 硬件id
     * @param ioId IO口id
     * @param dataTime 规定时间
     * @return 结果
     */
//    @DataSource(DataSourceType.SLAVE)
    int updateDevWorkDayHour2(@Param("hour") int hour, @Param("data") int data, @Param("workId") Integer workId,
                              @Param("devId") Integer devId, @Param("ioId") Integer ioId, @Param("dataTime") Date dataTime);

    /**
     * 删除工单各个IO口每天每小时数据
     *
     * @param hourId 工单各个IO口每天每小时数据ID
     * @return 结果
     */
//    @DataSource(DataSourceType.SLAVE)
    public int deleteDevWorkDayHourById(Integer hourId);

    /**
     * 批量删除工单各个IO口每天每小时数据
     *
     * @param hourIds 需要删除的数据ID
     * @return 结果
     */
//    @DataSource(DataSourceType.SLAVE)
    public int deleteDevWorkDayHourByIds(String[] hourIds);

    /**
     * 查询对应公司对应产线对应工单对应硬件对应IO口上一个小时的产量数据
     *
     * @param company_id 对应公司
     * @param line_id    对应产线
     * @param work_id    对应工单
     * @param dev_id     对应硬件
     * @param io_id      对应IO口
     * @param hour       对应上一个小时
     * @return 结果
     */
//    @DataSource(DataSourceType.SLAVE)
    int sumCompanyLineWorkDevIoHour(@Param("company_id") int company_id, @Param("line_id") int line_id, @Param("work_id") int work_id,
                                    @Param("dev_id") int dev_id, @Param("io_id") int io_id, @Param("hour") int hour);

    /**
     * 查询对应公司对应产线正在进行工单对应的硬件IO口24小时的数据
     *
     * @param company_id 公司编号
     * @param line_id    产线编号
     * @param work_id    工单编号
     * @param dev_id     硬件编号
     * @param io_id      工位id
     * @return
     */
//    @DataSource(DataSourceType.SLAVE)
    DevWorkDayHour selectInfoByCompanyLineWorkDevIo(@Param("company_id") int company_id, @Param("line_id") int line_id, @Param("work_id") int work_id,
                                                    @Param("dev_id") int dev_id, @Param("io_id") int io_id);

    /**
     * 通过工单id查询每小时数据记录列表
     *
     * @param workId 工单所属id
     * @return
     */
//    @DataSource(DataSourceType.SLAVE)
    public List<DevWorkDayHour> selectWorkDayHourListBeInOrFinish(@Param("workId") Integer workId);

    /**
     * 查询对应工单，对应IO口，对应时间的硬件IO口24小时数据
     *
     * @param workId   工单id
     * @param lineId  产线id
     * @param devId    硬件Id
     * @param ioId     IO口id
     * @param dataTime 时间
     * @return IO口24小时记录
     */
    DevWorkDayHour selectWorkDayHourListByDate(@Param("workId") Integer workId,
                                               @Param("lineId")Integer lineId,
                                               @Param("devId") Integer devId,
                                               @Param("ioId") Integer ioId,
                                               @Param("dataTime") Date dataTime);
}