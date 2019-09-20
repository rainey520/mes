package com.ruoyi.project.product.materiel.mapper;


import com.ruoyi.project.product.materiel.domain.Materiel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface MaterielMapper {
    /**
     * 查询物料信息
     *
     * @param id 物料ID
     * @return 物料信息
     */
    public Materiel selectMaterielById(Integer id);

    /**
     * 查询物料列表
     *
     * @param materiel 物料信息
     * @return 物料集合
     */
    public List<Materiel> selectMaterielList(Materiel materiel);

    /**
     * 新增物料
     *
     * @param materiel 物料信息
     * @return 结果
     */
    public int insertMateriel(Materiel materiel);

    /**
     * 修改物料
     *
     * @param materiel 物料信息
     * @return 结果
     */
    public int updateMateriel(Materiel materiel);

    /**
     * 删除物料
     *
     * @param id 物料ID
     * @return 结果
     */
    public int deleteMaterielById(Integer id);

    /**
     * 批量删除物料
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterielByIds(String[] ids);

    /**
     * 通过物料编码查询物料信息
     *
     * @param materielCode 物料编码
     * @param companyId    公司id
     * @return
     */
    public Materiel selectMaterielByMaterielCode(@Param("materielCode") String materielCode, @Param("companyId") Integer companyId);

    /**
     * 通过物料编码更新物料信息
     *
     * @param materiel
     */
//    @DataSource(value = DataSourceType.ERP)
    int updateMaterielByMaterielCode(Materiel materiel);

    /**
     * 根据供应商id查询对应的物料信息
     *
     * @param sid 供应商id
     * @return
     */
    List<Materiel> selectMaterielBySupplierId(@Param("cid") int cid, @Param("sid") int sid);

    /**
     * 查询物料信息
     *
     * @param companyId 公司id
     * @return 结果
     */
    List<Materiel> selectAllMatByComId(@Param("companyId") Integer companyId);

    /**
     * 查询物料名称信息
     *
     * @param companyId 公司id
     * @return 结果
     */
    List<Materiel> selectAllMatNameByComId(@Param("companyId") Integer companyId);

    /**
     * 查询对应MES未配置的物料信息
     * @param ruleId 规则id
     * @return 结果
     */
    List<Materiel> selectMaterielByMesNotCof(@Param("ruleId") Integer ruleId);

    /**
     * app查询物料信息
     * @param materiel 物料对象
     * @return 结果
     */
    List<Materiel> appSelectMaterielList(Materiel materiel);
}