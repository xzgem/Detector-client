package com.ryoua.controller;

import com.ryoua.model.LoadInfo;
import com.ryoua.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@RestController
@Slf4j
public class LoadController extends BaseController {
    /**
     * 发送负载信息
     */
    public void sendLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            LoadInfo loadInfo = loadInfoService.getLoadInfo();
            HttpEntity<LoadInfo> request = new HttpEntity<>(loadInfo, headers);
            loadInfo.setUpdateTime(TimeUtil.getNowTime());
            loadInfo.setUpdateTimeMills(System.currentTimeMillis());
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/loadInfo/register", request, String.class);
            log.info(result);
        } catch (ResourceAccessException e) {
            log.error("发送系统信息失败");
        }
    }
}
