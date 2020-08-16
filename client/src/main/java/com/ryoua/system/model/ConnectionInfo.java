package com.ryoua.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConnectionInfo {
    private String mid;

    private String ip;
    private Integer port;

    private Integer autoRegister;

    private String updateTime;
    private Long updateTimeMills;
}
