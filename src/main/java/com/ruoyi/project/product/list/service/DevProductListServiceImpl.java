package com.ruoyi.project.product.list.service;


import com.ruoyi.common.constant.ProductConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.app.domain.Product;
import com.ruoyi.project.product.importConfig.domain.ImportConfig;
import com.ruoyi.project.product.importConfig.mapper.ImportConfigMapper;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import com.ruoyi.project.quality.mesBatchRule.domain.MesBatchRule;
import com.ruoyi.project.quality.mesBatchRule.mapper.MesBatchRuleMapper;
import com.ruoyi.project.system.user.domain.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 产品管理 服务层实现
 *
 * @author ruoyi
 * @date 2019-04-10
 */
@Service("product")
public class DevProductListServiceImpl implements IDevProductListService {
    @Autowired
    private DevProductListMapper devProductListMapper;

    @Autowired
    private ImportConfigMapper configMapper;

    @Autowired
    private MesBatchRuleMapper mesBatchRuleMapper;

    @Override
    public List<DevProductList> findProductByCustomerId(Integer customerId) {
        return null;
    }

    @Override
    public DevProductList findProductByProIdAndCusId(Integer productId, Integer customerId) {
        return null;
    }

    @Override
    public List<DevProductList> selectProductByCustomerId(int customerId) {
        return null;
    }

    /**
     * 查询产品管理信息
     *
     * @param id 产品管理ID
     * @return 产品管理信息
     */
    @Override
    public DevProductList selectDevProductListById(Integer id) {
        return devProductListMapper.selectDevProductListById(id);
    }

    /**
     * 查询产品管理列表
     *
     * @param devProductList 产品管理信息
     * @return 产品管理集合
     */
    @Override
    public List<DevProductList> selectDevProductListList(DevProductList devProductList) {
        List<DevProductList> list = devProductListMapper.selectDevProductListList(devProductList);
        return list;
    }


    /**
     * 新增产品管理
     *
     * @param devProductList 产品管理信息
     * @return 结果
     */
    @Override
    public int insertDevProductList(DevProductList devProductList,HttpServletRequest request) {
        User currentUser = JwtUtil.getTokenUser(request);
        if (StringUtils.isNull(currentUser)) {
            return 0;
        }
        DevProductList product = devProductListMapper.selectDevProductByCode(currentUser.getCompanyId(), devProductList.getProductCode());
        if (product != null) return 0;
        if (devProductList.getPriceImport() != 0.0f) {
            // 设置导入价格
            devProductList.setPrice(new BigDecimal(devProductList.getPriceImport()));
        }
        devProductList.setCompanyId(currentUser.getCompanyId());
        devProductList.setCreate_by(currentUser.getUserName());
        return devProductListMapper.insertDevProductList(devProductList);
    }

    /**
     * 修改产品管理
     *
     * @param devProductList 产品管理信息
     * @return 结果
     */
    @Override
    public int updateDevProductList(DevProductList devProductList) {
        if (devProductList.getPriceImport() != 0.0f) {
            // 设置导入价格
            devProductList.setPrice(new BigDecimal(devProductList.getPriceImport()));
        }
        return devProductListMapper.updateDevProductList(devProductList);
    }

