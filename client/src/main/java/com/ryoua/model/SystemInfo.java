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
    private String ip;
    private String mac;
    private String host;

    private String osName;
    private String osDetail;

    private String cpuCores;
    private String cpuCoresDetail;

    private String memory;

    private String updateTime;
}
