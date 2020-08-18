package com.ryoua.system.model;

import lombok.Data;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/18
 **/
@Data
@ToString
public class Traffic {
    private String mid;

    private String networkInterfaceName;
    private String ipv4;
    private String ipv6;

    private Long sendPackets;
    private Long receivePackets;

    private Long receiveSpeed;
    private Long sendSpeed;

    private String receiveSpeedStr;
    private String sendSpeedStr;

    private String createTime;
    private Long createTimeMills;
}