package com.ruoyi.project.production.singleWork.service;

import com.ruoyi.project.production.singleWork.domain.SingleWork;
import java.util.List;

/**
 * 单工位数据 服务层
 * 
 * @author sj
 * @date 2019-07-03
 */
public interface ISingleWorkService 
{
	/**
     * 查询单工位数据信息
     * 
     * @param id 单工位数据ID
     * @return 单工位数据信息
     */
	public SingleWork selectSingleWorkById(Integer id);

	/**
	 * 根据id查询车间信息，并获取相关责任人信息
	 * @param id 车间id
	 * @return
	 */
	public SingleWork selectSingleWorkByIdGetUser(Integer id);

	/**
     * 查询单工位数据列表
     * 
     * @param singleWork 单工位数据信息
     * @return 单工位数据集合
     */
	public List<SingleWork> selectSingleWorkList(SingleWork singleWork);

	/**
	 * 查询所以车间
	 * @return
	 */
	public List<SingleWork> selectSingleWorkListSign0();

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
     * 删除单工位数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSingleWorkByIds(String ids);

	/**
	 * 校验车间名称的唯一性
	 * @param singleWork 单工位信息
	 * @return 结果
	 */
    String checkNameUnique(SingleWork singleWork);

	/**
	 * 单工位硬件配置
	 * @param singleWork 单工位信息
	 * @return 结果
	 */
	int saveConfigDev(SingleWork singleWork);

	/**
	 * 根据车间id查询所以单位信息
	 * @param pid 车间id
	 * @return
	 */
	List<SingleWork> selectAllNotConfigChildren(int pid);

	/**
	 * 根据工单id、车间id查询所有未配置的
	 * @param order_id
	 * @param pid
	 * @return
	 */
	 List<SingleWork> selectAllNotConfigWorkByOrderId(int order_id,int pid);

	/**
	 * 查询未配置sop的单工位信息
	 * @param parentId 车间id
	 * @param sopId sopid
	 * @param sopTag sop配置标记
	 * @return 结果
	 */
    List<SingleWork> selectNotConfigSop(int companyId,int parentId, int sopId,int sopTag);

	/**
	 * 通过父id查询车间信息
	 * @param companyId 公司id
	 * @param parentId 父id
	 * @return 结果
	 */
	List<SingleWork> selectSingleWorkByParentId(Integer companyId,Integer parentId);

	/**
	 * app端查询单工位列表信息
	 * @param singleWork 单工位对象
	 * @return 结果
	 */
	List<SingleWork> appSelectSingleWorkList(SingleWork singleWork);
}
