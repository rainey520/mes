package com.ruoyi.project.production.devWorkOrder.mapper;

import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 工单 数据层
 *
 * @author zqm
 * @date 2019-04-12
 */
public interface DevWorkOrderMapper {
    /**
     * 查询工单信息
     *
     * @param id 工单ID
     * @return 工单信息
     */
    public DevWorkOrder selectDevWorkOrderById(Integer id);

    /**
     * 查询工单列表
     *
     * @param devWorkOrder 工单信息
     * @return 工单集合
     */
    public List<DevWorkOrder> selectDevWorkOrderList(DevWorkOrder devWorkOrder);

    /**
     * 新增工单
     *
     * @param devWorkOrder 工单信息
     * @return 结果
     */
    public int insertDevWorkOrder(DevWorkOrder devWorkOrder);

    /**
     * 修改工单
     *
     * @param devWorkOrder 工单信息
     * @return 结果
     */
    public int updateDevWorkOrder(DevWorkOrder devWorkOrder);

    /**
     * 删除工单
     *
     * @param id 工单ID
     * @return 结果
     */
    public int deleteDevWorkOrderById(Integer id);

    /**
     * 批量删除工单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDevWorkOrderByIds(String[] ids);

    /**
     * 校验工单号是否存在
     *
     * @return
     */
    public DevWorkOrder checkWorkOrderNumber(@Param("workorderNumber") String workorderNumber, @Param("companyId") Integer companyId);

    /**
     * 查询对应公司对应产线正在进行中的工单
     *
     * @param compand_id 公司编号
     * @param line_id    产线编号
     * @param wlSign 车间或者流水线标记
     * @return
     */
    DevWorkOrder selectWorkByCompandAndLine(@Param("compand_id") int compand_id, @Param("line_id") int line_id,@Param("wlSign") int wlSign);

    DevWorkOrder selectWorkByCompandAndLine2(@Param("compand_id") int compand_id, @Param("line_id") int line_id);

    /**
     * 查询当天对应公司对应产线的工单计划的工单编号
     *
     * @param wlSign 车间或者流水线标记
     * @param company_id 公司编号
     * @param line_id    查询编号
     * @return
     */
    List<DevWorkOrder> selectDayWorkOrder(@Param("wlSign") Integer wlSign,@Param("company_id") int company_id, @Param("line_id") int line_id);


    /**
     * 判断流水线是否只有一个正在进行状态的工单
     * @param lineId 产线/车间id
     * @param sign 标记  0、产线 1、车间
     * @return
     */
    DevWorkOrder checkWorkLineUnique(@Param("lineId") Integer lineId,@Param("sign")int sign);

    /**
     * 查询生产状态处于正在进行的所有工单
     *
     * @return
     */
    List<DevWorkOrder> selectWorkOrderAllBeIn(@Param("companyId") Integer companyId);

    /**
     * 查询当天所有的工单
     *
     * @return
     */
    List<DevWorkOrder> selectWorkOrderAllToday(@Param("companyId") Integer companyId);

    /**
     * 查询对应时间段内产线已经提交的所以工单数据
     *
     * @param line_id   产线编号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    List<DevWorkOrder> selectOrderByLineIsSubmit(@Param("company_id") int company_id,
                                                 @Param("productCode")String productCode,
                                                 @Param("line_id") int line_id,
                                                 @Param("startTime") String startTime,
                                                 @Param("endTime") String endTime,
                                                 @Param("wlSign")int wlSign);

    /**
     * 根据用户查询对应的工单信息
     * @param company_id 公司id
     * @param productCode 产品/半成品
     * @param line_id //车间id
     * @param startTime //开始时间
     * @param endTime //结束时间
     * @param wlSign//工单标记  1
     * @param userId //员工id
     * @return
     */
    List<DevWorkOrder> selectOrderBySingleIsSubmit(@Param("company_id") int company_id,
                                                   @Param("productCode")String productCode,
                                                   @Param("line_id") int line_id,
                                                   @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime,
                                                   @Param("wlSign")int wlSign,
                                                   @Param("userId")int userId);


    /**
     * 查询昨天生产的工单
     *
     * @return
     */
    List<DevWorkOrder> selectWorkOrderAllYesterday(@Param("companyId") Integer companyId);

    /**
     * 查询当天正在生产或者已经完成的所有工单
     *
     * @return
     */
    List<DevWorkOrder> selectWorkOrderTodayBeInOrFinish();

    /**
     * 还未确认数据的所有工单信息
     *
     * @param wlSign 车间或者流水线标记
     * @return 结果
     */
    List<DevWorkOrder> selectWorkOrderAllBeInOrFinish(@Param("wlSign") Integer wlSign);

