package com.ryoua.system.model;

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
public class CpuLoad {
    private String mid;

    private Integer cpuCores;
    private String cpuSystemUse;
    private String cpuUserUse;
    private String cpuUsage;

    private String updateTime;
    private Long updateTimeMills;
}
