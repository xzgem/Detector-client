package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 负载信息
 * * @Author: RyouA
 * * @Date: 2020/7/3
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoadInfo {
    private String mid;

    private String memoryUnit;
    private double memoryAll;
    private double memoryUse;

    private String memorySwapUnit;
    private double memorySwapAll;
    private double memorySwapUse;

    private double cpuLoad;
    private String updateTime;
    private long updateTimeMills;
}
