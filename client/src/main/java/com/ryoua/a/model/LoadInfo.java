package com.ryoua.a.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoadInfo {
    private String mid;

    private CpuLoad cpuLoad;
    private MemoryLoad memoryLoad;
    private List<ProcessLoad> processLoads;
    private DockerInfo dockerInfo;
    private Traffic traffic;

    private String updateTime;
    private Long updateTimeMills;
}
