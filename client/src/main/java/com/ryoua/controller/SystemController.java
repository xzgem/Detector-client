package com.ryoua.controller;

import com.ryoua.acquire.BaseResourceInfoAcquire;
import com.ryoua.acquire.BaseSystemInfoAcquire;
import com.ryoua.model.ResourceInfo;
import com.ryoua.model.Result;
import com.ryoua.model.SystemInfo;
import com.ryoua.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: RyouA
 * @Date: 2020/5/12 - 1:51 下午
 **/
@RestController
@Slf4j
public class SystemController {
    @Value("${detector.main.host}")
    private String host;

    @Value("${detector.main.port}")
    private String port;

    @Autowired
    private BaseSystemInfoAcquire systemInfoAcquire;

    @Autowired
    private BaseResourceInfoAcquire resourceInfoAcquire;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public String index() {
        return "123";
    }

//    public void SystemInfo() {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            SystemInfo systemInfo = systemInfoAcquire.getSystemInfo();
//            HttpEntity<SystemInfo> request = new HttpEntity<>(systemInfo, headers);
//            systemInfo.setUpdateTime(TimeUtil.getNowTime());
//            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/systemInfo", request, String.class);
//            log.info(result);
//        } catch (ResourceAccessException e) {
//            log.error("发送系统信息失败");
//        }
//    }

//    public void ResourceInfo() {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            ResourceInfo resourceInfo = resourceInfoAcquire.getResourceInfo();
//            HttpEntity<ResourceInfo> request = new HttpEntity<>(resourceInfo, headers);
//            resourceInfo.setUpdateTime(TimeUtil.getNowTime());
//            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/resourceInfo", request, String.class);
//            log.info(result);
//        } catch (ResourceAccessException e) {
//            log.error("发送系统信息失败");
//        }
//    }

//    @GetMapping("/")
//    public Result<?> getInfo() {
//        List<Object> list = new ArrayList<>();
//        SystemInfo systemInfo = systemInfoAcquire.getSystemInfo();
//        ResourceInfo resourceInfo = resourceInfoAcquire.getResourceInfo();
//
//        systemInfo.setUpdateTime(TimeUtil.getNowTime());
//        resourceInfo.setUpdateTime(TimeUtil.getNowTime());
//
//        list.add(systemInfo);
//        list.add(resourceInfo);
//
//        return Result.SUCCESS(list);
//    }

//    @GetMapping("/systemInfo")
//    public Result<?> getSystemInfo() {
//        SystemInfo systemInfo = systemInfoAcquire.getSystemInfo();
//        systemInfo.setUpdateTime(TimeUtil.getNowTime());
//        return Result.SUCCESS(systemInfo);
//    }

//    @GetMapping("/resourceInfo")
//    public Result<?> getResourceInfo() {
//        ResourceInfo resourceInfo = resourceInfoAcquire.getResourceInfo();
//        resourceInfo.setUpdateTime(TimeUtil.getNowTime());
//        return Result.SUCCESS(resourceInfo);
//    }
}
