package com.ryoua.a.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiskIO {
    private String mid;

    private String device;
    private Long receiveSpeed;
    private Long writeSpeed;
    private Double svctm;
    private String util;

    private String createTime;
    private Long createTimeMills;
}
