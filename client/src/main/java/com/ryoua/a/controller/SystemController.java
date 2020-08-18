package com.ryoua.a.controller;

import com.ryoua.system.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@RestController
@Slf4j
public class SystemController extends BaseController {

//    public void sendSystemInfo() {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            SystemInfo systemInfo = systemService.getSystemInfo();
//            HttpEntity<SystemInfo> request = new HttpEntity<>(systemInfo, headers);
//            String result = this.restTemplate.postForObject("http://" + host + ":" + port + "/systemInfo/register/" + autoRegister, request, String.class);
//            log.info(result);
//        } catch (Exception e) {
//            log.info("发送信息失败");
//        }
//    }
//
//    @GetMapping("/systemInfo")
//    public Result getSystemInfo() throws UnknownHostException {
//        SystemInfo systemInfo = systemService.getSystemInfo();
//        return new Result<>(200, "查询成功", systemInfo);
//    }
}
