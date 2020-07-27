package com.ryoua.service;

import com.ryoua.acquire.MachineInfoAcquire;
import com.ryoua.acquire.LoadInfoAcquire;
import com.ryoua.model.MachineInfo;
import com.ryoua.utils.TimeUtil;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/25
 **/
@Service
public class MachineInfoService {
    public MachineInfo getMachineInfo() throws UnknownHostException {
        MachineInfo machineInfo = new MachineInfo();
        machineInfo.setIp(MachineInfoAcquire.getIp());
        machineInfo.setHost(MachineInfoAcquire.getHost());
        machineInfo.setCpuCores(MachineInfoAcquire.getCpuCores());
        machineInfo.setCpuDetail(MachineInfoAcquire.getCpuDetail());
        machineInfo.setUpdateTime(TimeUtil.getNowTime());
        machineInfo.setOsName(MachineInfoAcquire.getOsName());
        machineInfo.setOsDetail(MachineInfoAcquire.getOSDetail());
        machineInfo.setNetworkDetail(MachineInfoAcquire.getNetworkDetail());
        machineInfo.setMemoryDetail(MachineInfoAcquire.getMemory());
        machineInfo.setMemory((long) LoadInfoAcquire.getPhysicalMemoryGB());
        machineInfo.setDiskDetail(MachineInfoAcquire.getDisks());
        machineInfo.setFileDetail(MachineInfoAcquire.getFileSystem());
        return machineInfo;
    }

    public static void main(String[] args) throws UnknownHostException {
        MachineInfoService machineInfoService = new MachineInfoService();
        MachineInfo machineInfo = machineInfoService.getMachineInfo();
        System.out.println(1);
    }
}