    /**
     * 删除产品管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDevProductListByIds(String ids,HttpServletRequest request) {
        return devProductListMapper.deleteDevProductListByIds(Convert.toStrArray(ids));
    }

    @Override
    public String importProduct(MultipartFile file, boolean isUpdateSupport,int cType) throws Exception {
        User u = JwtUtil.getTokenUser(ServletUtils.getRequest());
        int failNum =  0;
        int inserNum =0;
        String code = cType ==0?"产品":"半成品";
        StringBuilder failMsg = new StringBuilder();
        StringBuilder successMsg = new StringBuilder();
        ImportConfig config = configMapper.selectImportConfigByType(u.getCompanyId(),cType);
        if(config == null){
            failMsg.insert(0,"很抱歉，导入失败，请先进行"+code+"导入信息配置");
            throw  new Exception(failMsg.toString());
        }
        //创建excel 文档对象
        Workbook wb = null;
        Sheet sheet = null;
        try {
            wb = WorkbookFactory.create(file.getInputStream());
        }catch (Exception e){
            failMsg.insert(0,"很抱歉，导入失败，只支持excel文件导入");
            throw  new Exception(failMsg.toString());
        }
        if(wb == null){
            failMsg.insert(0,"很抱歉，导入失败，只支持excel文件导入");
            throw  new Exception(failMsg.toString());
        }
        sheet = wb.getSheetAt(0);
        if(sheet == null){
            failMsg.insert(0,"很抱歉，导入失败，只支持excel文件导入");
            throw  new Exception(failMsg.toString());
        }
        //获取导入行数
        int rows = sheet.getPhysicalNumberOfRows();
        if(rows <=0){
            failMsg.insert(0,"很抱歉，导入失败，导入excel不能为空");
            throw  new Exception(failMsg.toString());
        }
        if(rows < config.getRowIndex()){
            failMsg.insert(0,"很抱歉，导入失败，导入excel行数必须大配置开始解析行数，请核对配置是否正确");
            throw  new Exception(failMsg.toString());
        }
        Row row = null;
        List<DevProductList> productList = new ArrayList<>();
        DevProductList product = null;
        //开始信息验证
        for (int i= config.getRowIndex() -1;i<rows;i++){
            row = sheet.getRow(i);
            if(row == null){
                failNum++;
                failMsg.append("<br/>第"+(i+1)+"行数据为空");
                continue;
            }
            product = new DevProductList();
            //获取产品编码
            String pCode = ExcelUtil.getCellValue1(row,config.getCon1()-1).toString().trim();
            if(StringUtils.isEmpty(pCode)){
                failNum ++;
                failMsg.append("<br/>第"+(i+1)+"行，"+code+"编码为空");
                continue;
            }
            product.setCompanyId(u.getCompanyId());
            product.setProductCode(pCode);
            //获取产品名称
            String pName = ExcelUtil.getCellValue1(row,config.getCon2()-1).toString().trim();
            if(StringUtils.isEmpty(pName)){
                failNum ++;
                failMsg.append("<br/>第"+(i+1)+"行，"+code+"名称为空");
                continue;
            }
            product.setProductName(pName);
            //获取产品型号
            String pModel = ExcelUtil.getCellValue1(row,config.getCon3() -1).toString().trim();
            if(StringUtils.isEmpty(pModel)){
                failNum ++;
                failMsg.append("<br/>第"+(i+1)+"行，"+code+"型号为空");
                continue;
            }
            product.setProductModel(pModel);
            //获取产品标准工时
            try {
                Integer hours = Integer.parseInt(ExcelUtil.getCellValue1(row,config.getCon4()-1).toString());
                if(hours == null || hours <=0){
                    failNum++;
                    failMsg.append("<br/>第"+(i+1)+"行，标准工时必须大于0");
                    continue;
                }
                product.setStandardHourYield(hours);
            }catch (Exception e){
                failNum++;
                failMsg.append("<br/>第"+(i+1)+"行,标准工时解析失败");
                continue;
            }
            //获取单价
            if(config.getPrice() > 0 ){
                try {
                    float price = Float.parseFloat(ExcelUtil.getCellValue1(row,config.getPrice()-1).toString());
                    product.setPriceImport(price);
                    product.setPrice(new BigDecimal(price));
                }catch (Exception e){
                    failNum++;
                    failMsg.append("<br/>第"+(i+1)+"行,"+code+"单价解析失败");
                    continue;
                }
            }
            //获取产品单位
            if(config.getUnit() >0){
                String unit = ExcelUtil.getCellValue1(row,config.getUnit() -1).toString().trim();
                product.setUnit(unit);
            }
            //获取备注信息
            if(config.getCon5() > 0){
                String remark = ExcelUtil.getCellValue1(row,config.getCon5() -1).toString();
                product.setRemark(remark);
            }
            product.setCreate_by(u.getLoginName());
            product.setCreateTime(new Date());
            product.setSign(0);
            product.setDef_id(0);
            productList.add(product);
         }
        if(wb != null){
            wb.close();
        }
        if(productList.size() <=0 || failNum > 0){
            failMsg.insert(0,"很抱歉，导入失败");
            throw new Exception(failMsg.toString());
        }
        failNum = 0;
        int a =0;
        for (DevProductList devProductList : productList) {
            devProductList.setSign(cType);
            try {
                a++;
                //根据产品编码产线对应的产品是否存在
                DevProductList productList1 = devProductListMapper.selectDevProductByCodeAndSign(u.getCompanyId(),devProductList.getProductCode(),cType);
                if(StringUtils.isNull(productList1)){
                    devProductList.setDef_id(0);
                    inserNum++;
                    devProductListMapper.insertDevProductList(devProductList);
                    successMsg.append("<br/>"+a+"、"+code+devProductList.getProductCode()+"新增成功");
                }else if(isUpdateSupport){
                    devProductList.setId(productList1.getId());
                    devProductList.setCompanyId(u.getCompanyId());
                    devProductList.setCreateTime(productList1.getCreateTime());
                    devProductListMapper.updateDevProductList(devProductList);
                    inserNum++;
                    successMsg.append("<br/>"+code+devProductList.getProductCode()+"修改成功");
                }else {
                    failNum ++;
                    failMsg.append("<br/>"+a+"、"+code+devProductList.getProductCode()+"已存在");
                }
            }catch (Exception e){
                failNum ++;
                failMsg.append("<br/>"+failNum+"、"+code+devProductList.getProductCode()+"导入失败："+e.getMessage());
            }
        }
        if(failNum > 0){
                failMsg.insert(0,"很抱歉，部分导入失败！共"+failNum+"条数据:");
                throw new Exception(failMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + inserNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 查询所属公司所有的产品
     *
     * @return
     */
    @Override
    public List<DevProductList> selectProductAllByCompanyId(int sign) {
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        if (user == null) return Collections.emptyList();
        return devProductListMapper.selectProductAllByCompanyId(user.getCompanyId(),sign);
    }

