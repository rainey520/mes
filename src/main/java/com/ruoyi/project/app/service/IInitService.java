package com.ruoyi.project.app.service;

import com.ruoyi.project.app.domain.Index;
import com.ruoyi.project.app.domain.Init;
import com.ruoyi.project.system.menu.domain.Menu;

import java.util.List;

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
}
