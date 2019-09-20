package com.ruoyi.project.production.devWorkData.mapper;

import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工单数据 数据层
 *
 * @author zqm
 * @date 2019-04-15
 */
public interface DevWorkDataMapper {
    /**
     * 查询工单数据信息
     *
     * @param dataId 工单数据ID
     * @return 工单数据信息
     */
    public DevWorkData selectDevWorkDataById(Integer dataId);

    /**
     * 查询工单数据列表
     *
     * @param devWorkData 工单数据信息
     * @return 工单数据集合
     */
    public List<DevWorkData> selectDevWorkDataList(DevWorkData devWorkData);

    /**
     * 新增工单数据
     *
     * @param devWorkData 工单数据信息
     * @return 结果
     */
//	@DataSource(DataSourceType.SLAVE)
    public int insertDevWorkData(DevWorkData devWorkData);

    /**
     * 修改工单数据
     *
     * @param devWorkData 工单数据信息
     * @return 结果
     */
    public int updateDevWorkData(DevWorkData devWorkData);

    /**
     * 删除工单数据
     *
     * @param dataId 工单数据ID
     * @return 结果
     */
    public int deleteDevWorkDataById(Integer dataId);

    /**
     * 批量删除工单数据
     *
     * @param dataIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDevWorkDataByIds(String[] dataIds);

    /**
     * 查询对应的公司对应产线对应工单对应硬件对应IO口的数据
     *
     * @param company_id 公司编号
     * @param line_id    产线编号
     * @param work_id    工单编号
     * @param dev_id     硬件编号
     * @param io_id      工位id
     * @param scType     车间或流水线标识
     * @return
     */
//	@DataSource(DataSourceType.SLAVE)
    DevWorkData selectWorkDataByCompanyLineWorkDev(@Param("company_id") int company_id, @Param("line_id") int line_id, @Param("work_id") int work_id,
                                                   @Param("dev_id") int dev_id, @Param("io_id") int io_id, @Param("scType") int scType);

    /**
     * 记录第一次已经上传的数据
     *
     * @param id           编号
     * @param initial_data 工单开始时硬件第一次上传的数据
     * @return
     */
//	@DataSource(DataSourceType.SLAVE)
    int initWorkData(@Param("id") int id, @Param("initial_data") int initial_data, @Param("sign") int sign);

    /**
     * 修改对应的累计产量
     *
     * @param id    编号
     * @param total 累计产量
     * @return
     */
//	@DataSource(DataSourceType.SLAVE)
    int saveTotalWorkData(@Param("id") int id, @Param("total") int total, @Param("sign") int sign);

    /**
     * 需修改对应工单数据标记，需要重新记录初始值
     *
     * @param work_id
     * @return
     */
//	@DataSource(DataSourceType.SLAVE)
    int updateWorkSigInit(@Param("work_id") int work_id);

    /**
     * 查询对应工单已做未标记数据的工单数据
     *
     * @param companyId 公司id
     * @param workId    工单id
     * @param lineId    产线id
     * @param devId     硬件id
     * @param scType    车间或流水线标记
     * @return 结果
     */
//	@DataSource(DataSourceType.SLAVE)
    DevWorkData selectWorkDataByIosign(@Param("companyId") Integer companyId,
                                       @Param("workId") Integer workId,
                                       @Param("lineId") Integer lineId,
                                       @Param("devId") Integer devId,
                                       @Param("scType") Integer scType);

    /**
     * 查询车间工单的累计产量信息
     *
     * @param companyId 公司id
     * @param lineId    车间id
     * @param workId    工单id
     * @param scType    车间或者流水线标记
     * @return 结果
     */
    DevWorkOrder selectHouseWorkDataActualNum(@Param("companyId") Integer companyId, @Param("lineId") Integer lineId,
                                              @Param("workId") Integer workId, @Param("scType") Integer scType);
}