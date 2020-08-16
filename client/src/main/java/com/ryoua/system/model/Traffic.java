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
public class Traffic {
    private String mid;

    private String networkInterfaceName;
    private String ipv4;
    private String ipv6;

    private Long sendPackets;
    private Long receivePackets;

    private Long receiveSpeed;
    private String receiveSpeedStr;
    private Long sendSpeed;
    private String sendSpeedStr;

    private String createTime;
    private Long createTimeMills;
}
