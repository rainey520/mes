package com.ruoyi.project.production.singleWork.mapper;


import com.ruoyi.project.production.singleWork.domain.SingleWorkOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单工位与工单进行配置 数据层
 *
 * @author sj
 * @date 2019-07-09
 */
public interface SingleWorkOrderMapper {
    /**
     * 查询单工位与工单进行配置信息
     *
     * @param id 单工位与工单进行配置ID
     * @return 单工位与工单进行配置信息
     */
    public SingleWorkOrder selectSingleWorkOrderById(Integer id);

    /**
     * 查询单工位与工单进行配置列表
     *
     * @param singleWorkOrder 单工位与工单进行配置信息
     * @return 单工位与工单进行配置集合
     */
    public List<SingleWorkOrder> selectSingleWorkOrderList(SingleWorkOrder singleWorkOrder);

    /**
     * 新增单工位与工单进行配置
     *
     * @param singleWorkOrder 单工位与工单进行配置信息
     * @return 结果
     */
    public int insertSingleWorkOrder(SingleWorkOrder singleWorkOrder);

    /**
     * 修改单工位与工单进行配置
     *
     * @param singleWorkOrder 单工位与工单进行配置信息
     * @return 结果
     */
    public int updateSingleWorkOrder(SingleWorkOrder singleWorkOrder);

    /**
     * 删除单工位与工单进行配置
     *
     * @param id 单工位与工单进行配置ID
     * @return 结果
     */
    public int deleteSingleWorkOrderById(Integer id);

    /**
     * 批量删除单工位与工单进行配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSingleWorkOrderByIds(String[] ids);

    /**
     * 通过工单id车间id查询相关单工位工位记录信息
     *
     * @param singleP 车间id
     * @param workId 工单id
     * @return 结果
     */
    List<SingleWorkOrder> selectSingleWorkByWorkIdAndPid(@Param("singleP") Integer singleP, @Param("workId") Integer workId);

}