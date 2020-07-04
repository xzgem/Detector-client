package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/3
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResourceInfo {
    /**
     * 内存使用占比
     */
    private Double memoryPercentage;

    /**
     * 内存已占用
     */
    private String memoryUsage;

    /**
     * 内存总容量
     */
    private String memoryCapacity;

    /**
     * 磁盘使用占比
     */
    private List<DiskInfo> diskInfos;

    /**
     * Cpu占用
     */
    private Double cpuPercentage;

    /**
     * Cpu核心数
     */
    private Integer cpuCores;

    /**
     * 更新时间
     */
    private String updateTime;
}
