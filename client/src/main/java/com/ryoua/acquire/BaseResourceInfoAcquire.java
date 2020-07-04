package com.ryoua.acquire;

import com.ryoua.model.DiskInfo;
import com.ryoua.model.ResourceInfo;
import com.ryoua.utils.FormatUtil;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 获取磁盘, 内存, CPU的使用率
 * * @Author: RyouA
 * * @Date: 2020/7/3
 **/
@Service
public class BaseResourceInfoAcquire implements ResourceInfoAcquire {
    private static final int CPUTIME = 500;

    private static final int PERCENT = 100;

    private static final int FAULTLENGTH = 10;

    /**
     * 获取内存使用率
     */
    public void getMemoryUseAge(ResourceInfo resourceInfo) {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存 + 虚拟内存
        long totalvirtualMemory = osmxb.getTotalPhysicalMemorySize() + osmxb.getTotalSwapSpaceSize();
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

        totalvirtualMemory /= 1024.0;
        freePhysicalMemorySize /= 1024.0;
        // 占用
        double compare = (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
        // 设置内存已使用和总大小
        if (totalvirtualMemory < 1024) {
            resourceInfo.setMemoryCapacity(totalvirtualMemory + "KB");
            resourceInfo.setMemoryUsage((totalvirtualMemory - freePhysicalMemorySize) + "KB");
        } else if (totalvirtualMemory < 1024 * 1024) {
            resourceInfo.setMemoryCapacity(totalvirtualMemory / 1024.0 + "MB");
            resourceInfo.setMemoryUsage(FormatUtil.DoubleSaveOnePoint((totalvirtualMemory - freePhysicalMemorySize) / 1024.0) + "MB");
        } else if (totalvirtualMemory < 1024 * 1024 * 1024) {
            resourceInfo.setMemoryCapacity(totalvirtualMemory / 1024.0 / 1024.0 + "GB");
            resourceInfo.setMemoryUsage(FormatUtil.DoubleSaveOnePoint((totalvirtualMemory - freePhysicalMemorySize) / 1024.0 / 1024.0) + "GB");
        }
        // 设置使用率
        resourceInfo.setMemoryPercentage(Double.valueOf(FormatUtil.DoubleSaveOnePoint(compare)));
    }

    /**
     * 获取文件系统使用率
     */
    public ResourceInfo getDiskUsage(ResourceInfo resourceInfo) {
        File[] roots = File.listRoots();//获取磁盘分区列表
        List<DiskInfo> diskInfos = new ArrayList<>();

        for (File file : roots) {
            DiskInfo diskInfo = new DiskInfo();
            diskInfo.setDiskCapacity(file.getTotalSpace() / 1024 / 1024 / 1024 + "GB");
            System.out.println(file.getUsableSpace() / file.getTotalSpace());
            diskInfo.setDiskPercentage((file.getUsableSpace() * 1.0 / file.getTotalSpace()));
            diskInfo.setMemoryUsage(file.getUsableSpace() / 1024 / 1024 / 1024 + "GB");
            diskInfos.add(diskInfo);
        }

        resourceInfo.setDiskInfos(diskInfos);
        return resourceInfo;
    }

    /**
     * 获取cpu占用
     * TODO: 好像有点问题, 有时间在看
     * @param resourceInfo
     * @return
     */
    public ResourceInfo getCpuUsage(ResourceInfo resourceInfo) {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        resourceInfo.setCpuCores(processor.getLogicalProcessorCount());
        System.out.println(processor.getSystemLoadAverage());
        System.out.println(processor.getSystemCpuLoad());
        resourceInfo.setCpuPercentage(processor.getSystemCpuLoad());
        return resourceInfo;
    }

    @Override
    public ResourceInfo getResourceInfo() {
        ResourceInfo resourceInfo = new ResourceInfo();
        getMemoryUseAge(resourceInfo);
        getDiskUsage(resourceInfo);
        getCpuUsage(resourceInfo);
        return resourceInfo;
    }
}
