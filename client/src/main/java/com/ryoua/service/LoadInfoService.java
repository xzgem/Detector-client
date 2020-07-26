package com.ryoua.service;

import com.ryoua.acquire.LoadInfoAcquire;
import com.ryoua.model.LoadInfo;
import org.springframework.stereotype.Service;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@Service
public class LoadInfoService {
    /**
     * 获取负载信息
     */
    public LoadInfo getLoadInfo() {
        LoadInfo loadInfo = new LoadInfo();
        // 获取Cpu负载
        loadInfo.setCpuLoad(LoadInfoAcquire.getCpuLoad());
        // 获取全部内存
        loadInfo.setMemoryAll(LoadInfoAcquire.getMemoryAll());
        // 获取已使用内存
        loadInfo.setMemoryUse(LoadInfoAcquire.getMemoryAllUse());
        // 获取全部的交换内存
        loadInfo.setMemorySwapAll(LoadInfoAcquire.getSwapMemory());
        // 获取已使用的交换内存
        loadInfo.setMemorySwapUse(LoadInfoAcquire.getSwapMemoryUse());
        // 设置内存的单位
        loadInfo.setMemoryUnit(LoadInfoAcquire.getMemoryUnit());
        return loadInfo;
    }
}
