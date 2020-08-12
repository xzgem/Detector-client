package com.ryoua.system.service;

import com.ryoua.config.Constants;
import com.ryoua.system.model.SystemInfo;
import com.ryoua.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Properties;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Service
public class SystemService {
    @Autowired
    private LoadService loadService;

    static final oshi.SystemInfo si = new oshi.SystemInfo();

    static final HardwareAbstractionLayer hal = si.getHardware();

    static final OperatingSystem os = si.getOperatingSystem();

    static final Formatter formatter = new Formatter();

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

    public static String getDisks() {
        StringBuilder stringBuilder = new StringBuilder();
        HWDiskStore[] diskStores = hal.getDiskStores();
        for (HWDiskStore disk : diskStores) {
            stringBuilder.append(disk.getName()).append(": 磁盘名:").append(disk.getModel()).append(", 容量:").append(FormatUtil.formatBytesDecimal(disk.getSize()));
            stringBuilder.append("\n");
            HWPartition[] partitions = disk.getPartitions();
        }
        return stringBuilder.toString();
    }

    public static String getFileSystemDetail() {
        StringBuilder stringBuilder = new StringBuilder();

        FileSystem fileSystem = os.getFileSystem();

        OSFileStore[] fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long usable = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            stringBuilder.append(formatter.format(
                    " %s (%s) [%s] %s of %s free (%.1f%%) is %s "
                            + (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s")
                            + " and is mounted at %s%n",
                    fs.getName(), fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
                    fs.getVolume(), fs.getLogicalVolume(), fs.getMount()));
        }
        return stringBuilder.toString();
    }

    public static String traffic() {
        NetworkIF[] networkIFs = hal.getNetworkIFs();
        StringBuilder stringBuilder = new StringBuilder();
        for (NetworkIF net : networkIFs) {
            boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
                    || net.getPacketsSent() > 0;

            stringBuilder.append("流量: 发送 " + (hasData ? net.getPacketsRecv() + "_packets " : "?") +
                    (hasData ? oshi.util.FormatUtil.formatBytes(net.getBytesRecv()) : "?") +
                    (hasData ? " (" + net.getInErrors() + " err) " : "") +
                    "    接收 " +
                    (hasData ? net.getPacketsSent() + "_packets " : "?") +
                    (hasData ? oshi.util.FormatUtil.formatBytes(net.getBytesSent()) : "?") +
                    (hasData ? " (" + net.getOutErrors() + " err)" : ""));
        }
        return stringBuilder.toString();
    }

    public SystemInfo getSystemInfo() throws UnknownHostException {
        SystemInfo systemInfo = new SystemInfo();

        systemInfo.setMid(Constants.mid);

        systemInfo.setIp(getIp());
        systemInfo.setHost(getHost());

        systemInfo.setNetworkDetail(getNetworkDetail());
        systemInfo.setOsName(getOsName());

        systemInfo.setCpuCores(getCpuCores());
        systemInfo.setCpuDetail(getCpuDetail());

        systemInfo.setMemorySize(loadService.getMemoryLoad().getMemorySize());

        systemInfo.setFileSystemDetail(getFileSystemDetail());

        systemInfo.setUpdateTime(TimeUtil.getNowTime());
        systemInfo.setUpdateTimeMills(System.currentTimeMillis());
        return systemInfo;
    }
}
