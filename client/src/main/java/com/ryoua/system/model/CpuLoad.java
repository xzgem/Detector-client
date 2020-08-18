package com.ryoua.system.model;

import lombok.Data;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/18
 **/
@Data
@ToString
public class CpuLoad {
    private String mid;

    private Integer cpuCores;

    private Double cpuUsage;
    private Double cpuSystemUsage;
    private Double cpuUserUsage;

    private String cpuUsageStr;
    private String cpuSystemUsageStr;
    private String cpuUseUsageStr;

    private String createTime;
    private Long createTimeMills;
}
