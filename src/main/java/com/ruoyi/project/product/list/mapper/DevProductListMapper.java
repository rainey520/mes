package com.ruoyi.project.product.list.mapper;

import com.ruoyi.project.app.domain.Product;
import com.ruoyi.project.product.list.domain.DevProductList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品管理 数据层
 *
 * @author ruoyi
 * @date 2019-04-10
 */
public interface DevProductListMapper {
    /**
     * 查询产品管理信息
     *
     * @param id 产品管理ID
     * @return 产品管理信息
     */
    public DevProductList selectDevProductListById(Integer id);

    /**
     * 根据产品编码查询对应的产品是否存在
     *
     * @param code
     * @return
     */
    DevProductList selectDevProductByCode(@Param("company_id") int company_id, @Param("code") String code);

    /**
     * 根据公司 产品/半成品编码 查询信息
     *
     * @param company_id
     * @param code
     * @param sign
     * @return
     */
    DevProductList selectDevProductByCodeAndSign(@Param("company_id") int company_id, @Param("code") String code, @Param("sign") int sign);

    /**
     * 查询产品管理列表
     *
     * @param devProductList 产品管理信息
     * @return 产品管理集合
     */
    public List<DevProductList> selectDevProductListList(DevProductList devProductList);

    /**
     * 新增产品管理
     *
     * @param devProductList 产品管理信息
     * @return 结果
     */
    public int insertDevProductList(DevProductList devProductList);

    /**
     * 修改产品管理
     *
     * @param devProductList 产品管理信息
     * @return 结果
     */
    public int updateDevProductList(DevProductList devProductList);

    /**
     * 删除产品管理
     *
     * @param id 产品管理ID
     * @return 结果
     */
    public int deleteDevProductListById(Integer id);

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDevProductListByIds(String[] ids);

    /**
     * 查询所属公司所有的产品信息
     *
     * @return
     */
    List<DevProductList> selectProductAllByCompanyId(@Param("companyId") Integer companyId, @Param("sign") int sign);

    /**
     * 通过产品id查询产品信息
     *
     * @param productId 产品id
     * @return 产品信息
     */
    DevProductList findProductInfo(@Param("productId") Integer productId);

    /**
     * 通过产品编码查询产品信息
     *
     * @param productCode 产品编码
     * @return 产品信息
     */
    DevProductList selectProductByCode(@Param("productCode") String productCode);

    /**
     * 查询产品编码是否为一
     *
     * @param productCode 产品编码
     * @param companyId   公司id
     * @return 查询结果数量
     */
    DevProductList checkProductCodeUnique(@Param("productCode") String productCode, @Param("companyId") Integer companyId);

    /**
     * 查询客户关联的产品
     *
     * @param pid 产品编号集合
     * @return
     */
    List<DevProductList> findCustomerProduct(@Param("array") List<Integer> pid);

    /**
     * 根据订单id查询对应的产品信息
     *
     * @param orderId 订单id
     * @return
     */
    List<DevProductList> findProductByOrderId(@Param("orderId") int orderId);

    /**
     * 根据产品编码查询对应公司的产品信息
     *
     * @param companyId   公司id
     * @param productCode 产品编码
     * @return
     */
    DevProductList findByCompanyIdAndProductCode(@Param("companyId") int companyId,
                                                 @Param("productCode") String productCode);

    /**
     * 查询产品名称信息
     *
     * @param companyId 公司id
     * @return 结果
     */
    List<DevProductList> selectProNameAllByComId(@Param("companyId") Integer companyId, @Param("sign") int sign);

    /**
     * 根据产线id查询所以未配置的产品信息
     *
     * @param lineId 产线id
     * @param sopTag sop配置标记流水线或者车间
     * @return 结果
     */
    List<DevProductList> selectNotConfigByLineId(@Param("lineId") int lineId, @Param("companyid") int companyid,
                                                 @Param("sopTag") int sopTag, @Param("isoId") int isoId);

    List<DevProductList> selectNotConfigByLineId2(@Param("lineId") int lineId, @Param("companyid") int companyid,
                                                  @Param("sopTag") int sopTag);

    /**
     * 根据sop 产线 配置查询已经配置的产品信息
     *
     * @param sid
     * @param companyId
     * @return
     */
    List<DevProductList> selectSopConfigBySid(@Param("sid") int sid, @Param("companyId") int companyId);

    /**
     * 根据作业指导书id查询所有配置的产品信息
     *
     * @param isoId     作业指导书id
     * @param companyId 公司id
     * @return 结果
     */
    List<DevProductList> selectNotConfigByIsoId(@Param("isoId") Integer isoId, @Param("companyId") Integer companyId);

    /**
     * 查询公司的所有产品信息包括半成品
     *
     * @param companyId 公司id
     * @return 结果
     */
    List<DevProductList> selectProductAll(@Param("companyId") Integer companyId);

    /**
     * 修改对应的产品MES规则
     *
     * @param id     产品/半成品id
     * @param ruleId 规则id
     * @return
     */
    int saveMesRuleConfig(@Param("id") int id, @Param("ruleId") int ruleId);

    /**
     * 查看mes规格配置明细
     *
     * @param productList 产品
     * @return 结果
     */
    List<DevProductList> selectMesCfList(DevProductList productList);

    /**
     * 通过配置规则id查询配置的产品信息
     *
     * @param companyId 公司id
     * @param ruleId    追踪规格id
     * @return 结果
     */
    List<DevProductList> selectDevProductByRuleId(@Param("companyId") Integer companyId, @Param("ruleId") Integer ruleId);

    /**
     * 通过标记查询半成品或者成品信息
     *
     * @param sign 半成品成品标记
     * @return 结果
     */
    List<DevProductList> selectProAllBySign(@Param("companyId") Integer companyId, @Param("sign") Integer sign);

    /**
     * 查询MES配置规则未配置的半成品信息
     *
     * @param ruleId MES规则id
     * @return 结果
     */
    List<DevProductList> selectPartListByMesNotCof(@Param("ruleId") Integer ruleId);

    /**
     * app检索产品信息
     *
     * @param product 产品信息
     * @return 结果
     */
    List<Product> appSearchProList(Product product);
}