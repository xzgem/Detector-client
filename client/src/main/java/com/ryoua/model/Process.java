package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Process {
    private String mid;
    private Integer pid;
    private String name;
    private Double cpuLoad;
    private Double memoryLoad;
    private String memoryUnit;
}
