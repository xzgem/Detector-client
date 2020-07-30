package com.ryoua.controller;

import com.ryoua.acquire.SystemInfoAcquire;
import com.ryoua.model.SystemInfo;
import com.ryoua.model.common.Result;
import com.ryoua.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import java.net.UnknownHostException;


/**
 * @Author: RyouA
 * @Date: 2020/5/12 - 1:51 下午
 **/
@RestController
@Slf4j
public class SystemController extends BaseController {
    public void SystemInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            SystemInfo systemInfo = systemInfoService.getSystemInfo();
            HttpEntity<SystemInfo> request = new HttpEntity<>(systemInfo, headers);
            systemInfo.setUpdateTime(TimeUtil.getNowTime());
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/machineInfo/register", request, String.class);
            log.info(result);
        } catch (ResourceAccessException e) {
            log.error("发送系统信息失败");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/machineInfo")
    public Result<?> getMachineInfo() throws UnknownHostException {
        SystemInfo systemInfo = systemInfoService.getSystemInfo();
        systemInfo.setUpdateTime(TimeUtil.getNowTime());
        systemInfo.setId(SystemInfoAcquire.getMid());
        return Result.SUCCESS(systemInfo);
    }
}
