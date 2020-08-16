package com.ryoua.system.model;

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
public class SystemInfo {
    private String mid;

    private String ip;
    private String host;

    private String osName;

    private String networkDetail;

    private Integer cpuCores;
    private String cpuDetail;

    private String memorySize;

    private String fileSystemDetail;
    private List<Disk> diskInfos;

    private String updateTime;
    private Long updateTimeMills;
}
