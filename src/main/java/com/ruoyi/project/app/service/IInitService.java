package com.ruoyi.project.app.service;

import com.ruoyi.project.app.domain.Index;
import com.ruoyi.project.app.domain.Init;
import com.ruoyi.project.system.menu.domain.Menu;

import java.util.List;
import java.util.Map;

public interface IInitService {
    /**
     * 获取当天工单、菜单权限、公司信息
     * @return
     */
    Init initIndex(Index index);

    /**
     * 获取菜单
     * @param index
     * @return
     */
    List<Menu> initMenu(Index index);

    /**
     * 获取工单号
     * @return 工单号
     */
    String getWorkCode();

    /**
     * 录入mes信息
     * @param index 上传信息
     * @return 结果
     */
    Map<String, Object> saveInput(Index index);

    /**
     * 获取工单相关mes退货数据
     * @return 结果
     */
    Map<String, Object> getMesBatch();

    /**
     * 获取产品相关mes退货数据
     * @return 结果
     */
    Map<String, Object> getProBatch();
}
