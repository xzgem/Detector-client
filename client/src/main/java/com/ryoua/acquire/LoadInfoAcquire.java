package com.ryoua.acquire;

import com.ryoua.utils.FormatUtil;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.util.*;


/**
 * 获取磁盘, 内存, CPU的使用率
 * * @Author: RyouA
 * * @Date: 2020/7/3
 **/
@Service
public class LoadInfoAcquire extends BaseAcquire {

    public static double getSwapMemoryUse() {
        GlobalMemory memory = hal.getMemory();
        return Double.parseDouble(oshi.util.FormatUtil.formatBytes(memory.getSwapUsed()).split(" ")[0]);
    }

    public static double getSwapMemory() {
        GlobalMemory memory = hal.getMemory();
        return Double.parseDouble(oshi.util.FormatUtil.formatBytes(memory.getSwapTotal()).split(" ")[0]);
    }

    public static double getMemoryAll() {
        GlobalMemory memory = hal.getMemory();
        return Double.parseDouble(oshi.util.FormatUtil.formatBytes(memory.getTotal()).split(" ")[0]);
    }

    public static String getMemoryAllUnit() {
        GlobalMemory memory = hal.getMemory();
        return oshi.util.FormatUtil.formatBytes(memory.getTotal()).split(" ")[1];
    }

    public static String getMemorySwapUnit() {
        GlobalMemory memory = hal.getMemory();
        return oshi.util.FormatUtil.formatBytes(memory.getSwapTotal()).split(" ")[1];
    }

    public static double getMemoryAllUse() {
        GlobalMemory memory = hal.getMemory();
        return Double.parseDouble(oshi.util.FormatUtil.formatBytes(memory.getAvailable()).split(" ")[0]);
    }

    private static void getCpuUse() {
        CentralProcessor processor = hal.getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();

        System.out.println("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();
        System.out.println("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

        System.out.format(
                "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%%n",
                100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
                100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu);
        System.out.format("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadBetweenTicks() * 100);
        System.out.format("CPU load: %.1f%% (OS MXBean)%n", processor.getSystemCpuLoad() * 100);
        double[] loadAverage = processor.getSystemLoadAverage(3);
        System.out.println("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
                + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
                + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
        // per core CPU
        StringBuilder procCpu = new StringBuilder("CPU load per processor:");
        double[] load = processor.getProcessorCpuLoadBetweenTicks();
        for (double avg : load) {
            procCpu.append(String.format(" %.1f%%", avg * 100));
        }
        System.out.println(procCpu.toString());
    }

    public static double getCpuLoad() {
        CentralProcessor processor = hal.getProcessor();
        Util.sleep(1000);
        return Double.parseDouble(FormatUtil.DoubleSaveOnePoint(processor.getSystemCpuLoadBetweenTicks() * 100));
    }

    private static void getTop5Processes() {
        GlobalMemory memory = hal.getMemory();
        // Sort by highest CPU
        List<OSProcess> procs = Arrays.asList(os.getProcesses(5, OperatingSystem.ProcessSort.CPU));
        System.out.println("   PID  %CPU %MEM       VSZ       RSS Name");
        for (int i = 0; i < procs.size() && i < 5; i++) {
            OSProcess p = procs.get(i);
            System.out.format(" %5d %5.1f %4.1f %9s %9s %s%n", p.getProcessID(),
                    100d * (p.getKernelTime() ) / p.getUpTime(),
                    100d * p.getResidentSetSize() / memory.getTotal(),
                    oshi.util.FormatUtil.formatBytes(p.getVirtualSize()),
                    oshi.util.FormatUtil.formatBytes(p.getResidentSetSize()),
                    p.getName());
        }
    }

    public static void main(String[] args) {
        getTop5Processes();
    }
}
