package com.ryoua.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

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

    private Double cpuCores;
    private String cpuDetail;

    private Double memory;
    private String memoryUnit;

    private List<DiskInfo> diskInfos;

    private String fileDetail;
    private String updateTime;
    private Long updateTimeMills;

    private Integer autoRegister;
}
