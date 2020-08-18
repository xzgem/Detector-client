package com.ryoua.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.hardware.*;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Service
public class SystemService extends BaseService {
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



    private static void printProcesses(OperatingSystem os, GlobalMemory memory) {
        System.out.println("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
        // Sort by highest CPU
        List<OSProcess> procs = Arrays.asList(os.getProcesses(10, OperatingSystem.ProcessSort.CPU));

        System.out.println("   PID  %CPU %MEM Name");
        for (int i = 0; i < procs.size(); i++) {
            OSProcess p = procs.get(i);
            System.out.format(" %5d %5.1f %4.1f %s%n", p.getProcessID(),
                    100d * (p.getKernelTime()) / p.getUpTime(),
                    100d * p.getResidentSetSize() / memory.getTotal(), p.getName());
        }
    }



//    public static void main(String[] args) throws InterruptedException {
//        System.out.println(getDisks());
//    }
//
//    public SystemInfo getSystemInfo() throws UnknownHostException {
//        SystemInfo systemInfo = new SystemInfo();
//
//        systemInfo.setMid(Constants.mid);
//
//        systemInfo.setIp(getIp());
//        systemInfo.setHost(getHost());
//
//        systemInfo.setNetworkDetail(getNetworkDetail());
//        systemInfo.setOsName(getOsName());
//
//        systemInfo.setCpuCores(getCpuCores());
//        systemInfo.setCpuDetail(getCpuDetail());
//
//        systemInfo.setMemorySize(loadService.getMemoryLoad().getMemorySize());
//
//        systemInfo.setFileSystemDetail(getFileSystemDetail());
//
//        systemInfo.setUpdateTime(TimeUtil.getNowTime());
//        systemInfo.setUpdateTimeMills(System.currentTimeMillis());
//        return systemInfo;
//    }
}
