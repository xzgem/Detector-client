package com.ryoua.system.controller;

import com.ryoua.system.model.CpuLoad;
import com.ryoua.system.model.Load;
import com.ryoua.system.model.MemoryLoad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;


/**
 * * @Author: RyouA
 * * @Date: 2020/8/18
 **/
@RestController
@Slf4j
public class LoadController extends BaseController {
    public void sendLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            Load load = loadService.getLoad();
            HttpEntity<Load> request = new HttpEntity<>(load, headers);
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/load/register/" + autoRegister, request, String.class);
            log.info(result);
        } catch (Exception e) {
            log.info("整体负载信息发送失败");
        }
    }

    public void sendMemoryLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            MemoryLoad load = loadService.getMemoryLoad();
            HttpEntity<MemoryLoad> request = new HttpEntity<>(load, headers);
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/memoryLoad/register/" + autoRegister, request, String.class);
            log.info(result);
        } catch (Exception e) {
            log.info("内存负载信息发送失败");
        }
    }

    public void sendCpuLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            CpuLoad load = loadService.getCpuLoad();
            HttpEntity<CpuLoad> request = new HttpEntity<>(load, headers);
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/cpuLoad/register/" + autoRegister, request, String.class);
            log.info(result);
        } catch (Exception e) {
            log.info("Cpu负载信息发送失败");
        }
    }
}
