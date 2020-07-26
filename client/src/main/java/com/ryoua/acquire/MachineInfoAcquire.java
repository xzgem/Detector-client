package com.ryoua.acquire;

import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 获取系统的基本信息
 * @Author: RyouA
 * @Date: 2020/5/23 - 7:46 下午
 **/
@Service
public class MachineInfoAcquire {
    private static final oshi.SystemInfo si = new SystemInfo();

    private static final HardwareAbstractionLayer hal = si.getHardware();

    private static final OperatingSystem os = si.getOperatingSystem();

    private static final Formatter formatter = new Formatter();

    private static final Properties properties = System.getProperties();


    public static String getOsName() {
        return os.toString();
    }

    public static String getIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }

    public static String getOid() throws UnknownHostException, SocketException {
        try {
            Process process = Runtime.getRuntime().exec(
                    new String[] { "wmic", "cpu", "get", "ProcessorId" });
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            String serial = sc.next();
            System.out.println(property + ": " + serial);
            return serial;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getCpuCores() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        return processor.getLogicalProcessorCount();
    }

    public static String getHost() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostName();
    }

    public static String getOSDetail() {
        ComputerSystem computerSystem = hal.getComputerSystem();
        return "manufacturer: " + computerSystem.getManufacturer() +
                "model: " + computerSystem.getModel() +
                "serialnumber: " + computerSystem.getSerialNumber();
    }

    public static String getCpuDetail() {
        CentralProcessor processor = hal.getProcessor();
        return processor +
                " " + processor.getPhysicalPackageCount() + " physical CPU package(s)" +
                " " + processor.getPhysicalProcessorCount() + " physical CPU core(s)" +
                " " + processor.getLogicalProcessorCount() + " logical CPU(s)";
    }

    public static String getMemory() {
        return FormatUtil.formatBytes(hal.getMemory().getTotal());
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

    public static String getFileSystem() {
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


}
