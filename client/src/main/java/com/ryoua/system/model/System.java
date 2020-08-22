package com.ryoua.system.model;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/19
 **/
@Data
@ToString
public class System {
    private String mid;

    private String ip;
    private String host;

    private String osName;

    private String networkDetail;

    private Integer cpuCores;
    private String cpuDetail;

    private Long memorySize;
    private String memorySizeStr;

    private String createTime;
    private Long createTimeMills;
}
