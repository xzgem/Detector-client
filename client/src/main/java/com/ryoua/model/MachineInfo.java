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
public class MachineInfo {
    private String oid;
    private String ip;
    private String host;
    private String mac;
    private String networkDetail;

    private String osName;
    private String osDetail;

    private long cpuCores;
    private String cpuDetail;

    private long memory;
    private String memoryUnit;
    private String memoryDetail;

    private long disk;
    private String diskDetail;
    private String diskUnit;

    private String fileDetail;
    private String updateTime;
    private int autoRegister;
}
