package com.ryoua.system.controller;

import com.ryoua.system.model.Traffic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/16
 **/
@RestController
@Slf4j
public class TrafficController extends BaseController {
    public void sendTrafficInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            List<Traffic> traffic = trafficService.getTraffic();
            HttpEntity<List<Traffic>> request = new HttpEntity<>(traffic, headers);
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/traffic/register/" + autoRegister, request, String.class);
            log.info(result);
        } catch (Exception e) {
            log.info("发送信息失败");
        }
    }
}
