package com.ryoua.a.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProcessLoad {
    private String mid;

    private Integer pid;
    private String name;

    private Double cpuLoad;
    private Double memoryLoad;
    private String memoryUnit;

    private String updateTime;
    private Long updateTimeMills;
}
