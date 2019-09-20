package com.ruoyi.project.app.service;

import com.ruoyi.project.production.productionLine.domain.ProductionLine;

import java.util.List;

/**
 * 查询工位操作
 */
public interface ILineService {
    /**
     * 查询所以产线，及其工位信息
     * @return
     */
    List<ProductionLine> selectAllLine();
}
