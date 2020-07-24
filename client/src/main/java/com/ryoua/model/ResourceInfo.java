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
    private Double memoryPercentage;
    private String memoryUsage;
    private String memoryCapacity;
    private List<DiskInfo> diskInfos;
    private Double cpuPercentage;
    private Integer cpuCores;
    private String updateTime;
}
