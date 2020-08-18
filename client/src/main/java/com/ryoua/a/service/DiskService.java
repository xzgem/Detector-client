package com.ryoua.a.service;

import com.ryoua.system.config.Constants;
import com.ryoua.a.model.Disk;
import org.springframework.stereotype.Service;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.util.FormatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/16
 **/
@Service
public class DiskService extends BaseService {
    public static List<Disk> getDisk() {
        List<Disk> list = new ArrayList<>();
        HWDiskStore[] diskStores = hal.getDiskStores();
        for (HWDiskStore diskStore : diskStores) {
            Disk disk = new Disk();
            disk.setMid(Constants.mid);
            disk.setModel(diskStore.getModel());
            disk.setSize(diskStore.getSize());
            disk.setSizeStr(FormatUtil.formatBytesDecimal(diskStore.getSize()));
//            disk.setUse();
//            disk.setUseStr();
//            disk.setFileSystem();
//            disk.setAvailable();
//            disk.setCreateTime();
//            disk.setCreateTimeMills();
//            disk.setMounted();

        }
        return list;
    }

    public static void main(String[] args) {
        getDisk();
    }

    public static String getDiskIO() {
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
}
