package com.ryoua.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: RyouA
 * @Date: 2020/5/12 - 1:41 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemInfo {
    private String id;
    private String ip;
    private String host;

    private String osName;

    private double cpuCores;
    private String cpuDetail;

    private double memory;
    private String memoryUnit;

    private double disk;
    private String diskDetail;
    private String diskUnit;

    private String fileDetail;
    private String updateTime;
    private long updateTimeMills;

    private int autoRegister;
}
