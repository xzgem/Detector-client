package com.ryoua.controller;

import com.ryoua.acquire.BaseSystemInfoAcquire;
import com.ryoua.model.Result;
import com.ryoua.model.SystemInfo;
import com.ryoua.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Date;

/**
 * @Author: RyouA
 * @Date: 2020/5/12 - 1:51 下午
 **/
@Service
@Slf4j
public class SystemController {
    @Autowired
    private BaseSystemInfoAcquire systemInfoAcquire;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/SystemInfo")
    public void SystemInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            SystemInfo systemInfo = systemInfoAcquire.getSystemInfo();
            HttpEntity<SystemInfo> request = new HttpEntity<>(systemInfo, headers);
            systemInfo.setUpdateTime(TimeUtil.getNowTime());
            String result = this.restTemplate.postForObject("http://127.0.0.1:8080/systemInfo", request, String.class);
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送系统信息失败");
        }
    }
}
