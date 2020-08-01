package com.ryoua.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrafficInfo {
    private String mid;
    private Double packets;
    private Double speed;
    private String speedUnit;
    private Double traffic;
    private String trafficUnit;
    private String updateTime;
    private Long updateTimeMills;
}
