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
    private long memoryAll;
    private long memoryUse;
    private long memorySwapAll;
    private long memorySwapUse;

    private long cpuLoad;
    private long cpuLoadAvg;

}
