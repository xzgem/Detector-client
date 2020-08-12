package com.ryoua.system.model;

import com.ryoua.model.DiskInfo;
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

    private Integer cpuCores;
    private String cpuDetail;

    private Double memorySize;
    private String memoryUnit;

    private List<DiskInfo> diskInfos;

    private String updateTime;
    private Long updateTimeMills;
}
