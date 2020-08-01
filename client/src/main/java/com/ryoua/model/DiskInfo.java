package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/4
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
}
