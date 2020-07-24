package com.ryoua.acquire;

import com.ryoua.model.DiskInfo;
import com.ryoua.model.ResourceInfo;
import com.ryoua.utils.FormatUtil;
import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;


/**
 * 获取磁盘, 内存, CPU的使用率
 * * @Author: RyouA
 * * @Date: 2020/7/3
 **/
@Service
public class BaseResourceInfoAcquire {
    private static final int CPUTIME = 500;

    private static final int PERCENT = 100;

    private static final int FAULTLENGTH = 10;

    private static final OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    
    public static long getPhysicalMemoryKB() {
        return osmxb.getTotalPhysicalMemorySize() / 1024;
    }

    public static long getPhysicalMemoryMB() {
        return getPhysicalMemoryKB() / 1024;
    }

    public static long getPhysicalMemoryGB() {
        return getPhysicalMemoryMB() / 1024;
    }

    public static long getSwapMemoryKB() {
        return osmxb.getTotalSwapSpaceSize() / 1024;
    }

    public static long getSwapMemoryMB() {
        return getSwapMemoryKB() / 1024;
    }

    public static long getSwapMemoryGB() {
        return getSwapMemoryMB() / 1024;
    }

    public static long getFreeMemoryKB() {
        return osmxb.getFreePhysicalMemorySize() / 1024;
    }

    public static long getFreeMemoryMB() {
        return getFreeMemoryKB() / 1024;
    }

    public static long getFreeMemoryGB() {
        return getFreeMemoryMB() / 1024;
    }

    public static long getUseMemoryKB() {
        return getPhysicalMemoryKB() - osmxb.getFreePhysicalMemorySize() / 1024;
    }

    public static long getUseMemoryMB() {
        return getUseMemoryKB() / 1024;
    }

    public static long getUseMemoryGB() {
        return getUseMemoryMB() / 1024;
    }

    public static String getUseMemoryPercent() {
        return String.valueOf(Double.parseDouble(FormatUtil.DoubleSaveOnePoint((getUseMemoryKB() * 1.0 / getPhysicalMemoryKB()) * 100)));
    }

    /**
     * 获取文件系统使用率
     */
    public static ResourceInfo getDiskUsage(ResourceInfo resourceInfo) {
        File[] roots = File.listRoots();//获取磁盘分区列表
        List<DiskInfo> diskInfos = new ArrayList<>();

        for (File file : roots) {
            DiskInfo diskInfo = new DiskInfo();
            diskInfo.setDiskCapacity(file.getTotalSpace() / 1024 / 1024 / 1024 + "GB");
            diskInfo.setDiskPercentage((file.getUsableSpace() * 1.0 / file.getTotalSpace()));
            diskInfo.setMemoryUsage(file.getUsableSpace() / 1024 / 1024 / 1024 + "GB");
            diskInfos.add(diskInfo);
        }

        resourceInfo.setDiskInfos(diskInfos);
        return resourceInfo;
    }

    public static int getCpuCores() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        return processor.getLogicalProcessorCount();
//        processor.getSystemCpuLoad();

    }

    public static double getCpuLoadAverage() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        return processor.getSystemLoadAverage();
    }

}
