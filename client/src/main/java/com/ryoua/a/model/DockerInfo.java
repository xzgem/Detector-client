package com.ryoua.a.model;

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
public class DockerInfo {
    private String mid;

    private String dockerName;
    private String dockerId;
    private String dockerCpuUsage;
    private String dockerMemoryUsage;

    private String updateTime;
    private Long updateTimeMills;
}
