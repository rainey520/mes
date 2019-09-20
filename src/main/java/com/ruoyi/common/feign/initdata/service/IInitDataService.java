package com.ruoyi.common.feign.initdata.service;

import com.ruoyi.common.feign.initdata.domain.InitData;

/**
 * 初始化数据
 */
public interface IInitDataService {
    /**
     * 初始化数据
     * @param initData 初始化数据
     * @return
     * @throws Exception
     */
    int initData(InitData initData) throws Exception ;
}
