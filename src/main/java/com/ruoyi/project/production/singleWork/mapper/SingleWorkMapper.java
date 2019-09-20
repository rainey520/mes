package com.ruoyi.project.production.singleWork.mapper;

import com.ruoyi.project.production.singleWork.domain.SingleWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单工位数据 数据层
 *
 * @author sj
 * @date 2019-07-03
 */
public interface SingleWorkMapper {
    /**
     * 查询单工位数据信息
     *
     * @param id 单工位数据ID
     * @return 单工位数据信息
     */
    public SingleWork selectSingleWorkById(Integer id);

    /**
     * 查询单工位数据列表
     *
     * @param singleWork 单工位数据信息
     * @return 单工位数据集合
     */
    public List<SingleWork> selectSingleWorkList(SingleWork singleWork);

    /**
     * 新增单工位数据
     *
     * @param singleWork 单工位数据信息
     * @return 结果
     */
    public int insertSingleWork(SingleWork singleWork);

    /**
     * 修改单工位数据
     *
     * @param singleWork 单工位数据信息
     * @return 结果
     */
    public int updateSingleWork(SingleWork singleWork);
    /**
     * 修改单工位数据
     *
     * @param singleWork 单工位数据信息
     * @return 结果
     */
    public int updateSingleWork1(SingleWork singleWork);

    /**
     * 删除单工位数据
     *
     * @param id 单工位数据ID
     * @return 结果
     */
    public int deleteSingleWorkById(Integer id);

    /**
     * 批量删除单工位数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSingleWorkByIds(String[] ids);

    /**
     * 通过车间名查询单工位信息
     *
     * @param companyId    公司id
     * @param workshopName 车间名
     * @return 结果
     */
    SingleWork selectSingleWorkByWorkshopName(@Param("companyId") Integer companyId, @Param("workshopName") String workshopName);

    /**
     * 通过父id查询单工位列表
     * @param companyId 公司id
     * @param parentId 父id
     * @return 结果
     */
    List<SingleWork> selectSingleWorkByParentId(@Param("companyId") Integer companyId, @Param("parentId") Integer parentId);

    /**
     * 根据硬件id查询对应的工位配置信息，没次调用只能使用一个参数
     * @param dev_id 计数器硬件id
     * @param watch_id 看板硬件id
     * @param e_id mes id
     * @return
     */
    SingleWork selectSingleWorkByCode(@Param("dev_id")int dev_id,@Param("watch_id")int watch_id,@Param("e_id")int e_id);

    /**
     * 根据车间id查询所以单位信息
     * @param pid 车间id
     * @return
     */
    List<SingleWork> selectAllNotConfigChildren(@Param("pid")int pid);

    /**
     * 根据工单id、车间id查询所有未配置的
     * @param order_id 工单id
     * @param pid 车间id
     * @return
     */
    List<SingleWork> selectAllNotConfigWorkByOrderId(@Param("order_id")int order_id,@Param("pid") int pid);

    /**
     * 查询还未配置的单工位信息
     * @param companyId 公司id
     * @param parentId 车间id
     * @param sopId sopid
     * @param sopTag sop配置标记
     * @return 结果
     */
    List<SingleWork> selectNotConfigSop(@Param("companyId") int companyId, @Param("parentId") int parentId,
                                        @Param("sopId") int sopId,@Param("sopTag") int sopTag);



    /**
     * 查询对应工单的所有责任人信息
     * @param workId 工单id
     * @param companyId 公司id
     * @param sign 车间或者单工位标记
     * @return 结果
     */
    List<SingleWork> selectWorkUserInfo(@Param("workId") Integer workId, @Param("companyId") Integer companyId,@Param("sign") Integer sign);
}