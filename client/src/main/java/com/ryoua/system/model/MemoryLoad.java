package com.ryoua.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemoryLoad {
    private String mid;

    private String memorySize;
    private String memoryUse;
    private String memoryLess;
    private String memoryUsage;

    private String updateTime;
    private Long updateTimeMills;
}
