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
public class Disk {
    private String mid;

    private String model;
    private String fileSystem;
    private Long size;
    private String sizeStr;
    private Long available;
    private String availableStr;
    private Double use;
    private String useStr;
    private String mounted;

    private String createTime;
    private Long createTimeMills;
}
