package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    private String memoryUsage;
    /**
     * 磁盘使用占比
     */
    private Integer diskPercentage;

    private String diskCapacity;

    private String memoryCapacity;

    private String cpuPercentage;

    private String cpuCores;
}
