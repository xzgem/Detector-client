package com.ryoua.system.controller;

import com.ryoua.system.model.CpuLoad;
import com.ryoua.system.model.LoadInfo;
import com.ryoua.system.model.MemoryLoad;
import com.ryoua.system.model.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@RestController
@Slf4j
public class LoadController extends BaseController {
    public void sendLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            LoadInfo loadInfo = loadService.getLoadInfo();
            HttpEntity<LoadInfo> request = new HttpEntity<>(loadInfo, headers);
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/loadInfo/register/" + autoRegister, request, String.class);
            log.info(result);
        } catch (Exception e) {
            log.info("发送信息失败");
        }
    }

    public void sendCpuLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            CpuLoad cpuLoad = loadService.getCpuLoad();
            HttpEntity<CpuLoad> request = new HttpEntity<>(cpuLoad, headers);
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/loadInfo/register/cpu" + autoRegister, request, String.class);
            log.info(result);
        } catch (Exception e) {
            log.info("发送信息失败");
        }
    }

    public void sendMemoryLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            MemoryLoad memoryLoad = loadService.getMemoryLoad();
            HttpEntity<MemoryLoad> request = new HttpEntity<>(memoryLoad, headers);
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/loadInfo/register/memory" + autoRegister, request, String.class);
            log.info(result);
        } catch (Exception e) {
            log.info("发送信息失败");
        }
    }

    @GetMapping("/loadInfo/memory")
    public Result getMemoryLoadInfo() {
        return new Result<>(200, "查询成功", loadService.getMemoryLoad());
    }

    @GetMapping("/loadInfo/cpu")
    public Result getCpuLoadInfo() throws InterruptedException {
        return new Result<>(200, "查询成功", loadService.getCpuLoad());
    }

    @GetMapping("/loadInfo/load")
    public Result getLoadInfo() throws InterruptedException {
        return new Result<>(200, "查询成功", loadService.getLoadInfo());
    }
}
