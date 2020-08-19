package com.ryoua.system.service;

import com.ryoua.system.config.Constants;
import com.ryoua.system.model.System;
import com.ryoua.system.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.NetworkIF;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/19
 **/
@Service
public class SystemService extends BaseService{
    @Autowired
    private LoadService loadService;

    public static String getOsName() {
        return os.toString();
    }

    public static String getIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }

    public static int getCpuCores() {
        oshi.SystemInfo systemInfo = new oshi.SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        return processor.getLogicalProcessorCount();
    }

    public static String getHost() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostName();
    }

    public static String getCpuDetail() {
        CentralProcessor processor = hal.getProcessor();
        return processor +
                " " + processor.getPhysicalPackageCount() + " physical CPU package(s)" +
                " " + processor.getPhysicalProcessorCount() + " physical CPU core(s)" +
                " " + processor.getLogicalProcessorCount() + " logical CPU(s)";
    }

    public static String getNetworkDetail() {
        NetworkIF[] networkIFs = hal.getNetworkIFs();
        StringBuilder stringBuilder = new StringBuilder();
        for (NetworkIF net : networkIFs) {
            stringBuilder.append("Name: ").append(net.getName()).append("(").append(net.getDisplayName()).append(")").append("\n");
            stringBuilder.append("MAC地址: ").append(net.getMacaddr()).append("\n");
            stringBuilder.append("MTU: ").append(net.getMTU()).append(" 速率: ").append(FormatUtil.formatValue(net.getSpeed(), "bps")).append("\n");
            stringBuilder.append("IPv4: ").append(Arrays.toString(net.getIPv4addr())).append("\n");
            stringBuilder.append("IPv6: ").append(Arrays.toString(net.getIPv6addr())).append("\n");
            return stringBuilder.toString();
        }
        return stringBuilder.toString();
    }

//    private static void printProcesses(OperatingSystem os, GlobalMemory memory) {
//        System.out.println("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
//        // Sort by highest CPU
//        List<OSProcess> procs = Arrays.asList(os.getProcesses(10, OperatingSystem.ProcessSort.CPU));
//
//        System.out.println("   PID  %CPU %MEM Name");
//        for (int i = 0; i < procs.size(); i++) {
//            OSProcess p = procs.get(i);
//            System.out.format(" %5d %5.1f %4.1f %s%n", p.getProcessID(),
//                    100d * (p.getKernelTime()) / p.getUpTime(),
//                    100d * p.getResidentSetSize() / memory.getTotal(), p.getName());
//        }
//    }

    public System getSystem() throws UnknownHostException {
        System system = new System();

        system.setMid(Constants.mid);
        system.setIp(getIp());
        system.setHost(getHost());
        system.setOsName(getOsName());
        system.setNetworkDetail(getNetworkDetail());
        system.setCpuCores(getCpuCores());
        system.setCpuDetail(getCpuDetail());
        system.setMemorySize(loadService.getMemoryLoad().getMemorySize());
        system.setMemorySizeStr(loadService.getMemoryLoad().getMemorySizeStr());
        system.setCreateTime(TimeUtil.getNowTime());
        system.setCreateTimeMills(TimeUtil.getNowTimeMills());

        return system;
    }
}