    /**
     * 通过产品id查询产品信息
     *
     * @param productId
     * @return
     */
    @Override
    public DevProductList findProductInfo(Integer productId,HttpServletRequest request) {
        DevProductList productInfo = devProductListMapper.findProductInfo(productId);
        return productInfo;
    }

    /**
     * 检验产品编码是否唯一
     *
     * @param product
     * @return
     */
    @Override
    public String checkProductCodeUnique(DevProductList product, HttpServletRequest request) {
        Integer companyId = JwtUtil.getTokenUser(request).getCompanyId();
        DevProductList productUnique = devProductListMapper.checkProductCodeUnique(product.getProductCode(), companyId);
        if (!StringUtils.isNull(productUnique) && product.getId() != productUnique.getId()) {
            return ProductConstants.PRODUCT_CODE_NOT_UNIQUE;
        }
        return ProductConstants.PRODUCT_CODE_UNIQUE;
    }


    /**
     * 根据订单id查询对应的产品信息
     *
     * @param orderId 订单id
     * @return
     */
    @Override
    public List<DevProductList> selectProductAllByOrderId(int orderId,HttpServletRequest request) {
        if (orderId == -1) {
            return this.selectProductAllByCompanyId(0);
        } else {
            return devProductListMapper.findProductByOrderId(orderId);
        }
    }

    /**
     * 查询各公司产品名称信息
     * 名称可能存在重复情况
     *
     * @return 结果
     */
    @Override
    public List<DevProductList> selectProNameAllByComId(int sign) {
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        if (user == null) {
            return Collections.emptyList();
        }
        return devProductListMapper.selectProNameAllByComId(user.getCompanyId(),sign);
    }



    @Override
    public List<DevProductList> selectNotConfigByLineId2(Integer lineId, Integer companyid, Integer sopTag) {
        return devProductListMapper.selectNotConfigByLineId2(lineId,companyid,sopTag);
    }


    /**
     * 查询公司所有的产品信息
     * @return 结果
     */
    @Override
    public List<DevProductList> selectProductAll() {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return devProductListMapper.selectProductAll(user.getCompanyId());
    }

    /**
     * 保存MES规则配置
     * @param id 对应产品/半成品 id
     * @param ruleId 规则id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMesRuleConfig(int id, int ruleId){
        return devProductListMapper.saveMesRuleConfig(id,ruleId);
    }

    /**
     * 取消产品/半成品 mes 规则
     * @param id 产品/半成品id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancel(int id) {
        return devProductListMapper.saveMesRuleConfig(id,0);
    }



    /**
     * 查看mes配置规则明细
     * @param productList 产品信息
     * @return 结果
     */
    @Override
    public List<DevProductList> selectMesCfList(DevProductList productList) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        productList.setCompanyId(user.getCompanyId());
        return devProductListMapper.selectMesCfList(productList);
    }

    /**
     * 通过标记查询所有的成品或者半成品
     * @param sign 成品0，半成品1
     * @return 结果
     */
    @Override
    public List<DevProductList> selectProAllBySign(Integer sign) {
        return devProductListMapper.selectProAllBySign(sign);
    }

    /**
     * 根据公司id和产品编号查询对应的产品信息
     * @param companyId 公司id
     * @param code 产品编号
     * @return 结果
     */
    @Override
    public DevProductList selectProductByCompanyIdAndCode(int companyId, String code) {
        return devProductListMapper.checkProductCodeUnique(code,companyId);
    }
    /**
     * 根据产品/半成品id查询对应配置的规则信息
     * @param id 产品/半成品
     * @return
     */
    @Override
    public MesBatchRule selectMesBatchRuleByPbId(int id) {
        //查询对应的产品/半成品是否存在
        DevProductList devProductList = devProductListMapper.selectDevProductListById(id);
        if(devProductList != null && devProductList.getRuleId() > 0){
            return mesBatchRuleMapper.selectMesBatchRuleById(devProductList.getRuleId());
        }
        return null;
    }

    @Override
    public List<Product> appSearchProList(Product product) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        product.setCompanyId(user.getCompanyId());
        return devProductListMapper.appSearchProList(product);
    }

}
