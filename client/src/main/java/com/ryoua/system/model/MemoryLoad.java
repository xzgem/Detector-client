package com.ryoua.system.model;

import lombok.Data;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/18
 **/
@Data
@ToString
public class MemoryLoad {
    private String mid;

    private Long memorySize;
    private Long memoryUse;
    private Long memoryLess;
    private Double memoryUsage;

    private String memorySizeStr;
    private String memoryUseStr;
    private String memoryLessStr;
    private String memoryUsageStr;

    private String createTime;
    private Long createTimeMills;
}
