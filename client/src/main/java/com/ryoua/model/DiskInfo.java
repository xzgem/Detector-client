package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/4
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiskInfo {
    /**
     * 磁盘使用率
     */
    private Double diskPercentage;

    /**
     * 磁盘大小
     */
    private String diskCapacity;

    /**
     * 磁盘已占用
     */
    private String memoryUsage;
}
