package com.ruoyi.project.quality.mesBatch.mapper;

import com.ruoyi.project.quality.mesBatch.domain.MesBatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MES批准追踪 数据层
 *
 * @author sj
 * @date 2019-07-22
 */
public interface MesBatchMapper {
    /**
     * 查询MES批准追踪信息
     *
     * @param id MES批准追踪ID
     * @return MES批准追踪信息
     */
    public MesBatch selectMesBatchById(Integer id);

    /**
     * 查询MES批准追踪列表
     *
     * @param mesBatch MES批准追踪信息
     * @return MES批准追踪集合
     */
    public List<MesBatch> selectMesBatchList(MesBatch mesBatch);

    /**
     * 新增MES批准追踪
     *
     * @param mesBatch MES批准追踪信息
     * @return 结果
     */
    public int insertMesBatch(MesBatch mesBatch);

    /**
     * 修改MES批准追踪
     *
     * @param mesBatch MES批准追踪信息
     * @return 结果
     */
    public int updateMesBatch(MesBatch mesBatch);

    /**
     * 删除MES批准追踪
     *
     * @param id MES批准追踪ID
     * @return 结果
     */
    public int deleteMesBatchById(Integer id);

    /**
     * 批量删除MES批准追踪
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMesBatchByIds(String[] ids);

    /**
     * 通过工单号查询批次追踪列表
     *
     * @param workCode 工单号
     * @return 结果
     */
    List<MesBatch> selectMesBatchListByWorkCode(@Param("companyId") Integer companyId, @Param("workCode") String workCode);

    /**
     * 通过mes查询mes批次追踪信息
     *
     * @param companyId 公司id
     * @param mesCode   mescode
     * @return 结果
     */
    MesBatch selectMesBatchByMesCode(@Param("companyId") Integer companyId, @Param("mesCode") String mesCode);

    /**
     * 查询MES物料批次信息
     *
     * @param mesBatch MES批次
     * @return 结果
     */
    List<MesBatch> selectMesBatchList2(MesBatch mesBatch);

    /**
     * 通过工单编号删除MES批次追踪
     *
     * @param workCode 工单编号
     * @return 结果
     */
    int deleteMesBatchByWorkCode(@Param("workCode") String workCode);

    /**
     * app查询mes信息
     * @param mesBatch mes数据
     * @return 结果
     */
    List<MesBatch> appSelectMesBatchList(MesBatch mesBatch);

    /**
     * 查看mes数据主表
     * @param mesBatch mes数据
     * @return 结果
     */
    MesBatch selectMesBatch(MesBatch mesBatch);
}