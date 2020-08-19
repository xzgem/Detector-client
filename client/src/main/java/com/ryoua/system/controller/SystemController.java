package com.ryoua.system.controller;

import com.ryoua.system.model.Load;
import com.ryoua.system.model.System;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/19
 **/
@RestController
@Slf4j
public class SystemController extends BaseController {
    public void sendLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            System system = systemService.getSystem();
            HttpEntity<System> request = new HttpEntity<>(system, headers);
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/system/register/" + autoRegister, request, String.class);
            log.info(result);
        } catch (Exception e) {
            log.info("机器信息发送失败");
        }
    }
}
