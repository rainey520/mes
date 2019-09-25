package com.ruoyi.project.quality.afterService.mapper;

import com.ruoyi.project.quality.afterService.domain.AfterService;
import com.ruoyi.project.quality.afterService.domain.AfterServiceItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 售后服务 数据层
 *
 * @author sj
 * @date 2019-08-20
 */
public interface AfterServiceMapper {
    /**
     * 查询售后服务信息
     *
     * @param id 售后服务ID
     * @return 售后服务信息
     */
    public AfterService selectAfterServiceById(Integer id);

    /**
     * 查询售后服务列表
     *
     * @param afterService 售后服务信息
     * @return 售后服务集合
     */
    public List<AfterService> selectAfterServiceList(AfterService afterService);

    /**
     * 新增售后服务
     *
     * @param afterService 售后服务信息
     * @return 结果
     */
    public int insertAfterService(AfterService afterService);

    /**
     * 修改售后服务
     *
     * @param afterService 售后服务信息
     * @return 结果
     */
    public int updateAfterService(AfterService afterService);

    /**
     * 删除售后服务
     *
     * @param id 售后服务ID
     * @return 结果
     */
    public int deleteAfterServiceById(Integer id);

    /**
     * 批量删除售后服务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAfterServiceByIds(String[] ids);

    /**
     * 通过新增的搜索条件查询记录数
     *
     * @param searchItems 搜索条件
     * @param params      参数
     * @return 结果
     */
    List<AfterService> selectAfterServiceListBySearchItems(@Param("searchItems") String[] searchItems, @Param("params") Map<String, Object> params);

    /**
     * 通过搜索条件查询所有的录入者
     *
     * @param companyId  公司id
     * @param searchItem 搜索条件
     * @param params     搜索参数
     * @return 结果
     */
    String selectListBySearchInfoUserName(@Param("companyId") Integer companyId, @Param("searchItem") String searchItem, @Param("params") Map<String, Object> params);

    /**
     * 通过搜索条件的查询列表
     *
     * @param afterService  售后检索
     * @return 结果
     */
    AfterServiceItem selectListByBatchInfo(AfterService afterService);
}