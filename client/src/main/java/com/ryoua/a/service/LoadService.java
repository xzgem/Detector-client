package com.ryoua.a.service;

import com.ryoua.system.config.Constants;
import com.ryoua.a.model.CpuLoad;
import com.ryoua.a.model.LoadInfo;
import com.ryoua.a.model.MemoryLoad;
import com.ryoua.system.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Service
@Slf4j
public class LoadService {
    public CpuLoad getCpuLoad() {
        SystemInfo systemInfo = new SystemInfo();
        CpuLoad cpuLoad = new CpuLoad();
        try {
            CentralProcessor processor = systemInfo.getHardware().getProcessor();
            long[] prevTicks = processor.getSystemCpuLoadTicks();
            // 睡眠1s
            TimeUnit.SECONDS.sleep(1);
            long[] ticks = processor.getSystemCpuLoadTicks();
            long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
            long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
            long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
            long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
            long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
            long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
            long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
            long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
            long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
            cpuLoad.setCpuCores(processor.getLogicalProcessorCount());
            cpuLoad.setCpuSystemUse(new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
            cpuLoad.setCpuUserUse(new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
            cpuLoad.setCpuUsage(new DecimalFormat("#.##%").format(1.0 - (idle * 1.0 / totalCpu)));
            cpuLoad.setMid(Constants.mid);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        return cpuLoad;
    }

    public MemoryLoad getMemoryLoad() {
        SystemInfo systemInfo = new SystemInfo();
        MemoryLoad memoryLoad = new MemoryLoad();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        // 总内存
        long totalByte = memory.getTotal();
        // 剩余
        long acaliableByte = memory.getAvailable();
        memoryLoad.setMemorySize(formatByte(totalByte));
        memoryLoad.setMemoryUse(formatByte(totalByte-acaliableByte));
        memoryLoad.setMemoryUsage(new DecimalFormat("#.##%").format((totalByte-acaliableByte)*1.0/totalByte));
        memoryLoad.setMemoryLess(formatByte(acaliableByte));
        return memoryLoad;
    }

    public LoadInfo getLoadInfo() {
        LoadInfo loadInfo = new LoadInfo();
        loadInfo.setCpuLoad(getCpuLoad());
        loadInfo.setMemoryLoad(getMemoryLoad());
        loadInfo.setMid(Constants.mid);
        loadInfo.setUpdateTime(TimeUtil.getNowTime());
        loadInfo.setUpdateTimeMills(TimeUtil.getNowTimeMills());
        return loadInfo;
    }

    public void setSysInfo(){
        System.out.println("----------------操作系统信息----------------");
        Properties props = System.getProperties();
        //系统名称
        String osName = props.getProperty("os.name");
        //架构名称
        String osArch = props.getProperty("os.arch");
        System.out.println("操作系统名 = " + osName);
        System.out.println("系统架构 = " + osArch);
    }

    // TODO: 暂时不用, 后面考虑JMX
    public void setJvmInfo(){
        System.out.println("----------------jvm信息----------------");
        Properties props = System.getProperties();
        Runtime runtime = Runtime.getRuntime();
        //jvm总内存
        long jvmTotalMemoryByte = runtime.totalMemory();
        //jvm最大可申请
        long jvmMaxMoryByte = runtime.maxMemory();
        //空闲空间
        long freeMemoryByte = runtime.freeMemory();
        //jdk版本
        String jdkVersion = props.getProperty("java.version");
        //jdk路径
        String jdkHome = props.getProperty("java.home");
        System.out.println("jvm内存总量 = " + formatByte(jvmTotalMemoryByte));
        System.out.println("jvm已使用内存 = " + formatByte(jvmTotalMemoryByte-freeMemoryByte));
        System.out.println("jvm剩余内存 = " + formatByte(freeMemoryByte));
        System.out.println("jvm内存使用率 = " + new DecimalFormat("#.##%").format((jvmTotalMemoryByte-freeMemoryByte)*1.0/jvmTotalMemoryByte));
        System.out.println("java版本 = " + jdkVersion);
        //System.out.println("jdkHome = " + jdkHome);
    }

    public static String formatByte(long byteNumber){
        //换算单位
        double FORMAT = 1024.0;
        double kbNumber = byteNumber/FORMAT;
        if(kbNumber<FORMAT){
            return new DecimalFormat("#.##KB").format(kbNumber);
        }
        double mbNumber = kbNumber/FORMAT;
        if(mbNumber<FORMAT){
            return new DecimalFormat("#.##MB").format(mbNumber);
        }
        double gbNumber = mbNumber/FORMAT;
        if(gbNumber<FORMAT){
            return new DecimalFormat("#.##GB").format(gbNumber);
        }
        double tbNumber = gbNumber/FORMAT;
        return new DecimalFormat("#.##TB").format(tbNumber);
    }
}
