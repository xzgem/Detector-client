package com.ryoua.controller;

import com.ryoua.acquire.MachineInfoAcquire;
import com.ryoua.model.MachineInfo;
import com.ryoua.model.Result;
import com.ryoua.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * @Author: RyouA
 * @Date: 2020/5/12 - 1:51 下午
 **/
@RestController
@Slf4j
public class SystemController extends BaseController{
    public void SystemInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            MachineInfo machineInfo = machineInfoService.getMachineInfo();
            HttpEntity<MachineInfo> request = new HttpEntity<>(machineInfo, headers);
            machineInfo.setUpdateTime(TimeUtil.getNowTime());
            machineInfo.setOid(MachineInfoAcquire.getOid());
            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/machineInfo/register", request, String.class);
            log.info(result);
        } catch (ResourceAccessException e) {
            log.error("发送系统信息失败");
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/machineInfo")
    public Result<?> getMachineInfo() throws UnknownHostException, SocketException {
        MachineInfo machineInfo = machineInfoService.getMachineInfo();
        machineInfo.setUpdateTime(TimeUtil.getNowTime());
        machineInfo.setOid(MachineInfoAcquire.getOid());
        return Result.SUCCESS(machineInfo);
    }
}
