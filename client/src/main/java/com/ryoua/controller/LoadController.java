package com.ryoua.controller;

import com.ryoua.acquire.MachineInfoAcquire;
import com.ryoua.model.LoadInfo;
import com.ryoua.service.MachineInfoService;
import com.ryoua.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import oshi.SystemInfo;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@RestController
@Slf4j
public class LoadController extends BaseController {
    /**
     * 获取负载信息
     */
    public void getLoadInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            LoadInfo loadInfo = loadInfoService.getLoadInfo();
            HttpEntity<LoadInfo> request = new HttpEntity<>(loadInfo, headers);
            loadInfo.setUpdateTime(TimeUtil.getNowTime());
            if (oid.equals("null"))
                loadInfo.setOid(MachineInfoAcquire.getOid());
            else
                loadInfo.setOid(oid);
            loadInfo.setUpdateTimeMills(System.currentTimeMillis());
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/loadInfo/register", request, String.class);
            log.info(result);
        } catch (ResourceAccessException e) {
            log.error("发送系统信息失败");
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
