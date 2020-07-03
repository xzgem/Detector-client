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
    /**
     * Host地址
     */
    private String host;
    /**
     * IP地址
     */
    private String ip;
    /**
     * MAC地址
     */
    private String mac;
    /**
     * JAVA版本
     */
    private String javaVersion;
    /**
     * 操作系统名称
     */
    private String osName;
    /**
     * 操作系统版本
     */
    private String osVersion;
    /**
     * 用户名
     */
    private String username;
    /**
     * 其他信息
     */
    private Object other;
    /**
     * 更新时间
     */
    private String updateTime;
}
