package com.ryoua.service;

import com.ryoua.config.Constants;
import com.ryoua.acquire.SystemInfoAcquire;
import com.ryoua.acquire.LoadInfoAcquire;
import com.ryoua.model.SystemInfo;
import com.ryoua.utils.TimeUtil;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/25
 **/
@Service
public class SystemInfoService {
    public SystemInfo getSystemInfo() throws UnknownHostException {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setMid(Constants.mid);
        systemInfo.setIp(SystemInfoAcquire.getIp());
        systemInfo.setHost(SystemInfoAcquire.getHost());
        systemInfo.setCpuCores(SystemInfoAcquire.getCpuCores());
        systemInfo.setCpuDetail(SystemInfoAcquire.getCpuDetail());
        systemInfo.setUpdateTime(TimeUtil.getNowTime());
        systemInfo.setOsName(SystemInfoAcquire.getOsName());
        systemInfo.setMemory(LoadInfoAcquire.getMemoryAll());
        systemInfo.setUpdateTimeMills(System.currentTimeMillis());
        systemInfo.setMemoryUnit(LoadInfoAcquire.getMemoryAllUnit());
//        systemInfo.setDiskDetail(SystemInfoAcquire.getDisks());
//        systemInfo.setFileDetail(SystemInfoAcquire.getFileSystem());
        return systemInfo;
    }

    public static void main(String[] args) throws UnknownHostException {
        SystemInfoService systemInfoService = new SystemInfoService();
        SystemInfo systemInfo = systemInfoService.getSystemInfo();
        System.out.println(systemInfo);
    }
}
