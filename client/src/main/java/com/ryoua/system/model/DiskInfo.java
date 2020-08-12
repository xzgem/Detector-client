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
public class DiskInfo {
    private String mid;

    private String diskCapacity;
    private String diskUsage;
    private String diskName;

    private String updateTime;
    private Long updateTimeMills;
}