    /**
     * 通过产线id或者工单id查询已经提交的工单列表
     *
     * @param companyId 公司id
     * @param lineId    产线
     * @param workOrderId 工单id
     * @return 结果
     */
    List<DevWorkOrder> selectWorkOrderFinishByLineIdOrWorkOrderId(@Param("companyId") Integer companyId,
                                                                  @Param("lineId") Integer lineId,
                                                                  @Param("workOrderId") Integer workOrderId);

    /**
     * 修改对应公司对应产品未进行的工单的ecn状态
     * @param companyId 公司id
     * @param productCode 产品编码
     * @return
     */
    int editCompanyProductWorkOrderEcn(@Param("companyId")int companyId,@Param("productCode")String productCode);

    /**
     * 根据公司id产线id查询对应的数据
     * @param companyId 公司id
     * @param lineId 产线id
     * @return
     */
    List<DevWorkOrder> selectWorkDataByCompanyIdAndLineId(@Param("companyId")int companyId,@Param("lineId")int lineId);

    /**
     * 查询最近完成工单信息
     * @param compand_id 公司id
     * @param line_id 产线id
     * @param wlSign 车间或者流水线标记
     * @return
     */
    DevWorkOrder selectLatelyCompleteWork(@Param("compand_id") int compand_id, @Param("line_id") int line_id,@Param("wlSign") int wlSign);

    /**
     * 修改已经完成的工单标记
     * @param workid
     * @return
     */
    int editLatelyCompleteWorkSign(@Param("workid")int workid);

    /**
     * 根据查询id查询是否存在未完成工单信息
     * @param lineId
     * @return
     */
    DevWorkOrder selectWorkByLineId(@Param("lineId")int lineId);

    /**
     * 将工单作废
     * @param workId 工单id
     * @return
     */
    int updateWorkOrderAbolish(@Param("workId")int workId);

    /**
     * 通过工单下发车间或流水线状态查询对应的所有工单信息
     * @param companyId 公司id
     * @param wlSign 工单车间流水标记
     * @return 结果
     */
    List<DevWorkOrder> selectWorkListInSw(@Param("companyId") Integer companyId,@Param("wlSign")Integer wlSign);

    /**
     * 通过工单进行状态查询工单列表
     * @param companyId 公司id
     * @param workOrderStatus 工单进行状态
     * @return 结果
     */
    List<DevWorkOrder> selectWorkListInWorkStatus(@Param("companyId") Integer companyId,@Param("workOrderStatus") Integer workOrderStatus);

    /**
     * 通过工单id查询工单信息
     * @param companyId 工单id
     * @param workId 工单id
     * @return 结果
     */
    DevWorkOrder selectWorkOrderInfoById(@Param("companyId") Integer companyId,@Param("workId") Integer workId);

    /**
     * 查询单工位未配置的下到对应车间的未确认数据的工单信息
     * @param lineId 车间id
     * @param workStatus 工单状态
     * @param wlSign 车间或者流水线标记
     * @param singleId 单工位id
     * @param companyId 公司id
     * @return 结果
     */
    List<DevWorkOrder> selectAllNotConfigBySwId(@Param("lineId") Integer lineId, @Param("workorderStatus") Integer workStatus,
                                                @Param("wlSign") Integer wlSign,@Param("singleId") Integer singleId,@Param("companyId") Integer companyId);

    /**
     * 查询正在车间对应单工位生产的工单信息
     * @param companyId 公司id
     * @param lineId 车间id
     * @param wlSign 车间或者流水线标记
     * @param singleId 单工位id
     * @param workStatus 工单状态
     * @return 结果
     */
    DevWorkOrder selectWorkInHouseBeInBySingId(@Param("companyId") Integer companyId,
                                               @Param("lineId") Integer lineId,@Param("wlSign") Integer wlSign,
                                               @Param("singleId") Integer singleId, @Param("workStatus") Integer workStatus);

    /**
     * 查询车间对应工单对应工位的最后一条记录
     * @param companyId 公司id
     * @param lineId 产线id
     * @param wlSign 车间或者流水线标记
     * @param singleId 单工位id
     * @param workStatus 工单状态
     * @return 结果
     */
    DevWorkOrder selectWorkInHouseLastByWorkStatus(@Param("companyId") Integer companyId, @Param("lineId") Integer lineId,
                                                   @Param("wlSign") Integer wlSign,@Param("singleId") Integer singleId, @Param("workStatus") Integer workStatus);

    /**
     * 通过工单号查询工单信息
     * @param workCode 工单号
     * @return 结果
     */
    DevWorkOrder selectWorkOrderByCode(@Param("workCode") String workCode);

    /**
     * app端查看两条工单
     * @return 结果
     */
    List<DevWorkOrder> appSelectWorkListTwo();

    /**
     * 修改工单推送反馈信息
     * @param id 工单id
     * @param resSign 反馈状态
     * @return
     */
    @Update("update dev_work_order set res_sign = #{resSign} where id  = #{id}")
    int updateWorkOrderResSign(@Param("id")int id,@Param("resSign")int resSign);
}